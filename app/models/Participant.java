package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Email;
import play.data.validation.Min;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.db.jpa.Model;

@Entity
public class Participant extends Model{

	

	@Required(message = "Navn er påkrevd")
	public String name;
	
	@Email(message="Vennligst oppgi en gyldig mail adresse")
	@Required 
	public String email;
	
	@Required(message="Vennligst oppgi telefonnummer")
	@Min(8)
	public String telephonenumber;
	
	public boolean paymentReceived;
	
	public boolean billed;
	
	public String company;
	
	@Required(message="Vennligst oppgi adressen som fakturaen skal sendes til")
	public String address;
	
	public String orgNumber;
	
	@ManyToOne
	public Course course;
	
	public Participant(Course course,String name, String email, String telephonenumber, String company, String address, String orgNumber) {
		super();
		this.course = course;
		this.name = name;
		this.email = email;
		this.telephonenumber = telephonenumber;
		this.address = address;
		this.company = company;
		this.orgNumber = orgNumber;
	}
	

	public Participant(String name, String email, String telephonenumber,
			String company, String address, String organizationNumber) {
		this.name = name;
		this.email = email;
		this.telephonenumber = telephonenumber;
		this.address = address;
		this.company = company;
		this.orgNumber = organizationNumber;
	}


	@Override
	public String toString() {
		return "Participant [address=" + address + ", billed=" + billed
				+ ", company=" + company + ", course=" + course + ", email="
				+ email + ", name=" + name + ", orgNumber=" + orgNumber
				+ ", paymentReceived=" + paymentReceived + ", telephonenumber="
				+ telephonenumber + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((orgNumber == null) ? 0 : orgNumber.hashCode());
		result = prime * result
				+ ((telephonenumber == null) ? 0 : telephonenumber.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participant other = (Participant) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (telephonenumber == null) {
			if (other.telephonenumber != null)
				return false;
		} else if (!telephonenumber.equals(other.telephonenumber))
			return false;
		return true;
	}
}
