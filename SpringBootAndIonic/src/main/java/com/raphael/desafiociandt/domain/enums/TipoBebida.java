package com.raphael.desafiociandt.domain.enums;

public enum TipoBebida {

	ALCOOLICA(1, "Alcoólica"), NAO_ALCOOLICA(2, "Não Alcoólica"), TODAS(3, "Alcoólica ou Não Alcoólica");

	private int codigo;
	private String descricao;

	private TipoBebida(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoBebida toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}

		for (TipoBebida tipo : TipoBebida.values()) {
			if (codigo.equals(tipo.getCodigo())) {
				return tipo;
			}
		}

		throw new IllegalArgumentException("ID inválido: " + codigo);
	}

}
