package com.crm.CRM.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.CRM.entity.Customer;
import com.crm.CRM.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class Controller {
	@Autowired
	private final CustomerService customerService;

	@GetMapping("/crm")
	@Operation(summary = "Welcome message", description = "Returns a welcome message for the CRM project.")
	@ApiResponse(responseCode = "200", description = "Success")
	public String getMethodName() {
		return "Welcome To The Customer Releationship Manahemnt Project";
	}

	public Controller(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("/customer")
	@Operation(summary = "Create a new customer", description = "Creates a new customer in the CRM system.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Customer created successfully"),
			@ApiResponse(responseCode = "400", description = "Bad request") })
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		Customer savedCustomer = customerService.createCustomer(customer);
		return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
	}

	@GetMapping("/customer/{id}")
	@Operation(summary = "Get customer by ID", description = "Fetches a customer by their ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Customer found"),
	@ApiResponse(responseCode = "404", description = "Customer not found") })
	public ResponseEntity<Customer> getCustomerById(@PathVariable UUID id) {
		Optional<Customer> customer = customerService.getCustomerById(id);
		return customer.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/customers")
	@Operation(summary = "Get customers by name or email", description = "Fetch customers based on their name or email.")
	@ApiResponse(responseCode = "200", description = "Customers found")
	public ResponseEntity<List<Customer>> getCustomersByNameOrEmail(@RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
		if (name != null) {
			return new ResponseEntity<>(customerService.getCustomerByName(name), HttpStatus.OK);
		} else if (email != null) {
			return new ResponseEntity<>(customerService.getCustomerByEmail(email), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/customer/{id}")
	@Operation(summary = "Update an existing customer", description = "Updates customer information based on ID.")
	@ApiResponse(responseCode = "200", description = "Customer updated successfully")
	public ResponseEntity<Customer> updateCustomer(@PathVariable UUID id, @RequestBody Customer updatedCustomer) {
		Customer updated = customerService.updateCustomer(id, updatedCustomer);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/customer/{id}")
	@Operation(summary = "Delete a customer", description = "Deletes a customer by their ID.")
	@ApiResponse(responseCode = "204", description = "Customer deleted successfully")
	public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
		customerService.deleteCustomer(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/customer/all")
	@Operation(summary = "Get all customers", description = "Fetches a list of all customers.")
	@ApiResponse(responseCode = "200", description = "Customers retrieved successfully")
	public ResponseEntity<List<Customer>> getAllCustomer() {
		List<Customer> customer = customerService.getAllCustomer();

		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

}
