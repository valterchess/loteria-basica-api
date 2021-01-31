package com.zup.orange.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zup.orange.domain.model.Loteria;
import com.zup.orange.domain.service.LoteriaService;

@RestController
@RequestMapping("api/loteria")
public class LoteriaController {
	
	@Autowired
	private LoteriaService loteriaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Loteria gerar(@Valid @RequestBody Loteria loteria) {
		return loteriaService.Gerar(loteria);
	}
}
