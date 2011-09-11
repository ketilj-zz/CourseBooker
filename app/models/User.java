package models;

import javax.persistence.Entity;

import play.data.validation.Email;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model {

	private static final long serialVersionUID = -805902496216834580L;
	@Override
	public String toString() {
		return "User [email=" + email + "]";
	}

	@Email
	@Required
	public String email;
	
	@Required
	@Password
	public String password;
	public String fullName;
	public boolean isAdmin;
	
	public User(String email, String password, String fullName) {
		super();
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}

	public static User connect(String email, String password) {
		return find("byEmailAndPassword", email, password).first();
	}
	
}
