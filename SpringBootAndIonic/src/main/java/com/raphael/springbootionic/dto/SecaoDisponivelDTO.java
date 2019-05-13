package com.raphael.springbootionic.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecaoDisponivelDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer secao;
	private Integer volumeDisponivel;
	private String volumeDisponivelString;
	private Integer volumeAtual;
	private Integer tipoBebida;
	private String tipoBebidaDescricao;

	public SecaoDisponivelDTO() {
	}

	public Integer getSecao() {
		return secao;
	}

	public SecaoDisponivelDTO setSecao(Integer secao) {
		this.secao = secao;
		return this;
	}

	public Integer getVolumeDisponivel() {
		return volumeDisponivel;
	}

	public SecaoDisponivelDTO setVolumeDisponivel(Integer volumeDisponivel) {
		this.volumeDisponivel = volumeDisponivel;
		return this;
	}

	public Integer getVolumeAtual() {
		return volumeAtual;
	}

	public SecaoDisponivelDTO setVolumeAtual(Integer volumeAtual) {
		this.volumeAtual = volumeAtual;
		return this;
	}

	public Integer getTipoBebida() {
		return tipoBebida;
	}

	public SecaoDisponivelDTO setTipoBebida(Integer tipoBebida) {
		this.tipoBebida = tipoBebida;
		return this;
	}

	public String getTipoBebidaDescricao() {
		return tipoBebidaDescricao;
	}

	public SecaoDisponivelDTO setTipoBebidaDescricao(String tipoBebidaDescricao) {
		this.tipoBebidaDescricao = tipoBebidaDescricao;
		return this;
	}

	public String getVolumeDisponivelString() {
		return volumeDisponivelString;
	}

	public SecaoDisponivelDTO setVolumeDisponivelString(String volumeDisponivelString) {
		this.volumeDisponivelString = volumeDisponivelString;
		return this;
	}

}
