package br.com.pedidos_api.controller;

import br.com.pedidos_api.dto.pedido.PedidoRequest;
import br.com.pedidos_api.dto.pedido.PedidoResponse;
import br.com.pedidos_api.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/pedidos")
public class PedidoController {

    private final PedidoService service;

    @PostMapping
    public ResponseEntity<PedidoResponse> criarPedido(@RequestBody PedidoRequest request) {
        PedidoResponse response = service.criarPedido(request);
        return ResponseEntity.status(201).body(response);
    }
}
