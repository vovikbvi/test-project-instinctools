package by.instinctools.bogdevich.testing.projects;

import by.instinctools.bogdevich.testing.BaseTest;
import by.instinctools.bogdevich.testing.utils.RandomValue;

import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProjectsEditDeletTest extends BaseTest {

	@BeforeMethod
	private void prerequisite() {
		
		if (driver.getTitle().equals("TRACKS::Login")) {
			logIn(login, password);
		}
		if (driver.getTitle() != "TRACKS::List Projects") {
			driver.navigate().to(baseURL+"projects/");
		}
		if (getCountProject() < 1) {
			createProject();
		}
	}

	// test PR6
	@Test
	public void testEditDefaulTags() throws Exception {

		String addTag = ", add_tag-" + RandomValue.INSTANCE.generateValue();
		
		driver.findElement(By.xpath(".//*[@id='list-active-projects']/div/div[1]/div/span/a")).click(); //xPath: click first project
		driver.findElement(By.linkText("Edit Project Settings")).click();
		driver.findElement(By.id("project_default_tags")).sendKeys(addTag);
		driver.findElement(By.xpath(".//*[@class='container project']/div/div[2]/form/div[3]/div/button")).click();  //xPath: click submit button

		driver.navigate().refresh();
		String strActual = driver.findElement(By.cssSelector("div.project_settings")).getText();
		String strExpected = addTag;
		assertTrue(strActual.contains(strExpected));
	}

	// test PR7
	@Test
	public void testEditTagsAndSaveMessage() {

		String addTag = ", add_tag-" + RandomValue.INSTANCE.generateValue();
		
		driver.findElement(By.xpath(".//*[@id='list-active-projects']/div/div[1]/div/span/a")).click();//click first project
		driver.findElement(By.linkText("Edit Project Settings")).click();
		driver.findElement(By.id("project_default_tags")).sendKeys(addTag);
		driver.findElement(By.xpath("html/body/div[2]/div[1]/div[2]/div/div[2]/form/div[3]/div/button")).click();//click update button 
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		;

		String expectedString = driver.findElement(By.id("flash")).getText();

		assertEquals(expectedString, "Project saved");

	}

	// test PR8
	@Test
	public void testDeleteProject() {
		String nameProject = driver.findElement(By.xpath(".//*[@id='list-active-projects']/div/div[1]/div/span/a")) 
				.getText();  // xPath get name first project
		driver.findElement(By.xpath(".//*[@id='list-active-projects']/div/div[1]/a[1]")).click(); //xPath Click delete icon
		driver.switchTo().alert().accept();
		assertTrue(driver.findElement(By.linkText(nameProject)).isDisplayed());
	}

	// test PR9
	@Test
	public void testMessageDeleteProject() {
		String nameProject = driver.findElement(By.xpath(".//*[@id='list-active-projects']/div/div[1]/div/span/a")) 
				.getText(); // xPath get name first project
		driver.findElement(By.xpath(".//*[@id='list-active-projects']/div/div[1]/a[1]")).click(); //xPath Click delete icon
		driver.switchTo().alert().accept();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

		assertEquals(driver.findElement(By.id("flash")).getText(), String.format("Deleted project '%s'", nameProject));
	}

}