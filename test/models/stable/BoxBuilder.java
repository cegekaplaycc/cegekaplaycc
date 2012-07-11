package models.stable;

import models.Horse;
import util.AbstractBuilder;

public class BoxBuilder extends AbstractBuilder<Box> {

	private Horse horse;

	private BoxBuilder() {
	}

	@Override
	public Box build() {
		Box box = new Box();
		box.horse = horse;
		return box;
	}

	public static BoxBuilder aBox() {
		return new BoxBuilder();
	}

	public BoxBuilder withHorse(Horse horse) {
		this.horse = horse;
		return this;
	}
}
