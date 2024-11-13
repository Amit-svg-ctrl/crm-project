package com.crm.CRM.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    
    private String email;
    private double annualSpend;
    private String membershipTier;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getAnnualSpend() {
		return annualSpend;
	}
	public void setAnnualSpend(double annualSpend) {
		this.annualSpend = annualSpend;
	}
	public String getMembershipTier() {
		return membershipTier;
	}
	public void setMembershipTier(String membershipTier) {
		this.membershipTier = membershipTier;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", annualSpend=" + annualSpend
				+ ", membershipTier=" + membershipTier + "]";
	}
	public Customer(UUID id, String name, String email, double annualSpend, String membershipTier) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.annualSpend = annualSpend;
		this.membershipTier = membershipTier;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
