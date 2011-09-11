import org.junit.*;
import org.junit.Before;

import controllers.Application;

import play.test.*;
import play.db.jpa.GenericModel.JPAQuery;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class ApplicationTest extends FunctionalTest {

	@Before
	public void doBeforeTests(){
		Fixtures.load("initialdata.yml");
	}
	
    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
    
    @Test
    public void testRegisterParticipant(){
    	
    }
    	
}