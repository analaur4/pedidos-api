package br.com.pedidos_api.controller;

import br.com.pedidos_api.dto.produto.ProdutoRequest;
import br.com.pedidos_api.dto.produto.ProdutoResponse;
import br.com.pedidos_api.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/produtos")
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoResponse> criarProduto(@RequestBody ProdutoRequest request) {
        ProdutoResponse response = service.criarPoduto(request);
        return ResponseEntity.status(201).body(response);

    }
}
