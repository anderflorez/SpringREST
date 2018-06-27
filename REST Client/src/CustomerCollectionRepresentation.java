
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="customers")
public class CustomerCollectionRepresentation
{
	private List<CustomerClientVersion> customers;

	public CustomerCollectionRepresentation() {}
	
	public CustomerCollectionRepresentation(List<CustomerClientVersion> customers)
	{
		this.customers = customers;
	}

	@XmlElement(name="customer")
	public List<CustomerClientVersion> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerClientVersion> customers) {
		this.customers = customers;
	}
	
	public String toString()
	{
		return "The customers are " + customers;
	}
}
