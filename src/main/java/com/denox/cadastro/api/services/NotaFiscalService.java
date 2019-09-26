package com.denox.cadastro.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.denox.cadastro.api.domain.ItemNotaFiscal;
import com.denox.cadastro.api.domain.NotaFiscal;
import com.denox.cadastro.api.repositories.ItemNotaFiscalRepository;
import com.denox.cadastro.api.repositories.NotaFiscalRepository;
import com.denox.cadastro.api.services.exceptions.ObjectNotFoundException;

@Service
public class NotaFiscalService {

	@Autowired
	private NotaFiscalRepository repo;
	
	@Autowired
	private ItemNotaFiscalRepository itemNotaFiscalRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ItemService itemService;
	
	public NotaFiscal find(Integer id) {
		Optional<NotaFiscal> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + NotaFiscal.class.getName()));
	}
	
	@Transactional
	public NotaFiscal insert(NotaFiscal obj) {
		obj.setId(null);
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj = repo.save(obj);
		for (ItemNotaFiscal iNf : obj.getItens()) {
			iNf.setItem(itemService.find(iNf.getItem().getId()));
			iNf.setPreco(iNf.getItem().getPreco());
			iNf.setNotaFiscal(obj);
			
		}
		
		itemNotaFiscalRepository.saveAll(obj.getItens());
		return obj;
	}
	
	public List<NotaFiscal> findAll() {
		return repo.findAll();
	}
}
