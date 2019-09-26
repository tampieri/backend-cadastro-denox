package com.denox.cadastro.api.dto;

import java.io.Serializable;
import java.util.List;

import com.denox.cadastro.api.domain.Cliente;
import com.denox.cadastro.api.domain.Item;
import com.denox.cadastro.api.domain.ItemNotaFiscal;
import com.denox.cadastro.api.domain.NotaFiscal;

public class NotaFiscalDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Cliente cliente;
	private List<Item> itens;
	
	public NotaFiscalDTO() {
	}
	
	public NotaFiscalDTO(NotaFiscal obj) {
		super();
		id = obj.getId();
		cliente = obj.getCliente();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Item> getItens() {
		return itens;
	}
	
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
}
