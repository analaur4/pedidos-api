package br.com.pedidos_api.enums;

import lombok.Getter;

@Getter
public enum StatusPedidoEnum {

    EM_PROCESSAMENTO,
    PEDIDO_CRIADO,
    CONCLUIDO,
    CANCELADO;

    private String status;
}
