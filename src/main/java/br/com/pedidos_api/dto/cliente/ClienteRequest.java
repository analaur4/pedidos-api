package br.com.pedidos_api.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    private String nome;
    private String email;
    private String telefone;
    private String cpf;
}
