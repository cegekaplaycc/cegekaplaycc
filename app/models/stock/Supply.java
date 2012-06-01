package models.stock;

import javax.persistence.Entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import play.db.jpa.Model;

@Entity
public class Supply extends Model {

	public String name;
	public long price;
	
	public Supply() {
		super();
	}
	
	public Supply(String name, long price) {
		this.name = name;
		this.price = price;
	}

	@Override
	public boolean equals(Object other) {
		return new EqualsBuilder()
			.append(this.name, ((Supply)other).name)
			.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(name)
			.hashCode();
	}

	
}
