package by.instinctools.bogdevich.testing.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import by.instinctools.bogdevich.testing.utils.RandomValue;

public class ProjectsPage {
	
	private static ProjectsPage projectsPage;
	
	public static ProjectsPage getInstance(){
		if (projectsPage == null){
			projectsPage = new ProjectsPage();
		}
		return projectsPage;
	}

	private ProjectsPage(){
		
	}
	
	public Integer getCountProject() {
		String strCountProject = AppManager.DRIVER.findElement(By.id("active-projects-count")).getText();
		Integer countProject = 0;

		try {
			countProject = Integer.valueOf(strCountProject);
		} catch (NumberFormatException e) {
			System.err.println("Неверный формат строки!");
		}
		return countProject;
	}

	public ProjectsPage clickFirstProject(){
		//xPath: click first project
		AppManager.DRIVER.findElement(By.xpath(".//*[@id='list-active-projects']/div/div[1]/div/span/a")).click();
		return this;
	}
	
	public String getFirstProjectName(){
		// xPath get name first project
		return AppManager.DRIVER.findElement(By.xpath(".//*[@id='list-active-projects']/div/div[1]/div/span/a"))
				.getText();		
	}
	
	public ProjectsPage clickDeleteLinc(){
		// xPath: Click delete icon
		AppManager.DRIVER.findElement(By.xpath(".//*[@id='list-active-projects']/div/div[1]/a[1]")).click(); 	
		return this;
		}
	
	public ProjectsPage clickAcceptModalWindows(){
		AppManager.DRIVER.switchTo().alert().accept();
		return this;
	}
	 
	public Boolean fiendProject(String nameProject){
		return AppManager.DRIVER.findElement(By.linkText(nameProject)).isDisplayed();
	}
	
	public boolean checkCountAction(){  
		//xPath: get count action
	return	getCountActive(AppManager.DRIVER.findElement(By.xpath(".//*[@id='list-active-projects']/div/div[1]/div/span")).getText()) < 2; 
	}
	

	public ProjectsPage clickSortByNumberOfTtasks(){
		AppManager.DRIVER.findElement(By.id("active_actionize_link")).click();
		return this;
	}

	public ProjectsPage clickSortSortAlphabetically (){
		AppManager.DRIVER.findElement(By.id("active_alphabetize_link")).click();
		return this;
	}

	public Integer getCountActive(String strCountActive) {
		strCountActive = strCountActive.substring(strCountActive.indexOf('(') + 1, strCountActive.length() - 8);
		strCountActive = strCountActive.replaceAll(" ", "");
		Integer countActive = 0;
		
		try {
			countActive = Integer.valueOf(strCountActive);
		} catch (NumberFormatException e) {
			
		}
		return countActive;
	}

	public void getListProjectAction(List<Integer> sortArray) {
		List<WebElement> listElements;
		String strCountActive;

		listElements = AppManager.DRIVER.findElements(By.xpath(".//*[@class='needsreview']")); //for get list project
		for (WebElement webElement : listElements) {
			strCountActive = webElement.getText();
			sortArray.add(ProjectsPage.getInstance().getCountActive(strCountActive));

		}
	}

	public void getListProjectName(List<String> sortArray) {
		List<WebElement> listElements;
		listElements = AppManager.DRIVER.findElements(By.xpath(".//*[@class='needsreview']/a")); //for get list project
		for (WebElement webElement : listElements) {
			sortArray.add(webElement.getText());
		}
	}

	public void addAction() {
		// add two action
		
		//click first project name             
		clickFirstProject();
		for (int i = 0; i < 2; i++) {
			AppManager.DRIVER.findElement(By.id("todo_description")).clear();
			AppManager.DRIVER.findElement(By.id("todo_description")).sendKeys("description-" + RandomValue.INSTANCE.generateValue());
			AppManager.DRIVER.findElement(By.id("todo_new_action_submit")).click();
		}
		AppManager.DRIVER.findElement(By.linkText("Projects")).click();
	}


}
