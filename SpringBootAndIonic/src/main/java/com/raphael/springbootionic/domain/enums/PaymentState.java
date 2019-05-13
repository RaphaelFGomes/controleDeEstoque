package com.raphael.springbootionic.domain.enums;

public enum PaymentState {
	
	PENDING(1, "Pending"),
	SETTLED(2, "Settled"),
	CANCELED(3, "Canceled");
	
	private int cod;
	private String description;
	
	private PaymentState(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	public static PaymentState toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (PaymentState type : PaymentState.values() ) {
			if (cod.equals(type.getCod())) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod);
	}
	
}
