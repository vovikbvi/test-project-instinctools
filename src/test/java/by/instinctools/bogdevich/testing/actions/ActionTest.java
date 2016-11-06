package by.instinctools.bogdevich.testing.actions;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.instinctools.bogdevich.testing.BaseTest;
import by.instinctools.bogdevich.testing.pageobject.AppManager;
import by.instinctools.bogdevich.testing.pageobject.MainPage;
import by.instinctools.bogdevich.testing.pageobject.ProjectDetailPage;
import by.instinctools.bogdevich.testing.utils.TestDataAction;

public class ActionTest extends BaseTest {

	@BeforeMethod
	private void prerequisite() {

		if (MainPage.getInstance().getTitle().equals("TRACKS::Login")) {
			logIn(login, password);
		}
		if (MainPage.getInstance().getTitle() != "TRACKS::List Projects") {
			AppManager.DRIVER.navigate().to(baseURL);
		}
	}

	// TEST AC1
	@Test
	public void testAddAction() {
		createProject();
		TestDataAction dataAction = createAction();

		String actual =  ProjectDetailPage.getInstance().getActionDescription();
		String expected = dataAction.getDescription();

		assertEquals(actual, expected);
	}

	// TEST AC2
	@Test
	public void testMessageAddAction() {
		createProject();
		createAction();

		programSleep(1);
		
		String actual = MainPage.getInstance().getFeedbackPanel();
		String expected = "Added new next action to tickler";

		assertEquals(actual, expected);
	}

	// TEST AC3
	@Test
	public void testShowNotesAction() {
		createProject();
		TestDataAction dataAction = createAction();

		ProjectDetailPage.getInstance().clickShowNotes();
																												
		String actual = ProjectDetailPage.getInstance().getNotesText(); 
		String expected = dataAction.getNotes();

		assertEquals(actual, expected);
	}

	// TEST AC4
	@Test
	public void testStatusActionCompleted() {
		createProject();
		TestDataAction dataAction = createAction();

		ProjectDetailPage.getInstance().clickCheckboxMarkComplite();
		String actual = ProjectDetailPage.getInstance().getNameCompliteAction();
		String expected = dataAction.getDescription();

		assertEquals(actual, expected);
	}

}
