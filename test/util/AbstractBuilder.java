package util;

import play.db.jpa.Model;

public abstract class AbstractBuilder<T extends Model> {

	public abstract T build();

	public T save() {
		return build().save();
	}


}
