package com.denox.cadastro.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denox.cadastro.api.domain.Item;
import com.denox.cadastro.api.repositories.ItemRepository;
import com.denox.cadastro.api.services.exceptions.ObjectNotFoundException;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository repo;

	public Item find(Integer id) {
		Optional<Item> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Item.class.getName()));
	}
}
