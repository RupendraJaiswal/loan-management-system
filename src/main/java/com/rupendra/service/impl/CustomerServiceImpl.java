package com.rupendra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rupendra.exception.ResourceNotFoundException;
import com.rupendra.model.Customer;
import com.rupendra.repository.CustomerRepository;
import com.rupendra.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public Customer updateCustomer(Long id, Customer updatedCustomer) {
		Customer customer = customerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
		customer.setName(updatedCustomer.getName());
		customer.setEmail(updatedCustomer.getEmail());
		customer.setContactNumber(updatedCustomer.getContactNumber());
		customer.setAddress(updatedCustomer.getAddress());
		return customerRepo.save(customer);
	}

	@Override
	public Customer getCustomer(Long id) {
		return customerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
	}
}
