package by.instinctools.bogdevich.testing.projects;

import by.instinctools.bogdevich.testing.BaseTest;
import by.instinctools.bogdevich.testing.pageobject.AppManager;
import by.instinctools.bogdevich.testing.pageobject.MainPage;
import by.instinctools.bogdevich.testing.pageobject.ProjectDetailPage;
import by.instinctools.bogdevich.testing.utils.TestDataProject;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProjectsCreateTest extends BaseTest {

	@BeforeMethod
	private void prerequisite() {

		if (MainPage.getInstance().getTitle().equals("TRACKS::Login")) {
			logIn(login, password);
		}
		if (MainPage.getInstance().getTitle() != "TRACKS::List Projects") {
			AppManager.DRIVER.navigate().to(baseURL+"projects/");
		}
	}

	// test PR1
	@Test
	public void testOpenPageAfterCreate() throws Exception {
		TestDataProject testDataProject = createProject();

		programSleep(1);

		String actual = MainPage.getInstance().getTitle();
		String expected = String.format("TRACKS::Project: %s", testDataProject.getProjectName());
		assertEquals(actual, expected);
	}

	// test PR2
	@Test
	public void testNameCreateProjectInTable() throws Exception {
		TestDataProject testDataProject = createProject();

		programSleep(1);
		

		String actual = ProjectDetailPage.getInstance().getProjectName();
		String expected = testDataProject.getProjectName();
		assertEquals(actual, expected);
	}

	// test PR3
	@Test
	public void testDescriptionCreateProject() throws Exception {

		TestDataProject testDataProject = createProject();

		programSleep(1);

	
		String actual = ProjectDetailPage.getInstance().getProjectDescription();
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

		String strActual = ProjectDetailPage.getInstance().getProjectTags();
		String strExpected = String.format("with '%s' as the default tags.", testDataProject.getDefaultTags());
		assertTrue(strActual.contains(strExpected));

	}


}
