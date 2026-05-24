package br.com.pedidos_api.service;

import br.com.pedidos_api.dto.cliente.ClienteRequest;
import br.com.pedidos_api.dto.cliente.ClienteResponse;
import br.com.pedidos_api.entity.ClienteEntity;
import br.com.pedidos_api.mapper.ClienteMapper;
import br.com.pedidos_api.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteResponse criarCliente(ClienteRequest request) {
        ClienteEntity cliente = mapper.toEntity(request);
        return mapper.toResponse(repository.save(cliente));
    }

    public List<ClienteResponse> listarClientes() {
        List<ClienteEntity> clientes = repository.findAll();
        return clientes.stream()
                .map(mapper::toResponse)
                .toList();
    }
}
