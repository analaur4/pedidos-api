package br.com.pedidos_api.enums;

import lombok.Getter;

@Getter
public enum StatusPedidoEnum {

    EM_PROCESSAMENTO,
    CONCLUIDO,
    CANCELADO;

    private String status;
}
