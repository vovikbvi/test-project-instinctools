package by.instinctools.bogdevich.testing.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	//private static WebDriver driver;
	public static LoginPage loginPage;

	public static LoginPage getInstance() {
		if (loginPage == null) {
			loginPage = new LoginPage();
		}
		return loginPage;
	}

	private LoginPage() {
	}

	public LoginPage enterLogin(String userName) {
		AppManager.DRIVER.findElement(By.id("user_login")).clear();
		AppManager.DRIVER.findElement(By.id("user_login")).sendKeys(userName);
		return this;
	}

	public LoginPage enterPssword(String userPassword) {
		AppManager.DRIVER.findElement(By.id("user_password")).clear();
		AppManager.DRIVER.findElement(By.id("user_password")).sendKeys(userPassword);
		return this;
	}

	public LoginPage loginClick() {
		AppManager.DRIVER.findElement(By.name("login")).click();
		return this;

	}
}
