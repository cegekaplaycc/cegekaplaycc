package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class User extends Model {

	@Id
	public Long id;

	@Id
	public Long credits;

	@Constraints.Required
	public String name;
	
}
