package com.raphael.springbootionic.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespostaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	//@JsonInclude(Include.NON_NULL)
	private String message;
	
	//@JsonInclude(Include.NON_NULL)
	private Integer idBebida;
	
	//@JsonInclude(Include.NON_NULL)
	private Integer tipoBebida;
	
	//@JsonInclude(Include.NON_NULL)
	private String tipoBebidaDescricao;
	
	//@JsonInclude(Include.NON_NULL)
	private Integer secao;
	
	//@JsonInclude(Include.NON_NULL)
	private Integer volumeTotal;
	
	//@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<SecaoDisponivelDTO> secoesDisponiveis = null;

	public RespostaDTO() {
	}

	public String getMessage() {
		return message;
	}

	public RespostaDTO setMessage(String message) {
		this.message = message;
		return this;
	}

	public Integer getIdBebida() {
		return idBebida;
	}

	public RespostaDTO setIdBebida(Integer idBebida) {
		this.idBebida = idBebida;
		return this;
	}

	public Integer getSecao() {
		return secao;
	}

	public RespostaDTO setSecao(Integer secao) {
		this.secao = secao;
		return this;
	}

	public Integer getTipoBebida() {
		return tipoBebida;
	}

	public RespostaDTO setTipoBebida(Integer tipoBebida) {
		this.tipoBebida = tipoBebida;
		return this;
	}

	public String getTipoBebidaDescricao() {
		return tipoBebidaDescricao;
	}

	public RespostaDTO setTipoBebidaDescricao(String tipoBebidaDescricao) {
		this.tipoBebidaDescricao = tipoBebidaDescricao;
		return this;
	}

	public Integer getVolumeTotal() {
		return volumeTotal;
	}

	public RespostaDTO setVolumeTotal(Integer volumeTotal) {
		this.volumeTotal = volumeTotal;
		return this;
	}

	public List<SecaoDisponivelDTO> getSecoesDisponiveis() {
		return secoesDisponiveis;
	}

	public RespostaDTO setSecoesDisponiveis(List<SecaoDisponivelDTO> secoesDisponiveis) {
		this.secoesDisponiveis = secoesDisponiveis;
		return this;
	}

}
