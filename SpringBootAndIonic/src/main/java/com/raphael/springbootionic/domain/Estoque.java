package com.raphael.springbootionic.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.raphael.springbootionic.domain.enums.TipoBebida;

@Entity
public class Estoque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer secao;
	private Integer tipoBebida;
	private String nomeBebida;
	private String marcaBebida;
	private Integer volume;
	private LocalDate horario;
	private String responsavel;

	public Estoque() {

	}

	public Estoque(Integer id, Integer secao, TipoBebida tipoBebida, String nomeBebida, String marcaBebida,
			Integer volume, LocalDate horario, String responsavel) {
		super();
		this.id = id;
		this.secao = secao;
		this.tipoBebida = tipoBebida.getCodigo();
		this.nomeBebida = nomeBebida;
		this.marcaBebida = marcaBebida;
		this.volume = volume;
		this.horario = horario;
		this.responsavel = responsavel;
	}

	public Integer getId() {
		return id;
	}

	public Estoque setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getSecao() {
		return secao;
	}

	public Estoque setSecao(Integer secao) {
		this.secao = secao;
		return this;
	}

	public Integer getTipoBebida() {
		return tipoBebida;
	}

	public Estoque setTipoBebida(Integer tipoBebida) {
		this.tipoBebida = tipoBebida;
		return this;
	}

	public String getNomeBebida() {
		return nomeBebida;
	}

	public Estoque setNomeBebida(String nomeBebida) {
		this.nomeBebida = nomeBebida;
		return this;
	}

	public String getMarcaBebida() {
		return marcaBebida;
	}

	public Estoque setMarcaBebida(String marcaBebida) {
		this.marcaBebida = marcaBebida;
		return this;
	}

	public Integer getVolume() {
		return volume;
	}

	public Estoque setVolume(Integer volume) {
		this.volume = volume;
		return this;
	}

	public LocalDate getHorario() {
		return horario;
	}

	public Estoque setHorario(LocalDate horario) {
		this.horario = horario;
		return this;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public Estoque setResponsavel(String responsavel) {
		this.responsavel = responsavel;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((marcaBebida == null) ? 0 : marcaBebida.hashCode());
		result = prime * result + ((nomeBebida == null) ? 0 : nomeBebida.hashCode());
		result = prime * result + ((secao == null) ? 0 : secao.hashCode());
		result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
		result = prime * result + ((tipoBebida == null) ? 0 : tipoBebida.hashCode());
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
		Estoque other = (Estoque) obj;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
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
		if (secao == null) {
			if (other.secao != null)
				return false;
		} else if (!secao.equals(other.secao))
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		if (tipoBebida == null) {
			if (other.tipoBebida != null)
				return false;
		} else if (!tipoBebida.equals(other.tipoBebida))
			return false;
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		return true;
	}

}
