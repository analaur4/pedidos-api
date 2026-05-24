package br.com.pedidos_api.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    @NotNull(message = "O nome do cliente é obrigatório")
    @NotBlank(message = "O nome do cliente não pode ser vazio")
    private String nome;

    @NotNull(message = "O email do cliente é obrigatório")
    @Email(message = "O email do cliente deve ser válido")
    private String email;

    @NotNull(message = "O telefone do cliente é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter apenas números e ter 10 ou 11 dígitos")
    private String telefone;

    @NotNull(message = "O CPF do cliente é obrigatório")
    private String cpf;
}
