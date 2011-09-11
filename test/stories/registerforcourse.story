package stories

import controllers.Application;
import play.test.Fixtures;
import models.Course;
import models.Participant;

scenario "register for course",{
	
	before "bootstrap",{
		Fixtures.load "initialdata.yml"
	}	
	
	given "course has one registered participant",{
		course = new Course("Kanban", new Date(), new Date())
		course.save();
		Participant participant = new Participant("Ketil Jensen", "Miles", "ketil@miles.no")
		course.addParticipant participant
	}
	
	when "a person registers for a course", {
		Application.registration(course,new Participant("Ola Nordmann", "Acme", "ola@acme.no"))
	}
	
	then "number of participants should be 2", {
		Course.findAll().size()
	}
}