package com.rupendra.service;

import com.rupendra.model.Customer;

public interface CustomerService {
 Customer addCustomer(Customer customer);
 Customer updateCustomer(Long id, Customer customer);
 Customer getCustomer(Long id);
}
