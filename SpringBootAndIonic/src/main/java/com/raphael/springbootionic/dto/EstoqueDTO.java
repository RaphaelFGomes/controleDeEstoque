package com.raphael.springbootionic.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.raphael.springbootionic.services.validation.EstoqueRequisicao;

@EstoqueRequisicao
public class EstoqueDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer secao;
	private BebidaDTO bebida;
	private Integer volume;

	@NotEmpty(message = "Nome do responsável é obrigatório!")
	@Length(min = 1, max = 120, message = "O tamanho do nome do responsável deve ser entre 1 e 120 caracteres!")
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
