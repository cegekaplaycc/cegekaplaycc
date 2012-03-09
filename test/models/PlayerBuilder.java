package models;

import java.util.Set;

import org.powermock.reflect.Whitebox;

import com.google.common.collect.Sets;

public class PlayerBuilder {

	private Set<Horse> horses = Sets.newHashSet();

	public Player build() {
		Player player = new Player("joske");
		Whitebox.setInternalState(player, "horses", horses);
		return player;
	}

	public PlayerBuilder withHorses(Horse... horses) {
		this.horses = Sets.newHashSet(horses);
		return this;
	}

}
