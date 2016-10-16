package by.instinctools.bogdevich.testing.actions;

import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.instinctools.bogdevich.testing.BaseTest;
import by.instinctools.bogdevich.testing.utils.TestDataAction;

public class ActionTest extends BaseTest {

	@BeforeMethod
	private void prerequisite() {

		if (driver.getTitle().equals("TRACKS::Login")) {
			logIn(login, password);
		}

		if (driver.getTitle() != "TRACKS::List Projects") {
			driver.navigate().to(baseURL);
		}
	}

	// TEST AC1
	@Test
	public void testAddAction() {
		createProject();
		TestDataAction dataAction = createAction();

		String actual = driver.findElement(By.xpath(".//*[@id='deferred_pending_container_items']/div[2]/div/div/span"))
				.getText(); // xPath: get actions' description name
		String expected = dataAction.getDescription();

		assertEquals(actual, expected);
	}

	// TEST AC2
	@Test
	public void testMessageAddAction() {
		createProject();
		createAction();

		programSleep(1);
		
		String actual = driver.findElement(By.id("flash")).getText();
		String expected = "Added new next action to tickler";

		assertEquals(actual, expected);
	}

	// TEST AC3
	@Test
	public void testShowNotesAction() {
		createProject();
		TestDataAction dataAction = createAction();

		driver.findElement(By.xpath(".//*[@id='deferred_pending_container_items']/div[2]/div/div/a[3]")).click(); // xPath: click Show notes  icon
																												
		String actual = driver
				.findElement(By.xpath(".//*[@id='deferred_pending_container_items']/div[2]/div/div/div/p")).getText(); // xPath: get notes text
																												
		String expected = dataAction.getNotes();

		assertEquals(actual, expected);
	}

	// TEST AC4
	@Test
	public void testStatusActionCompleted() {
		createProject();
		TestDataAction dataAction = createAction();

		// xPath: set checkbox in Deferred/pending actions in this project
		driver.findElement(By.xpath(".//*[@id='deferred_pending_container_items']/div[2]/div/input")).click();

		String actual = driver.findElement(By.xpath(".//*[@id='completed_container_items']/div/div/div/span[2]"))
				.getText(); // xPath: action in "Completed actions in this project"
		String expected = dataAction.getDescription();

		assertEquals(actual, expected);
	}

}
