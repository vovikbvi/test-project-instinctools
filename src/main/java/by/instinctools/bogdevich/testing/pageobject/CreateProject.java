package by.instinctools.bogdevich.testing.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import by.instinctools.bogdevich.testing.utils.TestDataProject;

public class CreateProject {
	//private WebDriver driver = AppManager.DRIVER;
	public static CreateProject createProject;

	public static CreateProject getInstance() {
		if (createProject == null) {
			createProject = new CreateProject();
		}
		return createProject;
	}

	private CreateProject() {
	}

	public CreateProject clickProjectLinc() {
		AppManager.DRIVER.findElement(By.linkText("Projects")).click();
		return this;
	}

	public CreateProject enterProjectName(TestDataProject testDataProject) {
		AppManager.DRIVER.findElement(By.id("project_name")).clear();
		AppManager.DRIVER.findElement(By.id("project_name")).sendKeys(testDataProject.getProjectName());
		return this;
	}

	public CreateProject enterProjecDescription(TestDataProject testDataProject) {
		AppManager.DRIVER.findElement(By.id("project_description")).clear();
		AppManager.DRIVER.findElement(By.id("project_description")).sendKeys(testDataProject.getProjectDescription());
		return this;
	}

	public CreateProject enterProjectContext(TestDataProject testDataProject) {
		AppManager.DRIVER.findElement(By.id("project_default_context_name")).clear();
		AppManager.DRIVER.findElement(By.id("project_default_context_name")).sendKeys(testDataProject.getProjectСontext());
		return this;
	}

	public CreateProject enterProjectTags(TestDataProject testDataProject) {
		AppManager.DRIVER.findElement(By.id("project_default_tags")).clear();
		AppManager.DRIVER.findElement(By.id("project_default_tags")).sendKeys(testDataProject.getDefaultTags());
		return this;
	}

	public CreateProject clickGoToProject() {
		AppManager.DRIVER.findElement(By.id("go_to_project")).click();
		return this;
	}

	public CreateProject clickCreateNewProject() {
		AppManager.DRIVER.findElement(By.id("project_new_project_submit")).click();
		return this;
	}

}
