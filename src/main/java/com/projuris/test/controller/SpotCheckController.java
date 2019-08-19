package com.projuris.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projuris.exception.PadraoInvalidoException;
import com.projuris.test.domain.Resultado;
import com.projuris.test.service.ManchaService;

@RestController
@RequestMapping({"/spot_check"})
public class SpotCheckController {
	
	@Autowired
	private ManchaService service;

	@ExceptionHandler(PadraoInvalidoException.class)
	@PostMapping
	public Resultado create(@RequestParam String area){
	   return service.processa(area);
	}
} 