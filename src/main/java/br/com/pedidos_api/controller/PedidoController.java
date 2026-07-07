package br.com.pedidos_api.controller;

import br.com.pedidos_api.dto.pedido.PedidoRequest;
import br.com.pedidos_api.dto.pedido.PedidoResponse;
import br.com.pedidos_api.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listarPedidos() {
        return ResponseEntity.ok(service.listarPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscarPedidoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPedidoPorId(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoResponse> atualizarStatusPedido(@PathVariable UUID id, @RequestBody PedidoRequest pedidoRequest) {
        return ResponseEntity.ok(service.atualizarStatusPedido(id, pedidoRequest));
    }
}
