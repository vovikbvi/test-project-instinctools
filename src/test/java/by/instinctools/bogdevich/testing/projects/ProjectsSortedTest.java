package by.instinctools.bogdevich.testing.projects;

import by.instinctools.bogdevich.testing.BaseTest;
import by.instinctools.bogdevich.testing.utils.RandomValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProjectsSortedTest extends BaseTest {

	@BeforeMethod
	private void prerequisite() {
		
		if (driver.getTitle().equals("TRACKS::Login")) {
			logIn(login, password);
		}
		if (driver.getTitle() != "TRACKS::List Projects") {
			driver.navigate().to(baseURL+"projects/");
		}
		
		Integer countProject = getCountProject();
		while (countProject < 2) {
			createProject();
			driver.navigate().to(baseURL+"projects/");
			getCountProject();
			countProject = getCountProject();
			
		}
	}


	// test PR10
	@Test
	public void testSortAction() {

		boolean checkCountAction = getCountActive(
				driver.findElement(By.xpath(".//*[@id='list-active-projects']/div/div[1]/div/span")).getText()) < 2; //xPath: get count projects
		if (checkCountAction) {
			addAction();
		}
		
		List<Integer> sortArray = new ArrayList<Integer>();
		getListProjectAction(sortArray);
		Collections.sort(sortArray.subList(0, sortArray.size()));
		Collections.reverse(sortArray);

		driver.findElement(By.id("active_actionize_link")).click();
		driver.switchTo().alert().accept();

		programSleep(3);
		

		List<Integer> expectedArray = new ArrayList<Integer>();
		getListProjectAction(expectedArray);

		Assert.assertTrue(expectedArray.equals(sortArray));
	}


	// test PR11
	@Test
	public void testSortAlphabetize() {

		List<String> sortArray = new ArrayList<String>();
		getListProjectName(sortArray);
		Collections.sort(sortArray.subList(0, sortArray.size()));

		driver.findElement(By.id("active_alphabetize_link")).click();
		driver.switchTo().alert().accept();

		programSleep(2);
		

		List<String> expectedArray = new ArrayList<String>();
		getListProjectName(expectedArray);

		Assert.assertTrue(expectedArray.equals(sortArray));
	}

	public void programSleep(int countSeconds) {
		try {
			TimeUnit.SECONDS.sleep(countSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	private void getListProjectAction(List<Integer> sortArray) {
		List<WebElement> listElements;
		String strCountActive;

		listElements = driver.findElements(By.xpath(".//*[@class='needsreview']")); //for get list project
		for (WebElement webElement : listElements) {
			strCountActive = webElement.getText();
			sortArray.add(getCountActive(strCountActive));

		}
	}

	protected void getListProjectName(List<String> sortArray) {
		List<WebElement> listElements;
		listElements = driver.findElements(By.xpath(".//*[@class='needsreview']/a")); //for get list project
		for (WebElement webElement : listElements) {
			sortArray.add(webElement.getText());
		}
	}

	private void addAction() {
		// add two action

		driver.findElement(By.xpath(".//*[@id='list-active-projects']/div/div[1]/div/span/a")).click(); //click first project name
		for (int i = 0; i < 2; i++) {
			driver.findElement(By.id("todo_description")).clear();
			driver.findElement(By.id("todo_description")).sendKeys("description-" + RandomValue.INSTANCE.generateValue());
			driver.findElement(By.id("todo_new_action_submit")).click();
		}
		driver.findElement(By.linkText("Projects")).click();
	}

	private Integer getCountActive(String strCountActive) {
		strCountActive = strCountActive.substring(strCountActive.indexOf('(') + 1, strCountActive.length() - 8);
		strCountActive = strCountActive.replaceAll(" ", "");
		Integer countActive = 0;

		try {
			countActive = Integer.valueOf(strCountActive);
		} catch (NumberFormatException e) {
			
		}
		return countActive;
	}

}
