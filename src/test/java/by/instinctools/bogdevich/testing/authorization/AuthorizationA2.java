package by.instinctools.bogdevich.testing.authorization;

import org.testng.annotations.*;

import by.instinctools.bogdevich.testing.BaseTest;

import static org.testng.Assert.*;

public class AuthorizationA2 extends BaseTest {

	@Test
	public void testAuthorizationTrue() throws Exception {
		logIn(login, password);

		assertEquals(driver.getTitle(), "TRACKS::List tasks");
	}

}
