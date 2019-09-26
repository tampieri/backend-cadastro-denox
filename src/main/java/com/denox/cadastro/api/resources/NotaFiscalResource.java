package com.denox.cadastro.api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.denox.cadastro.api.domain.NotaFiscal;
import com.denox.cadastro.api.dto.NotaFiscalDTO;
import com.denox.cadastro.api.services.NotaFiscalService;

@RestController
@RequestMapping(value="/invoice")
public class NotaFiscalResource {

	@Autowired
	private NotaFiscalService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<NotaFiscal> find(@PathVariable Integer id) {
		NotaFiscal obj = service.find(id);		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody NotaFiscal obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<NotaFiscal>> findAll() {
		List<NotaFiscal> list = service.findAll();
		//List<NotaFiscalDTO> listDto = list.stream().map(obj -> new NotaFiscalDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(list);
	}
}
