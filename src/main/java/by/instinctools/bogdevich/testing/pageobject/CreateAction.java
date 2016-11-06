package by.instinctools.bogdevich.testing.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import by.instinctools.bogdevich.testing.utils.TestDataAction;

public class CreateAction {
	//private WebDriver driver = AppManager.DRIVER;
	public static CreateAction createAction;

	public static CreateAction getInstance() {
		if (createAction == null) {
			createAction = new CreateAction();
		}
		return createAction;
	}

	private CreateAction() {
	}

	public CreateAction enterDescription(TestDataAction dataAction) {
		AppManager.DRIVER.findElement(By.id("todo_description")).clear();
		AppManager.DRIVER.findElement(By.id("todo_description")).sendKeys(dataAction.getDescription());
		return this;
	}

	public CreateAction enterNotes(TestDataAction dataAction) {
		AppManager.DRIVER.findElement(By.id("todo_notes")).clear();
		AppManager.DRIVER.findElement(By.id("todo_notes")).sendKeys(dataAction.getNotes());
		return this;
	}

	public CreateAction enterDue(TestDataAction dataAction) {
		AppManager.DRIVER.findElement(By.id("todo_due")).clear();
		AppManager.DRIVER.findElement(By.id("todo_due")).sendKeys(dataAction.getDue());
		return this;
	}

	public CreateAction enterShowForm(TestDataAction dataAction) {
		AppManager.DRIVER.findElement(By.id("todo_show_from")).clear();
		AppManager.DRIVER.findElement(By.id("todo_show_from")).sendKeys(dataAction.getShowForm());
		return this;
	}

	public CreateAction clickCreateNewActoin() {
		AppManager.DRIVER.findElement(By.id("todo_new_action_submit")).click();
		return this;
	}

}
