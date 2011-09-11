package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Course extends Model{

	
	@Required
	public String name;
	
	@Required
	public Date startDate;
	
	@Required
	public Date endDate;
	
	
	@Required
	@Lob
	@MaxSize(10000)
	public String description;
	
	@Required
	public int maxNumberOfParticipants;
	
	@Required
	public int price;
	public int earlyBird;
	
	@OneToMany(mappedBy="course", cascade=CascadeType.ALL)
	public List<Participant> participants;

	@ManyToOne
	public Instructor instructor;
	
	@ManyToOne
	public Location location;
	
	public Date earlyBirdEndDate;
	
	public Course addParticipant(Participant participant) {
		participant.course = this;
		participant.save();
		participants.add(participant);
		return this;
	}

	public Course addParticipant(String name2, String email,
			String telephoneNumber, String company, String address,
			String organizationNumber) {
		Participant participant = new Participant(this, name2, email, telephoneNumber, company, address, organizationNumber);
		participant.save();
		participants.add(participant);
		return this;
	}

	public boolean isNotFull() {
		return participants.size() < maxNumberOfParticipants;
	}
	
	public boolean isFull() {
		return participants.size() >= maxNumberOfParticipants;
	}
	
	public Long getId(){
		return id;
	}
	
	public String getTemplate(){
		return "#{kanbantraining /}";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((instructor == null) ? 0 : instructor.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}
	
	public String type(){
		if (isSeminar()){
			return "Seminar";
		}else{
			return "Course";
		}
	}
	
	public String price(){
		if (price > 0)
			return price + " NOK (+ VAT) ";
		else
			return "FREE";
	}
	public String time(){
		if (isSeminar()){
			return "0830 - 1200";
		}else{
			return "0900 - 1700";
		}
	}
	
	public Date endDate(){
		if (endDate.after(startDate)){
			return endDate;
		}else{
			return null;
		}
	}

	public String additionalInfo(){
		if (isSeminar()){
			return "Breakfast from 0830 - 0900";
		}else{
			if (seatsLeft() < 1){
				return "Sorry. The course is full";
			}
			if (seatsLeft() == 1){
				return "Only 1 seat left!";
			}
			if (seatsLeft() <= 5){
				return "Only " + seatsLeft() + " seats left!";
			}else{
				return null;
			}
		}
	}
	private boolean isSeminar() {
		return name.startsWith("SHIFT-ALT-CTRL Seminar");
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (instructor == null) {
			if (other.instructor != null)
				return false;
		} else if (!instructor.equals(other.instructor))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	public int seatsLeft() {
		return maxNumberOfParticipants - participants.size();
	}

}
