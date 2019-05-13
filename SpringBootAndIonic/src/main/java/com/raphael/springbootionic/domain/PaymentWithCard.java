package com.raphael.springbootionic.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.raphael.springbootionic.domain.enums.PaymentState;

@Entity
@JsonTypeName("paymentWithCard")
public class PaymentWithCard extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Integer numberOfInstallments;
	
	public PaymentWithCard ( ) {
		
	}

	public PaymentWithCard(Integer id, PaymentState state, Request request, Integer numberOfInstallments) {
		super(id, state, request);
		this.numberOfInstallments = numberOfInstallments;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}

}
