package by.instinctools.bogdevich.testing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import by.instinctools.bogdevich.testing.utils.RandomValue;
import by.instinctools.bogdevich.testing.utils.TestDataAction;
import by.instinctools.bogdevich.testing.utils.TestDataProject;

public class BaseTest {

	protected WebDriver driver;
	protected boolean acceptNextAlert = true;
	protected StringBuffer verificationErrors = new StringBuffer();
	protected String baseURL;
	protected String login;
	protected String password;

	public BaseTest() {
		super();
	}

	@Parameters({ "baseURL", "login", "password" })
	@BeforeClass(alwaysRun = true)
	public void setUp(@Optional("http://localhost:82/tracks/") String baseURL, @Optional("tracks") String login,
			@Optional("tracks123") String password) throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.baseURL = baseURL;
		this.login = login;
		this.password = password;
		driver.get(baseURL);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	}

	protected void logIn(String userName, String userPassword) {
		driver.findElement(By.id("user_login")).clear();
		driver.findElement(By.id("user_login")).sendKeys(userName);
		driver.findElement(By.id("user_password")).clear();
		driver.findElement(By.id("user_password")).sendKeys(userPassword);
		driver.findElement(By.name("login")).click();
	}

	protected TestDataProject createProject() {
		TestDataProject testDataProject = new TestDataProject();
		testDataProject.setProjectName("name-" + RandomValue.INSTANCE.generateValue());
		testDataProject.setProjectDescription("descript-" + RandomValue.INSTANCE.generateValue());
		testDataProject.setProjectСontext("context-" + RandomValue.INSTANCE.generateValue());
		testDataProject.setDefaultTags("UA, AJAX, inctinctools");

		driver.findElement(By.linkText("Projects")).click();

		driver.findElement(By.id("project_name")).clear();
		driver.findElement(By.id("project_name")).sendKeys(testDataProject.getProjectName());

		driver.findElement(By.id("project_description")).clear();
		driver.findElement(By.id("project_description")).sendKeys(testDataProject.getProjectDescription());

		driver.findElement(By.id("project_default_context_name")).clear();
		driver.findElement(By.id("project_default_context_name")).sendKeys(testDataProject.getProjectСontext());

		driver.findElement(By.id("project_default_tags")).clear();
		driver.findElement(By.id("project_default_tags")).sendKeys(testDataProject.getDefaultTags());

		driver.findElement(By.id("go_to_project")).click();

		driver.findElement(By.id("project_new_project_submit")).click();
		return testDataProject;
	}

	protected TestDataAction createAction() {
		TestDataAction dataAction = new TestDataAction();

		LocalDate tooday = LocalDate.now();
		String formStr = "dd/MM/yyyy";
		DateTimeFormatter formater = DateTimeFormatter.ofPattern(formStr);

		dataAction.setDescription("Description-" + RandomValue.INSTANCE.generateValue());
		dataAction.setNotes("Notes-" + (int) RandomValue.INSTANCE.generateValue());
		dataAction.setDue(tooday.plusDays(7).format(formater));
		dataAction.setShowForm(tooday.plusDays(1).format(formater));

		driver.findElement(By.id("todo_description")).clear();
		driver.findElement(By.id("todo_description")).sendKeys(dataAction.getDescription());

		driver.findElement(By.id("todo_notes")).clear();
		driver.findElement(By.id("todo_notes")).sendKeys(dataAction.getNotes());

		driver.findElement(By.id("todo_due")).clear();
		driver.findElement(By.id("todo_due")).sendKeys(dataAction.getDue());

		driver.findElement(By.id("todo_show_from")).clear();
		driver.findElement(By.id("todo_show_from")).sendKeys(dataAction.getShowForm());

		driver.findElement(By.id("predecessor_input")).click();
		driver.findElement(By.id("todo_new_action_submit")).click();

		return dataAction;

	}

	protected Integer getCountProject() {
		String strCountProject = driver.findElement(By.id("active-projects-count")).getText();
		Integer countProject = 0;

		try {
			countProject = Integer.valueOf(strCountProject);
		} catch (NumberFormatException e) {
			System.err.println("Неверный формат строки!");
		}
		return countProject;
	}

}