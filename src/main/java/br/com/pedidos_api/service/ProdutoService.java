package br.com.pedidos_api.service;

import br.com.pedidos_api.dto.produto.ProdutoRequest;
import br.com.pedidos_api.dto.produto.ProdutoResponse;
import br.com.pedidos_api.entity.ProdutoEntity;
import br.com.pedidos_api.mapper.ProdutoMapper;
import br.com.pedidos_api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public ProdutoResponse criarPoduto(ProdutoRequest request) {
        final ProdutoEntity entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    public List<ProdutoResponse> listarProdutos() {
        final List<ProdutoEntity> produtos = repository.findAll();
        return produtos.stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ProdutoResponse buscarProdutoPorId(final UUID idProduto) {
        final ProdutoEntity produto = repository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return mapper.toResponse(produto);
    }

}
