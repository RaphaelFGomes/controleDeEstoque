package com.raphael.springbootionic.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoricoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String horario;
	private String tipoRequisicao;
	private Integer volume;
	private String nomeBebida;
	private String marcaBebida;
	private Integer tipoBebida;
	private String tipoBebidaDescricao;
	private Integer secao;
	private String responsavel;

	public HistoricoDTO() {

	}

	public String getHorario() {
		return horario;
	}

	public HistoricoDTO setHorario(String horario) {
		this.horario = horario;
		return this;
	}

	public String getTipoRequisicao() {
		return tipoRequisicao;
	}

	public HistoricoDTO setTipoRequisicao(String tipoRequisicao) {
		this.tipoRequisicao = tipoRequisicao;
		return this;
	}

	public Integer getVolume() {
		return volume;
	}

	public HistoricoDTO setVolume(Integer volume) {
		this.volume = volume;
		return this;
	}

	public String getNomeBebida() {
		return nomeBebida;
	}

	public HistoricoDTO setNomeBebida(String nomeBebida) {
		this.nomeBebida = nomeBebida;
		return this;
	}

	public String getMarcaBebida() {
		return marcaBebida;
	}

	public HistoricoDTO setMarcaBebida(String marcaBebida) {
		this.marcaBebida = marcaBebida;
		return this;
	}

	public Integer getTipoBebida() {
		return tipoBebida;
	}

	public HistoricoDTO setTipoBebida(Integer tipoBebida) {
		this.tipoBebida = tipoBebida;
		return this;
	}

	public String getTipoBebidaDescricao() {
		return tipoBebidaDescricao;
	}

	public HistoricoDTO setTipoBebidaDescricao(String tipoBebidaDescricao) {
		this.tipoBebidaDescricao = tipoBebidaDescricao;
		return this;
	}

	public Integer getSecao() {
		return secao;
	}

	public HistoricoDTO setSecao(Integer secao) {
		this.secao = secao;
		return this;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public HistoricoDTO setResponsavel(String responsavel) {
		this.responsavel = responsavel;
		return this;
	}

}
