package com.raphael.springbootionic.dto;

import java.io.Serializable;

public class SecaoDisponivelDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer secao;
	private Integer volumeDisponivel;
	private Integer volumeAtual;

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

}
