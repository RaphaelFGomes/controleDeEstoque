package com.raphael.springbootionic.dto;

import java.io.Serializable;

public class BebidaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String marca;
	private Integer tipo;
	private String tipoDescricao;

	public BebidaDTO() {
	}

	public String getNome() {
		return nome;
	}

	public BebidaDTO setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public String getMarca() {
		return marca;
	}

	public BebidaDTO setMarca(String marca) {
		this.marca = marca;
		return this;
	}

	public Integer getTipo() {
		return tipo;
	}

	public BebidaDTO setTipo(Integer tipo) {
		this.tipo = tipo;
		return this;
	}

	public String getTipoDescricao() {
		return tipoDescricao;
	}

	public BebidaDTO setTipoDescricao(String tipoDescricao) {
		this.tipoDescricao = tipoDescricao;
		return this;
	}

}
