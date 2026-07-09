package br.com.pedidos_api.mapper;

import br.com.pedidos_api.dto.pedido.PedidoResponse;
import br.com.pedidos_api.entity.PedidoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    PedidoResponse toResponse(PedidoEntity pedidoEntity);
}
