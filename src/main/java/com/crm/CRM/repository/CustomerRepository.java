package com.crm.CRM.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.CRM.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findByName(String name);
    List<Customer> findByEmail(String email);
}
