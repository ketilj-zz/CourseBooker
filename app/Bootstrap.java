import controllers.Instructors;
import play.*;
import play.jobs.*;
import play.test.*;
 
import models.*;
 
@OnApplicationStart
public class Bootstrap extends Job {
 
    public void doJob() {
        // Check if the database is empty
        if(User.count() == 0) {
            loadData();
        }else if(Instructor.count() == 0){
        	loadData();
        }
    }

	private void loadData() {
		Fixtures.load("initialdata.yml");
	}
 
}
