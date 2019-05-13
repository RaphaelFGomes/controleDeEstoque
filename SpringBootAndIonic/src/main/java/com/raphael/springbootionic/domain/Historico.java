package com.raphael.springbootionic.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Historico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String horario;
	private LocalDate horarioLocalDate;
	private String tipoRequisicao;
	private Integer volume;
	private String nomeBebida;
	private String marcaBebida;
	private Integer tipoBebida;
	private Integer secao;
	private String responsavel;

	public Historico() {

	}

	public Integer getId() {
		return id;
	}

	public Historico setId(Integer id) {
		this.id = id;
		return this;
	}
	
	public String getTipoRequisicao() {
		return tipoRequisicao;
	}

	public Historico setTipoRequisicao(String tipoRequisicao) {
		this.tipoRequisicao = tipoRequisicao;
		return this;
	}

	public Integer getVolume() {
		return volume;
	}

	public Historico setVolume(Integer volume) {
		this.volume = volume;
		return this;
	}

	public Integer getSecao() {
		return secao;
	}

	public void getSecao(Integer secao) {
		this.secao = secao;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public Historico setResponsavel(String responsavel) {
		this.responsavel = responsavel;
		return this;
	}

	public String getNomeBebida() {
		return nomeBebida;
	}

	public Historico setNomeBebida(String nomeBebida) {
		this.nomeBebida = nomeBebida;
		return this;
	}

	public String getMarcaBebida() {
		return marcaBebida;
	}

	public Historico setMarcaBebida(String marcaBebida) {
		this.marcaBebida = marcaBebida;
		return this;
	}

	public Integer getTipoBebida() {
		return tipoBebida;
	}

	public Historico setTipoBebida(Integer tipoBebida) {
		this.tipoBebida = tipoBebida;
		return this;
	}

	public Historico setSecao(Integer secao) {
		this.secao = secao;
		return this;
	}

	public String getHorario() {
		return horario;
	}

	public Historico setHorario(String horario) {
		this.horario = horario;
		return this;
	}

	public LocalDate getHorarioLocalDate() {
		return horarioLocalDate;
	}

	public Historico setHorarioLocalDate(LocalDate horarioLocalDate) {
		this.horarioLocalDate = horarioLocalDate;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		result = prime * result + ((horarioLocalDate == null) ? 0 : horarioLocalDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((marcaBebida == null) ? 0 : marcaBebida.hashCode());
		result = prime * result + ((nomeBebida == null) ? 0 : nomeBebida.hashCode());
		result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
		result = prime * result + ((secao == null) ? 0 : secao.hashCode());
		result = prime * result + ((tipoBebida == null) ? 0 : tipoBebida.hashCode());
		result = prime * result + ((tipoRequisicao == null) ? 0 : tipoRequisicao.hashCode());
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());
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
		Historico other = (Historico) obj;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
			return false;
		if (horarioLocalDate == null) {
			if (other.horarioLocalDate != null)
				return false;
		} else if (!horarioLocalDate.equals(other.horarioLocalDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (marcaBebida == null) {
			if (other.marcaBebida != null)
				return false;
		} else if (!marcaBebida.equals(other.marcaBebida))
			return false;
		if (nomeBebida == null) {
			if (other.nomeBebida != null)
				return false;
		} else if (!nomeBebida.equals(other.nomeBebida))
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		if (secao == null) {
			if (other.secao != null)
				return false;
		} else if (!secao.equals(other.secao))
			return false;
		if (tipoBebida == null) {
			if (other.tipoBebida != null)
				return false;
		} else if (!tipoBebida.equals(other.tipoBebida))
			return false;
		if (tipoRequisicao == null) {
			if (other.tipoRequisicao != null)
				return false;
		} else if (!tipoRequisicao.equals(other.tipoRequisicao))
			return false;
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		return true;
	}
	
}
