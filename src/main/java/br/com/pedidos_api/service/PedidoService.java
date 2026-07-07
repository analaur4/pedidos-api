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
import java.util.UUID;

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

    public PedidoResponse buscarPedidoPorId(final UUID pedidoId) {
        final PedidoEntity entity = repository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return mapper.toResponse(entity);
    }

    public PedidoResponse atualizarStatusPedido(final UUID idPedido, final PedidoRequest pedidoRequest) {
        PedidoEntity pedidoEntity = repository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        podeAtualizarPedido(pedidoEntity);

        pedidoEntity = PedidoEntity.builder()
                .status(pedidoRequest.getStatus())
                .build();

        return mapper.toResponse(pedidoEntity);
    }

    public void deletarPedido(final UUID idPedido) {
        final PedidoEntity entity = repository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        if (entity.getStatus().equals(StatusPedidoEnum.CONCLUIDO)) {
            throw new RuntimeException("Pedido não pode ser deletado");
        } else {
            repository.delete(entity);
        }
    }

    private BigDecimal calcularValorTotal(final List<ProdutoEntity> produtos) {
        return produtos.stream()
                .map(ProdutoEntity::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private boolean podeAtualizarPedido(final PedidoEntity entity) {
        if (entity.getStatus().equals(StatusPedidoEnum.CANCELADO) || entity.getStatus().equals(StatusPedidoEnum.CONCLUIDO))
                throw new RuntimeException("Produto não pode ser atualizado");
        return true;
    }
}
