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

import by.instinctools.bogdevich.testing.pageobject.AppManager;
import by.instinctools.bogdevich.testing.pageobject.CreateAction;
import by.instinctools.bogdevich.testing.pageobject.CreateProject;
import by.instinctools.bogdevich.testing.pageobject.LoginPage;
import by.instinctools.bogdevich.testing.utils.RandomValue;
import by.instinctools.bogdevich.testing.utils.TestDataAction;
import by.instinctools.bogdevich.testing.utils.TestDataProject;

public class BaseTest {

	protected WebDriver driver ;
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
		this.baseURL = baseURL;
		this.login = login;
		this.password = password;
		
		
		AppManager.getInstance().initApp(baseURL);
		driver = AppManager.DRIVER;
		
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		AppManager.getInstance().stopApp();

	}

	protected void logIn(String userName, String userPassword) {
		LoginPage loginPage = LoginPage.getInstance();
		//LoginPage loginPage = new LoginPage();
		loginPage.enterLogin(userName);
		loginPage.enterPssword(userPassword);
		loginPage.loginClick();

	}

	protected TestDataProject createProject() {
		TestDataProject testDataProject = new TestDataProject();
		testDataProject.setProjectName("name-" + RandomValue.INSTANCE.generateValue());
		testDataProject.setProjectDescription("descript-" + RandomValue.INSTANCE.generateValue());
		testDataProject.setProjectСontext("context-" + RandomValue.INSTANCE.generateValue());
		testDataProject.setDefaultTags("UA, AJAX, inctinctools");

		CreateProject createProject = CreateProject.getInstance();

		createProject.clickProjectLinc();
		createProject.enterProjectName(testDataProject);
		createProject.enterProjecDescription(testDataProject);
		createProject.enterProjectContext(testDataProject);
		createProject.enterProjectTags(testDataProject);
		createProject.clickGoToProject();
		createProject.clickCreateNewProject();
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

		CreateAction createAction = CreateAction.getInstance();
		
		createAction.enterDescription(dataAction);
		createAction.enterNotes(dataAction);
		createAction.enterDue(dataAction);
		createAction.enterShowForm(dataAction);
		createAction.clickCreateNewActoin();

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

	public void programSleep(int countSeconds) {
		try {
			TimeUnit.SECONDS.sleep(countSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}