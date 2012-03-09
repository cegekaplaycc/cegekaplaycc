package models;

import static junit.framework.Assert.assertTrue;
import play.db.jpa.Model;


public abstract class AbstractTestBuilder<MODEL extends Model> {

	protected abstract MODEL buildObject();

	public MODEL build() {
		return buildObject();
	}

	public MODEL buildAndPersist() {
		MODEL model = buildObject();
		
		assertTrue("Model " + model + " is not valid", model.validateAndSave());
		
		return model;
	}

}
