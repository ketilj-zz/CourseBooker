package controllers;

import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jamonapi.utils.Logger;

import models.Book;
import models.Course;
import models.CourseInterest;
import models.Instructor;
import models.Location;
import models.MailSender;
import models.Participant;
import models.Presentation;
import models.Site;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.data.validation.Validation;
import play.db.jpa.JPABase;
import play.libs.Mail;
import play.mvc.*;

public class Application extends Controller {

	public static void index() {
		//Course course = getCourse("Kanban Training Class");
		//render(course);
		List<Course> courses = Course.findAll();
		render(courses);
	}

	public static void seminar() {
		Course course = getCourse("SHIFT-ALT-CTRL Seminar");
		render(course);
	}

	private static Course getCourse(String courseName) {
		Course course = Course.find("byName", courseName).first();
		return course;
	}

	public static void resources() {
		List<Book> books = Book.findAll();
		List<Presentation> presentations = Presentation.findAll();
		List<Site> sites = Site.findAll();
		render(books, presentations, sites);
	}

	public static void instructor(long id) {
		Instructor instructor = Instructor.find("byId", id).first();
		render(instructor);
	}

	public static void register() {
		render();
	}

	public static void confirmation(
			String courseName,
			@Required(message = "Please specify your name") String name,
			@Required(message = "Please specify your email address") String email,
			String telephonenumber,
			@Required(message = "Please specify company") String company,
			@Required(message = "Please specify billing address") String address,
			String orgnumber) {

		if (Validation.hasErrors()) {
			System.out.println(Validation.errors().toString());
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			index();
		}

		Course course = Course.find("byName", courseName).first();

		if (course.isNotFull()) {
			Participant participant = new Participant(name, email,
					telephonenumber, company, address, orgnumber);
			Logger.logInfo(participant + " signed up for " + course.name
					+ " starting " + course.startDate);
			course.addParticipant(participant);
			new MailSender().sendConfirmationMail(participant, course);
			flash.success("Thank you for registering for the Kanban Training Class.");

		} else {
			flash.error("Sorry. This course is fully booked");
			registerInterest(course);
		}
		render(course);
	}

	public static void getPicture(long id) {
		Instructor instructor = Instructor.find("byId", id).first();
		renderBinary(instructor.image.get());
	}

	public static void getInstructorImage(long id) {
		Instructor instructor = Instructor.find("byId", id).first();
		renderBinary(instructor.largeImage.get());
	}

	public static void getTemplate() {
		renderTemplate("kanbantraining.html");

	}

	public static void getLocationMap(long locationId) throws Exception {
		Location location = Location.find("byId", locationId).first();
		URL url = new URL(location.map);
		renderBinary(url.openStream());
	}

	/*
	public static void twitterBuzz() {
		System.out.println("twitterbuzz");
		Twitter twitter = new TwitterFactory().getInstance();
		List<Tweet> tweets = null;
		try {
			QueryResult result = twitter.search(new Query("kanban"));
			tweets = result.getTweets();
			for (Tweet tweet : tweets) {
				System.out.println("@" + tweet.getFromUser() + " - "
						+ tweet.getText());
			}
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
		}
		renderJSON(tweets);
	}
	*/
	private static void registerInterest(Course course) {
		render(course);
	}
	

	public static void coursedetails() {
		render();
	}

}