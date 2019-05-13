package com.raphael.springbootionic.domain.enums;

public enum ClientType {
	
	PERSON(1, "Person"),
	COMPANY(2, "Company"); 
	
	private int cod;
	private String description;
	
	private ClientType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	public static ClientType toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (ClientType type : ClientType.values() ) {
			if (cod.equals(type.getCod())) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + cod);
	}
	
}
