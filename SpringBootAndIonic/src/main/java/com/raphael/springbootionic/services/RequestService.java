package com.raphael.springbootionic.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raphael.springbootionic.domain.PaymentWithTicket;
import com.raphael.springbootionic.domain.Request;
import com.raphael.springbootionic.domain.RequestItem;
import com.raphael.springbootionic.domain.enums.PaymentState;
import com.raphael.springbootionic.exceptions.ObjectNotFoundException;
import com.raphael.springbootionic.repositories.PaymentRepository;
import com.raphael.springbootionic.repositories.RequestItemRepository;
import com.raphael.springbootionic.repositories.RequestRepository;

@Service
public class RequestService {

	@Autowired
	private RequestRepository repo;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private RequestItemRepository requestItemRepository;

	@Autowired
	private ProductService productService;

	public Request getRequest(Integer id) {
		Optional<Request> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Request.class.getName()));
	}

	@Transactional
	public Request insert(Request obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.getPayment().setState(PaymentState.PENDING);
		obj.getPayment().setRequest(obj);
		if (obj.getPayment() instanceof PaymentWithTicket) {
			PaymentWithTicket pagto = (PaymentWithTicket) obj.getPayment();
			ticketService.completePaymentWithTicket(pagto, obj.getInstant());
		}
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for (RequestItem ip : obj.getItems()) {
			ip.setDiscount(0.0);
			ip.setPrice(productService.find(ip.getProduct().getId()).getPrice());
			ip.setRequest(obj);
		}
		requestItemRepository.saveAll(obj.getItems());
		return obj;
	}

}
