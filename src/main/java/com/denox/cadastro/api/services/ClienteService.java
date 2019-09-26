package com.denox.cadastro.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.denox.cadastro.api.domain.Cliente;
import com.denox.cadastro.api.domain.enums.TipoCliente;
import com.denox.cadastro.api.dto.ClienteNewDTO;
import com.denox.cadastro.api.repositories.ClienteRepository;
import com.denox.cadastro.api.services.exceptions.DataIntegrityException;
import com.denox.cadastro.api.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há notas fiscais relacionadas");
		}
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}

	public Cliente findByCpfOuCnpj(String cpfOuCnpj) {
		Cliente obj = repo.findByCpfOuCnpj(cpfOuCnpj);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! cpf Ou Cnpj: " + Cliente.class.getName());
		}
		return obj;
	}
}
