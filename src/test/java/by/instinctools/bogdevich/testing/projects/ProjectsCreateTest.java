package by.instinctools.bogdevich.testing.projects;

import by.instinctools.bogdevich.testing.BaseTest;
import by.instinctools.bogdevich.testing.utils.TestDataProject;

import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProjectsCreateTest extends BaseTest {

	@BeforeMethod
	private void prerequisite() {

		if (driver.getTitle().equals("TRACKS::Login")) {
			logIn(login, password);
		}
		if (driver.getTitle() != "TRACKS::List Projects") {
			driver.navigate().to(baseURL+"projects/");
		}
	}

	// test PR1
	@Test
	public void testOpenPageAfterCreate() throws Exception {
		TestDataProject testDataProject = createProject();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(driver.getTitle(), String.format("TRACKS::Project: %s", testDataProject.getProjectName()));
	}

	// test PR2
	@Test
	public void testNameCreateProjectInTable() throws Exception {
		TestDataProject testDataProject = createProject();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		;

		assertEquals(driver.findElement(By.id("project_name")).getText(), testDataProject.getProjectName());
	}

	// test PR3
	@Test
	public void testDescriptionCreateProject() throws Exception {

		TestDataProject testDataProject = createProject();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		;

		assertEquals(driver.findElement(By.xpath(".//*[@class='container project']/div/div/div[2]/p")).getText(),
				testDataProject.getProjectDescription()); // xPath: get project description
															 
	}

	// test PR4
	@Test
	public void testContextCreateProject() throws Exception {
		createProject();
	}

	// test PR5
	@Test
	public void testTegsCreatingProject() throws Exception {

		TestDataProject testDataProject = createProject();

		String strActual = driver.findElement(By.cssSelector("div.project_settings")).getText();
		String strExpected = String.format("with '%s' as the default tags.", testDataProject.getDefaultTags());
		assertTrue(strActual.contains(strExpected));

	}

}
