import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class CourseTest extends UnitTest {

	private Course course;
	private Participant participant;
	private static final String SMALL_COURSE = "Small Course";
	
	@Before
	public void loadData() {
		Fixtures.load("initialData.yml");
		course = findCourse("Kanban Training Class");
		participant = Participant.find("byName", "Ketil Jensen").first();
	}
	

	@Test
	public void addParticipantShouldAddOneToNumberOfParticipant() {
		int numberOfParticipants = course.participants.size();
		course.addParticipant("Ola", "ola@fakemail.com", "98989898",
				"fakeCompany", "efwefwf", "000000000");
		assertNotNull(Participant.find("byName", "Ola").first());
		assertEquals(numberOfParticipants + 1, course.participants.size());
	}


	@Test
	public void courseIsNotFullIfNumberOfRegisteredLessThanMaxNumberOfParticipants(){
		assertTrue(course.isNotFull());
	}
	
	@Test
	public void courseIsFullWhenNumberOfParticipantsHasReachedMaxNumber(){
		Course smallCourse = findCourse(SMALL_COURSE);
		smallCourse.addParticipant("Ola", "ola@fakemail.com", "98989898",
				"fakeCompany", "efwefwf", "000000000");
		assertFalse(smallCourse.isNotFull());
	}
	
	@Test
	public void shouldReturnBreakfastInformationForSeminar(){
		Course seminar = findCourse("SHIFT-ALT-CTRL Seminar");
		assertTrue(seminar.additionalInfo().contains("Breakfast"));
	}
	
	@Test
	public void shouldReturnNullIfCourseHasMoreThanFiveSeatsLeft(){
		assertNull(course.additionalInfo());
	}

	@Test
	public void shouldReturnNumberOfSeatsLeftIfLessThanSixSeatsLeft(){
		Course smallCourse = findCourse(SMALL_COURSE);
		assertTrue(smallCourse.additionalInfo().contains("Only"));
	}
	
//	
	@Test
	public void shoudlReturnInformationThatCourseIsFullWhenNoSeatsLeft(){
		Course smallCourse = findCourse(SMALL_COURSE);
		smallCourse.addParticipant(participant);
		String info = smallCourse.additionalInfo();
		assertTrue("Exp course to be full, but was: "  + info, info.equals("Sorry. The course is full"));
	}
	
	private Participant makeParticipant() {
		// TODO Auto-generated method stub
		return null;
	}


	private Course findCourse(String name) {
		return Course.find("byName",name).first();
	}

	@After
	public void cleanUp(){
		Participant.deleteAll();
	}
}

