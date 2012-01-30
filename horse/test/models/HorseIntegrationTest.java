package models;

import static junit.framework.Assert.assertEquals;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.mchange.util.AssertException;

import play.db.jpa.JPABase;
import play.test.UnitTest;

public class HorseIntegrationTest extends UnitTest {

	@Test
	public void canBePersisted() {
		new Horse("joske").save();
		List<Horse> horses = Horse.findAll();
		
		assertEquals(1, horses.size());
		assertEquals("joske", horses.iterator().next().name);
	}
}
