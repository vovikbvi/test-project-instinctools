package by.instinctools.bogdevich.testing.authorization;

import org.testng.annotations.*;

import by.instinctools.bogdevich.testing.BaseTest;

import static org.testng.Assert.*;

import org.openqa.selenium.*;

public class AuthorizationA3 extends BaseTest {

	@Test
	public void testLogOut() {
		logIn(login, password);

		// logout
		assertEquals(driver.getTitle(), "TRACKS::List tasks");
		driver.findElement(By.linkText("Logout (tracks) »")).click();
		assertEquals(driver.getTitle(), "TRACKS::Login");

		assertEquals(driver.findElement(By.id("flash")).getText(), "You have been logged out of Tracks.");
	}

}
