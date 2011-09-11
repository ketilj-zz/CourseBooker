package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class CourseInterest extends Model{

	public String courseName;
	public String participantName;
	public String email;
	public String telephoneNumber;

	public CourseInterest(String course, String name, String mail,
			String tlf) {
		courseName = course;
		participantName =  name;
		email = mail;
		telephoneNumber = tlf;
	}
}
