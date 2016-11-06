package by.instinctools.bogdevich.testing.pageobject;

import org.openqa.selenium.By;

public class MainPage {

	private static MainPage mainPage;

	public static MainPage getInstance(){
		if (mainPage == null){
			mainPage = new MainPage();
		}
		return mainPage;
	}
	
	private MainPage (){
	}
	
	public String getTitle(){
		return AppManager.DRIVER.getTitle();
	}
	
	public String getFeedbackPanel(){
		return AppManager.DRIVER.findElement(By.id("flash")).getText();
	}
	
	public void clickLogout(){
		AppManager.DRIVER.findElement(By.linkText("Logout (tracks) »")).click(); 	}
}
