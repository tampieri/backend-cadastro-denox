package com.denox.cadastro.api.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class NotaFiscal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "id.notaFiscal")
	private Set<ItemNotaFiscal> itens = new HashSet<>();
	
	public NotaFiscal() {
	}

	public NotaFiscal(Integer id, Cliente cliente) {
		super();
		this.id = id;
		this.cliente = cliente;
	}
	
	public double getValorTotal() {
		double soma = 0.0;
		for(ItemNotaFiscal iNf : itens) {
			soma = soma+iNf.getSubTotal();
		}
		return soma;
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

	public Set<ItemNotaFiscal> getItens() {
		return itens;
	}

	public void setItens(Set<ItemNotaFiscal> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotaFiscal other = (NotaFiscal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append("Nota fiscal número: ");
		builder.append(getId());
		builder.append(", Cliente: ");
		builder.append(getCliente().getNome());
		builder.append("\nDetalhes:\n");
		for (ItemNotaFiscal iNf : getItens()) {
			builder.append(iNf.toString());
		}
		builder.append("Valor total: ");
		builder.append(nf.format(getValorTotal()));
		return builder.toString();
	}
}
