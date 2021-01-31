package com.zup.orange.domain.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.orange.domain.exception.DomainException;
import com.zup.orange.domain.model.Cliente;
import com.zup.orange.domain.model.Loteria;
import com.zup.orange.domain.repository.ClienteRepository;
import com.zup.orange.domain.repository.loteriaRepository;

@Service
public class LoteriaService {
	
	@Autowired
	private loteriaRepository loteriaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Loteria Gerar(Loteria loteria) {
		Cliente cliente = clienteRepository.findById(loteria.getCliente().getId())
				.orElseThrow(()-> new DomainException("Cliente não encontrado"));
		
		loteria.setCliente(cliente);
		var gerador = new Random();
		loteria.setNumeros(gerador.nextInt());
		loteria.setDataCompra(LocalDateTime.now());
		return loteriaRepository.save(loteria);
				
	} 

}
