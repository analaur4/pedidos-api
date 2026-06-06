package br.com.pedidos_api.controller;

import br.com.pedidos_api.dto.produto.ProdutoRequest;
import br.com.pedidos_api.dto.produto.ProdutoResponse;
import br.com.pedidos_api.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/v1/produtos")
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoResponse> criarProduto(@RequestBody @Valid ProdutoRequest request) {
        ProdutoResponse response = service.criarPoduto(request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarProdutos() {
        return ResponseEntity.ok(service.listarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscarProdutoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarProdutoPorId(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable UUID id, @RequestBody @Valid ProdutoRequest request) {
        return ResponseEntity.ok(service.atualizarProduto(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable UUID id) {
        service.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

}
