package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Book extends Model{

	public String imageUrl;
	
	public String title;
	
	public String externalUrl;
	
	@ManyToOne
	public Instructor author;
	
}
