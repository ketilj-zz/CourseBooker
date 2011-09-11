package controllers;

import models.Course;
import models.Participant;
import play.mvc.Controller;

public class Interest extends Controller{

	public static void confirmation(String name){
		Course course = Course.find("byName",name).first();
		render(course);
	}
	
	public static void register(String courseName, String name,
			String email, String companyName) {
		Course course = Course.find("byName", courseName).first();
		Participant participant = new Participant(name, email, "123456789","", companyName, "");
		
		course.addParticipant(participant);
		flash.success("Thank you for registering for the Kanban Training Class.");
		confirmation(courseName);
	}
}
