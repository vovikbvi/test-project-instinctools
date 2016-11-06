package by.instinctools.bogdevich.testing.projects;

import by.instinctools.bogdevich.testing.BaseTest;
import by.instinctools.bogdevich.testing.pageobject.AppManager;
import by.instinctools.bogdevich.testing.pageobject.MainPage;
import by.instinctools.bogdevich.testing.pageobject.ProjectDetailPage;
import by.instinctools.bogdevich.testing.pageobject.ProjectsPage;
import by.instinctools.bogdevich.testing.utils.RandomValue;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProjectsEditDeletTest extends BaseTest {

	@BeforeMethod
	private void prerequisite() {

		if (MainPage.getInstance().getTitle().equals("TRACKS::Login")) {
			logIn(login, password);
		}
		if (MainPage.getInstance().getTitle() != "TRACKS::List Projects") {
			AppManager.DRIVER.navigate().to(baseURL + "projects/");
		}
		if (ProjectsPage.getInstance().getCountProject() < 1) {
			createProject();
		}
	}

	// test PR6
	@Test
	public void testEditDefaulTags() throws Exception {

		String addTag = ", add_tag-" + RandomValue.INSTANCE.generateValue();

		ProjectsPage.getInstance().clickFirstProject();
		ProjectDetailPage.getInstance().clickEditProject()
										.editDefaulText(addTag)
										.clickSubmitProject();

		AppManager.DRIVER.navigate().refresh();
		String actual = ProjectDetailPage.getInstance().getProjectTags();
		String expected = addTag;
		assertTrue(actual.contains(expected));
	}

	// test PR7
	@Test
	public void testEditTagsAndSaveMessage() {

		String addTag = ", add_tag-" + RandomValue.INSTANCE.generateValue();

		ProjectsPage.getInstance().clickFirstProject();
		ProjectDetailPage.getInstance().clickEditProject()
									   .editDefaulText(addTag)
									   .clickSubmitProject();
																																																	// button
		programSleep(1);

		String actual = MainPage.getInstance().getFeedbackPanel();
		String expected = "Project saved";
		assertEquals(actual, expected);

	}

	// test PR8
	@Test
	public void testDeleteProject() {

		String nameProject = ProjectsPage.getInstance().getFirstProjectName();
		ProjectsPage.getInstance().clickDeleteLinc()
		                          .clickAcceptModalWindows();
		

		assertTrue(ProjectsPage.getInstance().fiendProject(nameProject));
	}

	// test PR9
	@Test
	public void testMessageDeleteProject() {

		String nameProject = ProjectsPage.getInstance().getFirstProjectName();
		ProjectsPage.getInstance().clickDeleteLinc()
		                          .clickAcceptModalWindows();

		programSleep(1);
		assertEquals(MainPage.getInstance().getFeedbackPanel(), String.format("Deleted project '%s'", nameProject));
	}

}
