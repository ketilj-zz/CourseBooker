package models;

import java.net.URL;
import java.text.SimpleDateFormat;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import play.Play;
import play.libs.Mail;
import play.mvc.Mailer;

public class MailSender extends Mail{

	public void sendConfirmationMail(Participant participant, Course course){
		try{
			SimpleEmail email = makeSimpleMail();
			email.setSubject("Confirmation " + course.name);
			email.setMsg(makeMailMessage(participant,course));
			email.addTo(participant.email);
			System.out.println(Play.configuration.getProperty("mail.smtp", ""));
			System.out.println(Play.configuration.getProperty("mail.smtp.host", ""));
			Mail.send(email);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
		
	}

	private SimpleEmail makeSimpleMail() throws EmailException {
		SimpleEmail email = new SimpleEmail();
		email.setFrom("kurs@miles.no");
		return email;
	}

	private String makeMailMessage(Participant participant, Course course) {
		StringBuffer message = 
			new StringBuffer("Hi ").append(participant.name).append(",\n\n").
				append("This email confirms that you are registered to ").
				append(course.name).append(" with ").append(course.instructor.name).
				append(", March 28-29 in Oslo. \n\n").
				append("An invoice will be sent to ").append(participant.company).append(" at the following address: \n").
				append(participant.address).append("\n\n").
				append("If you have any questions about the course please feel free to contact us at kurs@miles.no or +47 971 72 278 \n\n").
				append("Best regards, \n").append("Miles Oslo AS");
		return message.toString();
	}

	public void sendSeminarConfirmation(Course course, String name,String emailAddress) {
		try{
			SimpleEmail email = makeSimpleMail();
			email.setCharset(SimpleEmail.ISO_8859_1);
			email.setSubject("Confirmation " + course.name);
			email.setMsg(makeSeminarText(name, course));
			email.addTo(emailAddress);
			System.out.println(Play.configuration.getProperty("mail.smtp", ""));
			System.out.println(Play.configuration.getProperty("mail.smtp.host", ""));
			Mail.send(email);
			
		}catch (Exception e){
			throw new RuntimeException(e);
		}
		
	}
	
	public String makeSeminarText(String name, Course course){
		StringBuffer message = 
			new StringBuffer("Hi ").append(name).append(",\n\n").
				append("This email confirms that you are registered to ").
				append(course.name).append(" with ").append(course.instructor.name).append(" ").
				append(new SimpleDateFormat("EEE, d MMM yyyy").format(course.startDate)  + " \n\n").
				
				append("If you have any questions about this seminar, please feel free to contact us: \n\n").
				append("Arne: arne@miles.no (917 61 456)\n").
				append("Ketil: ketil@miles.no (971 72 278) \n\n").
				append("Best regards, \n").append("Miles Oslo AS");
		return message.toString();
		
	}
	
	 
}
