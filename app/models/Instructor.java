package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import play.data.validation.MaxSize;
import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Instructor extends Model{
	public String name;
	
	@Lob
    @MaxSize(10000)
	public String resume;
	
	@Lob
	@MaxSize(10000)
	public String bio;
	
	public Blob image;
	
	public Blob largeImage;
	
	public String twitterId;
	
	public String website;
	
	@OneToMany(mappedBy="instructor", cascade=CascadeType.ALL)
	public List<Course> courses;
	
	@OneToMany
	public List<Book> books;
	
	@OneToMany
	public List<Presentation> presentations;
}
