package com.raphael.springbootionic.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Historico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date horario;
	private String tipoRequisicao;
	private Double volume;
	private Bebida bedida;
	private Integer secao;
	private String responsavel;

	public Historico() {

	}

	public Historico(Integer id, Date horario, String tipoRequisicao, Double volume, Bebida bedida, Integer secao,
			String responsavel) {
		super();
		this.id = id;
		this.horario = horario;
		this.tipoRequisicao = tipoRequisicao;
		this.volume = volume;
		this.bedida = bedida;
		this.secao = secao;
		this.responsavel = responsavel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public String getTipoRequisicao() {
		return tipoRequisicao;
	}

	public void setTipoRequisicao(String tipoRequisicao) {
		this.tipoRequisicao = tipoRequisicao;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Bebida getBedida() {
		return bedida;
	}

	public void setBedida(Bebida bedida) {
		this.bedida = bedida;
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

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bedida == null) ? 0 : bedida.hashCode());
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((secao == null) ? 0 : secao.hashCode());
		result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
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
		if (bedida == null) {
			if (other.bedida != null)
				return false;
		} else if (!bedida.equals(other.bedida))
			return false;
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
