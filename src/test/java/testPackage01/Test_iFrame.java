package testPackage01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shaft.browser.BrowserActions;
import com.shaft.browser.BrowserFactory;
import com.shaft.element.ElementActions;
import com.shaft.io.ReportManager;
import com.shaft.validation.Verifications;

public class Test_iFrame {
	// Declaring webdriver and excelreader instances
	WebDriver driver;

	@Test(priority = 0, description = "TC001 - Navigate to URL and assert element exists inside iframe")
	public void navigateToURLandAssertElementExists() {
		BrowserActions.navigateToURL(driver, "https://developer.mozilla.org/en-US/docs/Web/HTML/Element/iframe");
		// directly find elmeent - expected to fail
		By goButton = By.id("goButton");
		// Verifications.verifyElementExists(driver, goButton, true);
		// switch to frame manually, then find element, then switch out - expected to
		// pass
		By elementInMainPage = By.id("Result");
		By iframe_1 = By.id("frame_Example1");
		By iframe_2 = By.xpath("//iframe[@title='iframe example 1']");
		ElementActions.switchToIframe(driver, iframe_1);
		ElementActions.switchToIframe(driver, iframe_2);
		Verifications.verifyElementExists(driver, goButton, true);
		ElementActions.switchToDefaultContent(driver);
		Verifications.verifyElementExists(driver, elementInMainPage, true);

		// attempt to switch out while already out
		ElementActions.switchToDefaultContent(driver);

		// identify iframe dynamically, then switch in and out??
		// goButton = By.xpath("//iframe[@title='iframe example
		// 1']//button[@id='goButton']");
		// Verifications.verifyElementExists(driver, goButton, true);
	}

	@BeforeClass // Set-up method, to be run once before the first test
	public void beforeClass() {
		driver = BrowserFactory.getBrowser();
	}

	@AfterClass(alwaysRun = true) // Tear-down method, to be run once after the last test
	public void afterClass() {
		BrowserFactory.closeAllDrivers();
	}

	@AfterMethod
	public void afterMethod() {
		ReportManager.getTestLog();
	}
}