package com.raphael.springbootionic.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.raphael.springbootionic.domain.PaymentWithTicket;

@Service
public class TicketService {
	public void completePaymentWithTicket(PaymentWithTicket payment, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		payment.setExpiredDate(cal.getTime());
	}
}
