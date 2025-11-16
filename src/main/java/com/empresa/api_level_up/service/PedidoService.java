package com.empresa.api_level_up.service;

import com.empresa.api_level_up.dto.request.PagoRequestDTO;
import com.empresa.api_level_up.dto.request.PedidoRequestDTO;
import com.empresa.api_level_up.dto.response.PagoResponseDTO;
import com.empresa.api_level_up.dto.response.PedidoResponseDTO;
import com.empresa.api_level_up.model.*;
import com.empresa.api_level_up.repository.ClienteRepository;
import com.empresa.api_level_up.repository.PagoRepository;
import com.empresa.api_level_up.repository.PedidoRepository;
import com.empresa.api_level_up.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private ProductoRepository productoRepo;


    public PedidoResponseDTO crearPedido(PedidoRequestDTO req) {

        Cliente cliente = new Cliente();
        cliente.setFirst_name_cli(req.cliente.first_name_cli);
        cliente.setLast_name_cli(req.cliente.last_name_cli);
        clienteRepo.save(cliente);


        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);


        List<DetallePedido> detalles = new ArrayList<>();
        for (PedidoRequestDTO.ItemDTO item : req.detalle_pedidos) {
            Producto prod = productoRepo.getReferenceById(item.id_prod);

            prod.setStock_prod(prod.getStock_prod() - item.cant);
            productoRepo.save(prod);

            DetallePedido det = new DetallePedido();
            det.setCant(item.cant);
            det.setPedido(pedido);
            det.setProducto(prod);

            detalles.add(det);
        }
        pedido.setDetalle_pedidos(detalles);

        List<Pago> pagos = new ArrayList<>();
        for(PagoRequestDTO pago : req.pago) {

            Pago p = new Pago();
            p.setMonto_total(pago.getMonto_total());
            p.setFecha_pago(pago.getFecha_pago());
            p.setMetodo_pago(pago.getMetodo_pago());
            p.setPedido(pedido);

            pagos.add(p);
        }
        pedido.setPago(pagos);

        pedidoRepo.save(pedido);

        PedidoResponseDTO out = new PedidoResponseDTO();
        out.pedido_id = pedido.getId_ped();
        out.cliente_id = pedido.getCliente().getId_cli();
        out.items = pedido.getDetalle_pedidos().size();

        for (DetallePedido d : pedido.getDetalle_pedidos()) {
            PedidoResponseDTO.ItemDTO item = new PedidoResponseDTO.ItemDTO();
            item.producto_id = d.getProducto().getId_prod();
            item.cant = d.getCant();
            item.precio = d.getProducto().getPrec_prod();
            out.detalle.add(item);
        }

        for (Pago p : pedido.getPago()) {

            PagoResponseDTO pago = new PagoResponseDTO();
            pago.setMonto_total(p.getMonto_total());
            pago.setFecha_pago(p.getFecha_pago());
            pago.setMetodo_pago(p.getMetodo_pago());
            out.pagos.add(pago);

        }

        return out;

    }

    public List<PedidoResponseDTO> listarPedidos() {

        List<Pedido> pedidos = pedidoRepo.findAll();
        List<PedidoResponseDTO> resPedidosDTO = new ArrayList<>();

        pedidos.forEach(index -> {
            PedidoResponseDTO pedidoDTO = new PedidoResponseDTO();
            pedidoDTO.pedido_id = index.getId_ped();
            pedidoDTO.cliente_id = index.getCliente().getId_cli();
            pedidoDTO.items = index.getDetalle_pedidos().size();

            List<PedidoResponseDTO.ItemDTO> itemsDTO = new ArrayList<>();
            for (DetallePedido d : index.getDetalle_pedidos()) {
                PedidoResponseDTO.ItemDTO itemDTO = new PedidoResponseDTO.ItemDTO();
                itemDTO.producto_id = d.getProducto().getId_prod();
                itemDTO.cant = d.getCant();
                itemDTO.precio = d.getProducto().getPrec_prod();
                itemsDTO.add(itemDTO);
            }
            pedidoDTO.detalle = itemsDTO;

            List<PagoResponseDTO> pagoDTO = new ArrayList<>();
            for (Pago p : index.getPago()) {
                PagoResponseDTO pago = new PagoResponseDTO();
                pago.setId_pago(p.getId_pago());
                pago.setMonto_total(p.getMonto_total());
                pago.setFecha_pago(p.getFecha_pago());
                pago.setMetodo_pago(p.getMetodo_pago());
                pagoDTO.add(pago);
            }
            pedidoDTO.pagos = pagoDTO;
            resPedidosDTO.add(pedidoDTO);
        });
        return resPedidosDTO;
    }

    public PedidoResponseDTO deletePedidoById(Long id_pedido) {
        Pedido pedido = pedidoRepo.getReferenceById(id_pedido);
        pedidoRepo.delete(pedido);

        PedidoResponseDTO res = new PedidoResponseDTO();
        res.pedido_id = pedido.getId_ped();
        res.cliente_id = pedido.getCliente().getId_cli();
        res.items = pedido.getDetalle_pedidos().size();
        List<PedidoResponseDTO.ItemDTO> items = new ArrayList<>();
        for (DetallePedido d : pedido.getDetalle_pedidos()) {
            PedidoResponseDTO.ItemDTO item = new PedidoResponseDTO.ItemDTO();
            item.producto_id = d.getProducto().getId_prod();
            item.cant = d.getCant();
            item.precio = d.getProducto().getPrec_prod();
            items.add(item);
        }
        res.detalle = items;
        return res;
    }

    public String DeleteAllPedidos() {
        pedidoRepo.deleteAll();
        return "Pedidos eliminados con exito";
    }

}
