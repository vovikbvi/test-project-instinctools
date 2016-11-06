package by.instinctools.bogdevich.testing.authorization;

import static org.testng.Assert.*;

import org.testng.annotations.*;
import by.instinctools.bogdevich.testing.BaseTest;
import by.instinctools.bogdevich.testing.pageobject.MainPage;

import org.openqa.selenium.*;

public class AuthorizationA1 extends BaseTest {

	@Test
	public void testAuthorizationFalse() {
		logIn("trckss", "111111");
		assertEquals(MainPage.getInstance().getTitle(), "TRACKS::Login");

		assertEquals(MainPage.getInstance().getFeedbackPanel(), "Неудачная попытка входа.");
	}

}
