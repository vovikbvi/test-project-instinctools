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

		programSleep(1);

		String actual = driver.getTitle();
		String expected = String.format("TRACKS::Project: %s", testDataProject.getProjectName());
		assertEquals(actual, expected);
	}

	// test PR2
	@Test
	public void testNameCreateProjectInTable() throws Exception {
		TestDataProject testDataProject = createProject();

		programSleep(1);
		

		String actual = driver.findElement(By.id("project_name")).getText();
		String expected = testDataProject.getProjectName();
		assertEquals(actual, expected);
	}

	// test PR3
	@Test
	public void testDescriptionCreateProject() throws Exception {

		TestDataProject testDataProject = createProject();

		programSleep(1);

		// xPath: get project description
		String actual = driver.findElement(By.xpath(".//*[@class='container project']/div/div/div[2]/p")).getText();
		String expected = testDataProject.getProjectDescription();
		
		assertEquals(actual, expected); 
															 
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
	
	public void programSleep(int counSseconds) {
		try {
			TimeUnit.SECONDS.sleep(counSseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
