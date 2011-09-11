package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Presentation extends Model{

	public String url;
	public String name;
	public String location;
	public String event;
//	
//	public String where;
//	
	@ManyToOne
	public Instructor presenter;
}
