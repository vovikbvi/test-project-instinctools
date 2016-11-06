package by.instinctools.bogdevich.testing.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AppManager {

	public static WebDriver DRIVER;
	public static AppManager app;

	private AppManager() {
		super();
		
	}

	public static AppManager getInstance() {
		if (app == null) {
			app = new AppManager();
		}
		return app;

	}

	public void initApp(String baseURL) {
		DRIVER = new FirefoxDriver();
		DRIVER.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		DRIVER.get(baseURL);

	}

	public void stopApp() {
		DRIVER.quit();
		
	}
}
