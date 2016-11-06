package by.instinctools.bogdevich.testing.projects;

import by.instinctools.bogdevich.testing.BaseTest;
import by.instinctools.bogdevich.testing.pageobject.AppManager;
import by.instinctools.bogdevich.testing.pageobject.MainPage;
import by.instinctools.bogdevich.testing.pageobject.ProjectsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProjectsSortedTest extends BaseTest {

	@BeforeMethod
	private void prerequisite() {
		
		if (MainPage.getInstance().getTitle().equals("TRACKS::Login")) {
			logIn(login, password);
		}
		if (MainPage.getInstance().getTitle() != "TRACKS::List Projects") {
			AppManager.DRIVER.navigate().to(baseURL + "projects/");
		}
		
		Integer countProject = ProjectsPage.getInstance().getCountProject();
		while (countProject < 2) {
			createProject();
			AppManager.DRIVER.navigate().to(baseURL + "projects/");
			ProjectsPage.getInstance().getCountProject();
			countProject = ProjectsPage.getInstance().getCountProject();
			
		}
	}


	// test PR10
	@Test
	public void testSortAction() {

		if (ProjectsPage.getInstance().checkCountAction()) {
			ProjectsPage.getInstance().addAction();
		}
		
		List<Integer> sortArray = new ArrayList<Integer>();
		ProjectsPage.getInstance().getListProjectAction(sortArray);
		Collections.sort(sortArray.subList(0, sortArray.size()));
		Collections.reverse(sortArray);

		ProjectsPage.getInstance().clickSortByNumberOfTtasks()
		                          .clickAcceptModalWindows();

		programSleep(2);
		

		List<Integer> expectedArray = new ArrayList<Integer>();
		ProjectsPage.getInstance().getListProjectAction(expectedArray);

		Assert.assertTrue(expectedArray.equals(sortArray));
	}


	// test PR11
	@Test
	public void testSortAlphabetize() {

		List<String> sortArray = new ArrayList<String>();
		ProjectsPage.getInstance().getListProjectName(sortArray);
		Collections.sort(sortArray.subList(0, sortArray.size()));

		
		
		ProjectsPage.getInstance().clickSortSortAlphabetically()
        						  .clickAcceptModalWindows();

		programSleep(2);
		

		List<String> expectedArray = new ArrayList<String>();
		ProjectsPage.getInstance().getListProjectName(expectedArray);

		Assert.assertTrue(expectedArray.equals(sortArray));
	}



}
