package br.com.pedidos_api.dto.pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {

    private UUID clienteId;
    private List<UUID> produtosIds;
}
