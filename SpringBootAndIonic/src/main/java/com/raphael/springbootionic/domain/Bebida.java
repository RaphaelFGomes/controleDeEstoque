package com.raphael.springbootionic.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.raphael.springbootionic.domain.enums.TipoBebida;

@Entity
public class Bebida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String marca;
	private Integer tipo;

	public Bebida() {

	}

	public Bebida(Integer id, String nome, String marca, TipoBebida tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.tipo = tipo.getCodigo();
	}

	public Integer getId() {
		return id;
	}

	public Bebida setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getNome() {
		return nome;
	}

	public Bebida setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public String getMarca() {
		return marca;
	}

	public Bebida setMarca(String marca) {
		this.marca = marca;
		return this;
	}

	public Integer getTipo() {
		return tipo;
	}

	public Bebida setTipo(Integer tipo) {
		this.tipo = tipo;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Bebida other = (Bebida) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}
