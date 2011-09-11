import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class CourseInterestTest extends UnitTest {

	@After
	public void clear(){
		CourseInterest.deleteAll();
	}
	
	@Test
	public void whenSavingACourseInterestOneShouldBeAbleToRetrieveIt(){
		String courseName = "Kanban Coaching Camp";
		String participantName = "Ola Dunk";
		CourseInterest interest = new CourseInterest(courseName, participantName, "ola@fakemail.com", "99999999").save();
		
		List<CourseInterest> interestList = CourseInterest.find("byParticipantName", participantName).fetch();
		assertEquals(1, interestList.size());
	}
}

