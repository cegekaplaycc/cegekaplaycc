package models;

import org.fest.assertions.Assert;
import org.fest.assertions.Assertions;
import org.junit.Test;

public class UserIntegrationTest extends IntegrationTestCase {

	@Test
	public void canBePersisted() {
		User user = new User("ID");
		user.save();
		
		Assertions.assertThat(User.findAll()).hasSize(1);
		User refreshed = (User) User.findAll().iterator().next();
		
		Assertions.assertThat(refreshed.getUserId()).isEqualTo("ID");
		Assertions.assertThat(refreshed.getName()).isNullOrEmpty();
	}
}
