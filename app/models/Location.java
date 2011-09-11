package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import play.data.validation.MaxSize;
import play.db.jpa.Model;

@Entity
public class Location extends Model{

	public String name;
	public String telephoneNumber;
	public String address;
	public String postal;
	@Lob
	@MaxSize(1000)
	public String map;
	public String url;
	@Lob
	@MaxSize(1000)
	public String mapUrl;

	@OneToMany(mappedBy="location", cascade=CascadeType.ALL)
	public List<Course> courses;
}
