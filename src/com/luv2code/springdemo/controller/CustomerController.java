package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// need to inject out customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		List<Customer> theCustomer = customerService.getCustomers();
		theModel.addAttribute("customers",theCustomer);
		
		
		return "list-customers";
		
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer ) {
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		Customer theCustomer = customerService.getCustomer(theId);
		
		theModel.addAttribute("customer", theCustomer);
		
			
		return "customer-form" ;
		
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {
		customerService.delete(theId);
		

		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName,Model theModel) {
		
		List<Customer> theCustomersList = customerService.searchByFirstAndLastName(theSearchName);
		theModel.addAttribute("customers",theCustomersList);
		
		return "list-customers";
	}
}
