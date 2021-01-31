package com.zup.orange.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.orange.domain.exception.DomainException;
import com.zup.orange.domain.model.Cliente;
import com.zup.orange.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente criar(Cliente cliente) {
		
		Cliente clienteExistente = clienteRepository
				.findByEmail(cliente.getEmail());
		
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new DomainException("O email informado já está cadastrado.");
		}
		
		return clienteRepository.save(cliente);
	}
	
	
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	

}
