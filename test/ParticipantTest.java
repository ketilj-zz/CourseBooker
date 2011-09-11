import models.Course;
import models.Participant;

import org.junit.Test;

import play.test.UnitTest;


public class ParticipantTest extends UnitTest{

	@Test
	public void whenParticipantIsSavedItShouldBePossibleToRetrieveAllDetails(){
		Course course = Course.find("byName", "Small Course").first();
		String name = "Ola Dunk";
		String email = "ola@dunk.com";
		String telephonenumber = "99999999";
		String company = "Dunk & Co";
		String address = "Dunkveien 8";
		String orgNumber = "111111111";
		Participant participant = new Participant(course, name, email, telephonenumber, company, 
				address, orgNumber).save();
		Participant savedParticipant = Participant.find("byName", "Ola Dunk").first();
		assertEquals(participant.name, savedParticipant.name);
	}

}
