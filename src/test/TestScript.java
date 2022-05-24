package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageObjects.SamplePageObjects;
import utilities.ExcelRead;
import utilities.UtilBase;
import utilities.WaitUntil;
import utilities.WebElementLib;

public class TestScript extends UtilBase {

	public ExtentTest test;
	public ExtentReports report;

	public String baseUrl = null, email = null, password = null;
	public String receiver = null, receiver2=null, subject = null, message = null;
	SamplePageObjects pageObj;
	WaitUntil waitObj;

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {

		initialiseDriver();
		driver.manage().window().maximize();

		report = new ExtentReports(System.getProperty("user.dir") + "//testReports//ExtentReport.html");
	}

	@Test(priority = 1)
	public void excelReadTest() throws InterruptedException {

		test = report.startTest("Excel Read Test");

		try {
			baseUrl = ExcelRead.getData(0, 1, "Sheet1");
			email = ExcelRead.getData(1, 1, "Sheet1");
			password = ExcelRead.getData(2, 1, "Sheet1");

			receiver = ExcelRead.getData(0, 1, "Sheet2");
			receiver2 = ExcelRead.getData(3, 1, "Sheet2");
			subject = ExcelRead.getData(1, 1, "Sheet2");
			message = ExcelRead.getData(2, 1, "Sheet2");

			System.out.println(receiver2);
			test.log(LogStatus.PASS, "Excel File Read Sucessful");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to read data from Excel File");
		}

		report.endTest(test);
	}

	@Test(priority = 2)
	public void sendEmail() throws InterruptedException {

		test = report.startTest("Go to Write Email");

//		go to url
		driver.get(baseUrl);
		pageObj = new SamplePageObjects();
//click signin btn
		pageObj.signInBtn().click();
//enter email and click next
		pageObj.enterEmail().sendKeys(email);
		pageObj.enterEmailNext().click();

		// enter password and click next
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement MyPass = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(".//*[@id='password']/div[1]/div/div[1]/input")));
		MyPass.sendKeys(password);

		pageObj.enterPasswordNext().click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div/div")));


//		click the compose Btn
		pageObj.compose().click();
		Thread.sleep(4000);
		
		try {
//			close the pop-up alert box
			WebElement noThanksBtn = pageObj.noThanks();
			boolean result = WebElementLib.doesElementExist(noThanksBtn);
			
			if (result) {
				//Click No Thanks btn
				pageObj.noThanks().click();
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}

//		send the email
		pageObj.sendTo().sendKeys(receiver);
		pageObj.sendTo().sendKeys(Keys.ENTER);
		pageObj.sendTo().sendKeys(receiver2);
		pageObj.sendTo().sendKeys(Keys.ENTER);
		pageObj.emailSubject().sendKeys(subject);
		pageObj.emailBody().sendKeys(message);
		pageObj.sendBtn().click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("#link_vsm")));
//		view sent message
		WebElement vsm = pageObj.viewSentMsz();
		boolean vsmResult = WebElementLib.doesElementExist(vsm);
		if (vsmResult) {
//			click view sent msz
			pageObj.viewSentMsz().click();
		}
//		wait until visibility of subject section of sent email
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#\\:1 > div > div:nth-child(3) > div > table > tr > td.Bu.bAn > div.nH.if > div.nH.V8djrc.byY")));
//capture screenshot
		capture("EmailSent");
//		end the report
		report.endTest(test);
	}

	
	@AfterMethod
	public void afterMethod() throws InterruptedException {
		report.flush();
		driver.close();

	}
}
