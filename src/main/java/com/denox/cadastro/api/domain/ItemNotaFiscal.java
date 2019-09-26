package com.denox.cadastro.api.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemNotaFiscal  implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemNotaFiscalPK id = new ItemNotaFiscalPK();
	
	private Integer quantidade;
	private Double preco;
	
	public ItemNotaFiscal() {
	}

	public ItemNotaFiscal(NotaFiscal notaFiscal, Item item, Integer quantidade, Double preco) {
		super();
		id.setNotaFiscal(notaFiscal);
		id.setItem(item);
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
 	public double getSubTotal() {
 		return preco * quantidade;
 	}
 	
 	@JsonIgnore	
	public NotaFiscal getNotaFiscal() {
		return id.getNotaFiscal();
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		id.setNotaFiscal(notaFiscal);
	}

	public Item getItem() {
		return id.getItem();
	}

	public void setItem(Item item) {
		id.setItem(item);
	}

	public ItemNotaFiscalPK getId() {
		return id;
	}

	public void setId(ItemNotaFiscalPK id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
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
		ItemNotaFiscal other = (ItemNotaFiscal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getItem().getNome());
		builder.append(", Qte: ");
		builder.append(getQuantidade());
		builder.append(", Preço unitário: ");
		builder.append(nf.format(getPreco()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}
 	
 	
 	
 	
	
	
	
	
}
