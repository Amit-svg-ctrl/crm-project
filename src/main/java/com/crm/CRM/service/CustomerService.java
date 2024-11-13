package com.crm.CRM.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.CRM.entity.Customer;
import com.crm.CRM.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	// Create customer
	public Customer createCustomer(Customer customer) {
		customer.setMembershipTier(calculateTier(customer.getAnnualSpend()));
		return customerRepository.save(customer);
	}

	public Optional<Customer> getCustomerById(UUID id) {
		return customerRepository.findById(id);
	}

	// Update customer
	public Customer updateCustomer(UUID id, Customer updatedCustomer) {
		Optional<Customer> existingCustomer = customerRepository.findById(id);
		if (existingCustomer.isPresent()) {
			Customer customer = existingCustomer.get();
			customer.setName(updatedCustomer.getName());
			customer.setEmail(updatedCustomer.getEmail());
			customer.setAnnualSpend(updatedCustomer.getAnnualSpend());
			customer.setMembershipTier(calculateTier(updatedCustomer.getAnnualSpend()));
			return customerRepository.save(customer);
		} else {
			throw new RuntimeException("Customer not found");
		}
	}

	// Delete customer
	public void deleteCustomer(UUID id) {
		customerRepository.deleteById(id);
	}

	public List<Customer> getCustomerByName(String name) {
		return customerRepository.findByName(name);
	}

	public List<Customer> getCustomerByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	private String calculateTier(double annualSpend) {
		if (annualSpend >= 10000) {
			return "Platinum";
		} else if (annualSpend >= 5000) {
			return "Gold";
		} else {
			return "Silver";
		}
	}

	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}
}
