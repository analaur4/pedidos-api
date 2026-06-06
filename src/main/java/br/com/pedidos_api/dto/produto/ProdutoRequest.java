package br.com.pedidos_api.dto.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo;

}
