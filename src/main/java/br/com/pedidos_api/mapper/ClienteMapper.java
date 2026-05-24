package br.com.pedidos_api.mapper;

import br.com.pedidos_api.dto.cliente.ClienteRequest;
import br.com.pedidos_api.dto.cliente.ClienteResponse;
import br.com.pedidos_api.entity.ClienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteEntity toEntity(ClienteRequest request);
    ClienteResponse toResponse(ClienteEntity entity);
}
