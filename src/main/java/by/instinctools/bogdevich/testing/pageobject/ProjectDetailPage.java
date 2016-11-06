package by.instinctools.bogdevich.testing.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProjectDetailPage {

	public static ProjectDetailPage projectDetailPage;

	public static ProjectDetailPage getInstance() {
		if (projectDetailPage == null) {
			projectDetailPage = new ProjectDetailPage();
		}
		return projectDetailPage;
	}

	private ProjectDetailPage() {
	}

	public String getProjectName() {
		return AppManager.DRIVER.findElement(By.id("project_name")).getText();
	}

	public String getProjectDescription() {
		// xPath: get project description
		return AppManager.DRIVER.findElement(By.xpath(".//*[@class='container project']/div/div/div[2]/p")).getText();
	}

	public String getProjectTags() {
		return AppManager.DRIVER.findElement(By.cssSelector("div.project_settings")).getText();
	}

	public ProjectDetailPage clickEditProject() {
		AppManager.DRIVER.findElement(By.linkText("Edit Project Settings")).click();
		return this;
	}

	public ProjectDetailPage editDefaulText(String addTag) {
		AppManager.DRIVER.findElement(By.id("project_default_tags")).sendKeys(addTag);
		return this;
	}

	public ProjectDetailPage clickSubmitProject() {
		// xPath: click submit button
		AppManager.DRIVER.findElement(By.xpath(".//*[@class='container project']/div/div[2]/form/div[3]/div/button"))
				.click();
		return this;
	}

	public String getActionDescription() {
		// xPath: get actions' description name
		return AppManager.DRIVER
				.findElement(By.xpath(".//*[@id='deferred_pending_container_items']/div[2]/div/div/span")).getText();
	}

	public ProjectDetailPage clickShowNotes() {
		// xPath: click Show notes icon
		AppManager.DRIVER.findElement(By.xpath(".//*[@id='deferred_pending_container_items']/div[2]/div/div/a[3]"))
				.click();
		return this;
	}

	public String getNotesText() {
		// xPath: get notes text
		return AppManager.DRIVER
				.findElement(By.xpath(".//*[@id='deferred_pending_container_items']/div[2]/div/div/div/p")).getText();
	}

	public ProjectDetailPage clickCheckboxMarkComplite() {
		// xPath: set checkbox in Deferred/pending actions in this project
		AppManager.DRIVER.findElement(By.xpath(".//*[@id='deferred_pending_container_items']/div[2]/div/input"))
				.click();
		return this;
	}

	public String getNameCompliteAction() {
		// xPath: action in "Completed actions in this project";
		return AppManager.DRIVER.findElement(By.xpath(".//*[@id='completed_container_items']/div/div/div/span[2]"))
				.getText();
	}
}
