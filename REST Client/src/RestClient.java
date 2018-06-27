import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient
{

	public static void main(String[] args) throws IOException
	{
		RestTemplate template = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		List<MediaType> acceptableMediaTypes = new ArrayList<>();
		acceptableMediaTypes.add(MediaType.IMAGE_JPEG);
		acceptableMediaTypes.add(MediaType.APPLICATION_XML);
		headers.setAccept(acceptableMediaTypes);
		
		// Add a new customer
		CustomerClientVersion customer = new CustomerClientVersion();
		customer.setCompanyName("Symbol-LIS");
		customer.setNotes("Even more notes");
		
//		customer = template.postForObject("http://localhost:8080/mywebapp/customers", customer, CustomerClientVersion.class);
		ResponseEntity<CustomerClientVersion> customerEntity = template.postForEntity("http://localhost:8080/mywebapp/customers", customer, CustomerClientVersion.class);
		customer = customerEntity.getBody();
		System.out.println(customerEntity.getStatusCode());
		
		System.out.println("The new customer has been given an id of " + customer.getCustomerId());
		
		
		// Update customer
		customer.setCompanyName("Red Prarie");
		
		template.put("http://localhost:8080/mywebapp/customer/" + customer.getCustomerId(), customer);
	
		
		
		HttpEntity requestEntity = new HttpEntity(headers);
		
//		try
//		{
//			HttpEntity<String> response = template.exchange("http://localhost:8080/mywebapp/customer/1000296516958",
//					HttpMethod.GET, requestEntity, String.class);
//			System.out.println(response);
//		} 
//		catch (HttpClientErrorException e)
//		{
//			if (e.getStatusCode() == HttpStatus.NOT_FOUND)
//			{
//				System.out.println("Sorry, the customer was not found " + e.getResponseBodyAsString());
//			}
//			else 
//			{
//				System.out.println("Some other error occured" + e.getStatusCode());				
//			}
//		}
		
		HttpEntity<CustomerCollectionRepresentation> response = template.exchange("http://localhost:8080/mywebapp/customers",
														HttpMethod.GET, requestEntity, CustomerCollectionRepresentation.class);
		CustomerCollectionRepresentation results = response.getBody();
		
		System.out.println(results);
	}

}
