package br.com.pedidos_api.mapper;

import br.com.pedidos_api.dto.produto.ProdutoRequest;
import br.com.pedidos_api.dto.produto.ProdutoResponse;
import br.com.pedidos_api.entity.ProdutoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoEntity toEntity(ProdutoRequest request);
    ProdutoResponse toResponse(ProdutoEntity entity);
}
