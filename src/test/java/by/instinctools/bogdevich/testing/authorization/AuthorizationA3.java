package by.instinctools.bogdevich.testing.authorization;

import org.testng.annotations.*;

import by.instinctools.bogdevich.testing.BaseTest;
import by.instinctools.bogdevich.testing.pageobject.MainPage;

import static org.testng.Assert.*;

import org.openqa.selenium.*;

public class AuthorizationA3 extends BaseTest {

	@Test
	public void testLogOut() {
		logIn(login, password);

		// logout
		assertEquals(MainPage.getInstance().getTitle(), "TRACKS::List tasks");
		MainPage.getInstance().clickLogout();
		
		assertEquals(MainPage.getInstance().getTitle(), "TRACKS::Login");

		assertEquals(MainPage.getInstance().getFeedbackPanel(), "You have been logged out of Tracks.");
	}

}
