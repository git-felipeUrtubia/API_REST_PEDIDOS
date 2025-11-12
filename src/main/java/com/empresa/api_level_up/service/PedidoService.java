package com.empresa.api_level_up.service;

import com.empresa.api_level_up.dto.request.PedidoRequestDTO;
import com.empresa.api_level_up.dto.response.PedidoResponseDTO;
import com.empresa.api_level_up.model.Cliente;
import com.empresa.api_level_up.model.DetallePedido;
import com.empresa.api_level_up.model.Pedido;
import com.empresa.api_level_up.model.Producto;
import com.empresa.api_level_up.repository.ClienteRepository;
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

        // 2) Crear pedido simple
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);


        // 3) Armar detalles: cargar precio desde Producto por id
        List<DetallePedido> detalles = new ArrayList<>();
        for (PedidoRequestDTO.ItemDTO item : req.detalle_pedidos) {
            Producto prod = productoRepo.getReferenceById(item.id_prod);

            DetallePedido det = new DetallePedido();
            det.setCant(item.cant);
            det.setPedido(pedido);
            det.setProducto(prod);

            detalles.add(det);
        }
        pedido.setDetalle_pedidos(detalles);

        pedidoRepo.save(pedido);

        PedidoResponseDTO out = new PedidoResponseDTO();
        out.pedido_id = pedido.getId_ped();
        out.cliente_id = pedido.getCliente().getId_cli();
        out.items = pedido.getDetalle_pedidos().size();

        for (DetallePedido d : pedido.getDetalle_pedidos()) {
            PedidoResponseDTO.ItemDTO item = new PedidoResponseDTO.ItemDTO();
            item.id_detalle = d.getId_detalle_pedido();
            item.producto_id = d.getProducto().getId_prod();
            item.cant = d.getCant();
            item.precio = d.getProducto().getPrec_prod();
            out.detalle.add(item);
        }

        return out;

    }

    public List<Pedido> listarPedidos() {
        return pedidoRepo.findAll();
    }

}
