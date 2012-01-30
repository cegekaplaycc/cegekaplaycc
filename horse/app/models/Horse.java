package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Horse extends Model {

	@Id
	public Long id;

	@Required
	public String name;
	
}
