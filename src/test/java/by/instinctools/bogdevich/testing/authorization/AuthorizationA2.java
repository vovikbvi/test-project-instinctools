package by.instinctools.bogdevich.testing.authorization;

import org.testng.annotations.*;

import by.instinctools.bogdevich.testing.BaseTest;
import by.instinctools.bogdevich.testing.pageobject.MainPage;

import static org.testng.Assert.*;

public class AuthorizationA2 extends BaseTest {

	@Test
	public void testAuthorizationTrue() throws Exception {
		logIn(login, password);

		assertEquals(MainPage.getInstance().getTitle(), "TRACKS::List tasks");
	}

}
