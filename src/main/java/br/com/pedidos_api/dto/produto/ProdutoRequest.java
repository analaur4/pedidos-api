package br.com.pedidos_api.dto.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {

    @NotNull(message = "O nome do produto é obrigatório")
    @NotBlank(message = "O nome do produto não pode ser vazio")
    private String nome;

    @NotNull(message = "A descrição do produto é obrigatória")
    @NotBlank(message = "A descrição do produto não pode ser vazia")
    private String descricao;

    @NotNull(message = "O preço do produto é obrigatório")
    private BigDecimal preco;

    @NotNull(message = "O status do produto é obrigatório")
    private Boolean ativo;

}
