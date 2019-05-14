package com.raphael.desafiociandt.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.raphael.desafiociandt.services.validation.EstoqueRequisicao;

@EstoqueRequisicao
public class EstoqueDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer secao;
	private BebidaDTO bebida;
	private Integer volume;
	
	@Length(min = 1, max = 100, message = "O tamanho do nome do responsável deve ser entre 1 e 100 caracteres!")
	private String responsavel;

	public EstoqueDTO() {

	}

	public Integer getSecao() {
		return secao;
	}

	public EstoqueDTO setSecao(Integer secao) {
		this.secao = secao;
		return this;
	}

	public BebidaDTO getBebida() {
		return bebida;
	}

	public EstoqueDTO setBebida(BebidaDTO bebida) {
		this.bebida = bebida;
		return this;
	}

	public Integer getVolume() {
		return volume;
	}

	public EstoqueDTO setVolume(Integer volume) {
		this.volume = volume;
		return this;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public EstoqueDTO setResponsavel(String responsavel) {
		this.responsavel = responsavel;
		return this;
	}

}
