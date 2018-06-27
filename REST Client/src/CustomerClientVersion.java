import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="customer")
public class CustomerClientVersion
{
	//@XmlElement(name="companyName")
	private String companyName;
	private String customerId;
	private String notes;
	private String email;
	private String telephone;
	private List<CallClientVersion> calls;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getName() {
		return companyName;
	}
	public void setName(String name) {
		this.companyName = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public List<CallClientVersion> getCalls() {
		return calls;
	}
	public void setCalls(List<CallClientVersion> calls) {
		this.calls = calls;
	}
	public String toString()
	{
		return this.customerId + " " + this.getCompanyName();
	}
	
}
