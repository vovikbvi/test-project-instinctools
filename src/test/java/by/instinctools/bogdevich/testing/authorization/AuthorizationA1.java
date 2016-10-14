package by.instinctools.bogdevich.testing.authorization;

import static org.testng.Assert.*;

import org.testng.annotations.*;
import by.instinctools.bogdevich.testing.BaseTest;
import org.openqa.selenium.*;

public class AuthorizationA1 extends BaseTest {

	@Test
	public void testAuthorizationFalse() {
		logIn("trckss", "111111");
		assertEquals(driver.getTitle(), "TRACKS::Login");

		assertEquals(driver.findElement(By.id("flash")).getText(), "Неудачная попытка входа.");
	}

}
