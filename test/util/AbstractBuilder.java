package util;

import play.db.jpa.Model;

public abstract class AbstractBuilder<T extends Model> {

	public abstract T build();

	public T persist() {
		return build().save();
	}

}
