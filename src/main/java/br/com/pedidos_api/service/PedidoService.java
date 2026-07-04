package br.com.pedidos_api.service;

import br.com.pedidos_api.dto.pedido.PedidoRequest;
import br.com.pedidos_api.dto.pedido.PedidoResponse;
import br.com.pedidos_api.entity.ClienteEntity;
import br.com.pedidos_api.entity.PedidoEntity;
import br.com.pedidos_api.entity.ProdutoEntity;
import br.com.pedidos_api.enums.StatusPedidoEnum;
import br.com.pedidos_api.mapper.PedidoMapper;
import br.com.pedidos_api.repository.ClienteRepository;
import br.com.pedidos_api.repository.PedidoRepository;
import br.com.pedidos_api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoMapper mapper;

    public PedidoResponse criarPedido(final PedidoRequest request) {
        final ClienteEntity clienteEntity = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        final List<ProdutoEntity> produtoEntityList = produtoRepository.findAllById(request.getProdutosIds())
                .stream()
                .toList();
        if (produtoEntityList.isEmpty()) {
            throw new RuntimeException("Nenhum produto encontrado");
        }

        PedidoEntity entity = PedidoEntity.builder()
                .dataCriacao(LocalDateTime.now())
                .status(StatusPedidoEnum.PEDIDO_CRIADO)
                .valorTotal(calcularValorTotal(produtoEntityList))
                .cliente(clienteEntity)
                .produtos(produtoEntityList)
                .build();

        return mapper.toResponse(repository.save(entity));
    }

    public List<PedidoResponse> listarPedidos() {
        final List<PedidoEntity> pedidos = repository.findAll();
        return pedidos.stream()
                .map(mapper::toResponse)
                .toList();
    }

    private BigDecimal calcularValorTotal(final List<ProdutoEntity> produtos) {
        return produtos.stream()
                .map(ProdutoEntity::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
