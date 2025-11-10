package com.empresa.api_level_up.service;

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

    public String crearPedido(Pedido pedidoEntire) {

        Cliente cliente = clienteRepo.save(pedidoEntire.getCliente());

        // 2) Crear pedido simple
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);


        // 3) Armar detalles: cargar precio desde Producto por id
        List<DetallePedido> detalles = new ArrayList<>();
        for (DetallePedido dt : pedidoEntire.getDetalle_pedidos()) {
            Producto prod = productoRepo.getReferenceById(dt.getProducto().getId_prod());

            DetallePedido det = new DetallePedido();
            det.setCant(dt.getCant());
            det.setPedido(pedido);
            det.setProducto(prod);

            detalles.add(det);
        }
        pedido.setDetalle_pedidos(detalles);

        pedidoRepo.save(pedido);
        return "Pedido creado!";

    }

    public List<Pedido> listarPedidos() {
        return pedidoRepo.findAll();
    }

}
