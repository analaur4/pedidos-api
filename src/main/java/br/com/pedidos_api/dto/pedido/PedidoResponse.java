package br.com.pedidos_api.dto.pedido;

import br.com.pedidos_api.dto.cliente.ClienteResponse;
import br.com.pedidos_api.dto.produto.ProdutoResponse;
import br.com.pedidos_api.enums.StatusPedidoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponse {

    private UUID id;
    private LocalDateTime dataCriacao;
    private StatusPedidoEnum status;
    private BigDecimal valorTotal;
    private ClienteResponse cliente;
    private List<ProdutoResponse> produtos;
}
