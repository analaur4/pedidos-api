package br.com.pedidos_api.service;

import br.com.pedidos_api.dto.cliente.ClienteRequest;
import br.com.pedidos_api.dto.cliente.ClienteResponse;
import br.com.pedidos_api.entity.ClienteEntity;
import br.com.pedidos_api.mapper.ClienteMapper;
import br.com.pedidos_api.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteResponse criarCliente(final ClienteRequest request) {
        final ClienteEntity cliente = mapper.toEntity(request);
        return mapper.toResponse(repository.save(cliente));
    }

    public List<ClienteResponse> listarClientes() {
        final List<ClienteEntity> clientes = repository.findAll();
        return clientes.stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ClienteResponse buscarClientePorId(final UUID idCliente) {
        final ClienteEntity cliente = repository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return mapper.toResponse(cliente);
    }

    public ClienteResponse atualizarCliente(final UUID idCliente, final ClienteRequest request) {
        repository.findById(idCliente).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        final ClienteEntity clienteEntity = ClienteEntity.builder()
                .id(idCliente)
                .nome(request.getNome())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .cpf(request.getCpf())
                .build();
        repository.save(clienteEntity);
        return mapper.toResponse(clienteEntity);
    }
}
