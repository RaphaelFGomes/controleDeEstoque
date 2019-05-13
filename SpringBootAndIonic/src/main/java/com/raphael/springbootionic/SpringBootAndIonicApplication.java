package com.raphael.springbootionic;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.raphael.springbootionic.domain.Address;
import com.raphael.springbootionic.domain.Category;
import com.raphael.springbootionic.domain.City;
import com.raphael.springbootionic.domain.Client;
import com.raphael.springbootionic.domain.Payment;
import com.raphael.springbootionic.domain.PaymentWithCard;
import com.raphael.springbootionic.domain.PaymentWithTicket;
import com.raphael.springbootionic.domain.Product;
import com.raphael.springbootionic.domain.Request;
import com.raphael.springbootionic.domain.RequestItem;
import com.raphael.springbootionic.domain.State;
import com.raphael.springbootionic.domain.enums.ClientType;
import com.raphael.springbootionic.domain.enums.PaymentState;
import com.raphael.springbootionic.repositories.AddressRepository;
import com.raphael.springbootionic.repositories.CategoryRepository;
import com.raphael.springbootionic.repositories.CityRepository;
import com.raphael.springbootionic.repositories.ClientRepository;
import com.raphael.springbootionic.repositories.PaymentRepository;
import com.raphael.springbootionic.repositories.ProductRepository;
import com.raphael.springbootionic.repositories.RequestItemRepository;
import com.raphael.springbootionic.repositories.RequestRepository;
import com.raphael.springbootionic.repositories.StateRepository;


@SpringBootApplication
public class SpringBootAndIonicApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;	
	@Autowired
	private ProductRepository productRepository;	
	@Autowired
	private CityRepository cityRepository;	
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private RequestItemRepository requestItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAndIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);
		
		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(est1, est2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "3534535", ClientType.PERSON);
		cli1.getPhoneNumbers().addAll(Arrays.asList("53453543", "6435645"));
		
		Address e1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "42342432", cli1, c1);
		Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "343453553", cli1, c2);
		
		cli1.getAddresses().addAll(Arrays.asList(e1, e2));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Request req1 = new Request(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Request req2 = new Request(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Payment pagt1 = new PaymentWithCard(null, PaymentState.SETTLED, req1, 6);
		req1.setPayment(pagt1);
		
		Payment pagt2 = new PaymentWithTicket(null, PaymentState.PENDING, req2, sdf.parse("20/10/2017 19:35"), null);
		req2.setPayment(pagt2);
		
		cli1.getRequests().addAll(Arrays.asList(req1, req2));
		
		requestRepository.saveAll(Arrays.asList(req1, req2));
		paymentRepository.saveAll(Arrays.asList(pagt1, pagt2));
		
		RequestItem ri1 = new RequestItem(req1, p1, 0.00, 1, 2000.0);
		RequestItem ri2 = new RequestItem(req1, p3, 0.00, 2, 80.0);
		RequestItem ri3 = new RequestItem(req2, p2, 100.00, 1, 800.0);
		
		req1.getItems().addAll(Arrays.asList(ri1, ri2));
		req2.getItems().addAll(Arrays.asList(ri3));
		
		p1.getItems().addAll(Arrays.asList(ri1));
		p2.getItems().addAll(Arrays.asList(ri3));
		p3.getItems().addAll(Arrays.asList(ri2));
		
		requestItemRepository.saveAll(Arrays.asList(ri1, ri2, ri3));
		*/
		
	}

}
