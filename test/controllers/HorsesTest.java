package controllers;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import models.Horse;

import org.fest.assertions.Assertions;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;
import play.test.UnitTest;

public class HorsesTest extends FunctionalTest {

	@Test
	public void blank() {
		Response response = GET("/horse");
		
		assertIsOk(response);
		
		Assertions.assertThat(Horse.count()).isEqualTo(0);
		Horses.create("testHorse");
		Assertions.assertThat(Horse.count()).isEqualTo(1);
	}

}
