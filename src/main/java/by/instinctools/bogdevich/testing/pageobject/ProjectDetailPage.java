package by.instinctools.bogdevich.testing.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProjectDetailPage {

	//private WebDriver driver = AppManager.DRIVER;
	public static ProjectDetailPage projectDetailPage;

	public static ProjectDetailPage getInstance() {
		if (projectDetailPage == null) {
			projectDetailPage = new ProjectDetailPage();
		}
		return projectDetailPage;
	}

	private ProjectDetailPage() {
	}
	
	public String getTitle(){
		return AppManager.DRIVER.getTitle();
	}
	
	public String getProjectName (){
		return AppManager.DRIVER.findElement(By.id("project_name")).getText();
	}
	
	public String getProjectDescription (){
		// xPath: get project description
		return AppManager.DRIVER.findElement(By.xpath(".//*[@class='container project']/div/div/div[2]/p")).getText();
	}
	
	public String getProjectTags(){
		return AppManager.DRIVER.findElement(By.cssSelector("div.project_settings")).getText();
	}
}
