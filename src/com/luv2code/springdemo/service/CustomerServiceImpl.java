package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	// need to inject customer dao
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		customerDAO.saveCustomer(customer);
		
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		
		//first method
		//customerDAO.getCustomers().stream().filter(i->Integer.valueOf(i.getId()).equals(theId)).findFirst().orElse(null);
		return customerDAO.getCustomer(theId);
	}

	@Override
	@Transactional
	public void delete(int theId) {
		customerDAO.delete(theId);
	}

	@Override
	@Transactional
	public List<Customer> searchByFirstAndLastName(String theSearchName) {
		
		return customerDAO.searchByFirstAndLastName(theSearchName);
	}

}
