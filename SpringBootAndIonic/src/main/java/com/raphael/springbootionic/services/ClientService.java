package com.raphael.springbootionic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raphael.springbootionic.domain.Address;
import com.raphael.springbootionic.domain.City;
import com.raphael.springbootionic.domain.Client;
import com.raphael.springbootionic.domain.enums.ClientType;
import com.raphael.springbootionic.dto.ClientDTO;
import com.raphael.springbootionic.dto.ClientNewDTO;
import com.raphael.springbootionic.exceptions.DataIntegrityException;
import com.raphael.springbootionic.exceptions.ObjectNotFoundException;
import com.raphael.springbootionic.repositories.AddressRepository;
import com.raphael.springbootionic.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Client find(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Object not found! Id: " + id + ", Type: " + Client.class.getName()));
		}
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		addressRepository.saveAll(obj.getAddresses());
		return obj;
		}
	
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
		}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Is not possible delete this client that contains requests!");
		}
		
		}
	
	public List<Client> findAll() {		
		return repo.findAll();
		}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pagerequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pagerequest);
		}
	
	public Client fromDTO(ClientDTO objDTO) {
		return new Client(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDTO) {
		Client cli = new Client(null, objDTO.getName(), objDTO.getEmail(), objDTO.getCode(), ClientType.toEnum(objDTO.getType()));
		City cit = new City(objDTO.getCityId(), null, null);
		Address addr = new Address(null, objDTO.getPublicPlace(), objDTO.getNumber(), objDTO.getComplement(), objDTO.getNeighborhood(), objDTO.getZipCode(), cli, cit);
		cli.getAddresses().add(addr);
		cli.getPhoneNumbers().add(objDTO.getPhone1());
		if (objDTO.getPhone2() != null) {
			cli.getPhoneNumbers().add(objDTO.getPhone2());
		}
		
		if (objDTO.getPhone3() != null) {
			cli.getPhoneNumbers().add(objDTO.getPhone3());
		}
		
		return cli;
	}
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

}
