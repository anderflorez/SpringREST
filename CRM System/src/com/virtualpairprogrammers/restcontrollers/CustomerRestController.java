package com.virtualpairprogrammers.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.virtualpairprogrammers.domain.Customer;
import com.virtualpairprogrammers.services.customers.CustomerManagementService;
import com.virtualpairprogrammers.services.customers.CustomerNotFoundException;

@Controller
public class CustomerRestController
{
	@Autowired
	private CustomerManagementService customerService;
	
	// we want to support get requests to /customer/[customerId]
	@RequestMapping("/customer/{id}")
	public Customer findCustomerById(@PathVariable String id)
	{
		Customer foundCustomer;
		try 
		{
			foundCustomer = customerService.getFullCustomerDetail(id);
		} 
		catch (CustomerNotFoundException e)
		{
			// TODO - Improve this
			throw new RuntimeException(e);
		}
		return foundCustomer;
	}
}
