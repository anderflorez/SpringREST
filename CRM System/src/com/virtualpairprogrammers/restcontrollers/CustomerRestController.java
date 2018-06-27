package com.virtualpairprogrammers.restcontrollers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.virtualpairprogrammers.domain.Customer;
import com.virtualpairprogrammers.services.customers.CustomerManagementService;
import com.virtualpairprogrammers.services.customers.CustomerNotFoundException;

@RestController
public class CustomerRestController
{
	@Autowired
	private CustomerManagementService customerService;
	
	//@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="The customer wasn't found.")
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ClientErrorInformation> rulesForCustomerNotFound(HttpServletRequest req, Exception e) 
	{
		//Return a representation of the error
		ClientErrorInformation error = new ClientErrorInformation(e.toString(), req.getRequestURI());
		return new ResponseEntity<ClientErrorInformation>(error, HttpStatus.NOT_FOUND);
	}
	
	// we want to support get requests to /customer/[customerId]
	// as httpmessageconverter will automatically support multiple formats there is no need to state the accept headers
	@RequestMapping(value="/customer/{id}", method=RequestMethod.GET /* headers={"Accept=application/xml, application/json"}*/)
	// @ResponseBody		// Not required if using @RestController on the class
	public Customer findCustomerById(@PathVariable String id) throws CustomerNotFoundException
	{
		return customerService.getFullCustomerDetail(id);
	}
	
	@RequestMapping(value="/customers", method=RequestMethod.GET)
	public CustomerCollectionRepresentation returnAllCustomers(@RequestParam(required=false) Integer first, 
															   @RequestParam(required=false) Integer last)
	{
		System.out.println("First is " + first + " and Last is " + last);
		
		List<Customer> allCustomers = customerService.getAllCustomers();
		for (Customer next : allCustomers)
		{
			next.setCalls(null);
		}
		
		if (first != null && last != null)
		{
			return new CustomerCollectionRepresentation(allCustomers.subList(first-1, last));  
		}
		else
		{
			return new CustomerCollectionRepresentation(allCustomers);			
		}
		
	}
	
	@RequestMapping(value="/customers", method=RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public Customer createNewCustomer(@RequestBody Customer newCustomer)
	{
		return customerService.newCustomer(newCustomer);
	}
	
	@RequestMapping(value="/customer/{id}", method=RequestMethod.DELETE)
	public void deleteById(@PathVariable String id) throws CustomerNotFoundException
	{
		Customer oldCustomer = customerService.findCustomerById(id);
		customerService.deleteCustomer(oldCustomer);
	}
	
	@RequestMapping(value="/customer/{id}", method=RequestMethod.PUT)
	public void updateCustoemr(@RequestBody Customer customerToUpdate) throws CustomerNotFoundException
	{
		customerService.updateCustomer(customerToUpdate);
	}
}
