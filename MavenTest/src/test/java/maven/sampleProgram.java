/* To Login as an employee, navigate to absence request and save a valid absence request */
package maven;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sampleProgram extends TestData{

	@Test
	public void test() throws InterruptedException, IOException {		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\VigneshwarUthaman\\Downloads\\Old Lapop Downloads\\chromedriver_win32\\chromedriver.exe");

		data();
		
		WebDriver driver = new ChromeDriver();

		WebDriverWait wait = new WebDriverWait(driver, 50);

		driver.get(url);

		driver.manage().window().maximize();

		//Logging in
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("userid")));

		driver.findElement(By.name("userid")).sendKeys(userId);

		driver.findElement(By.name("pwd")).sendKeys(passWord);

		driver.findElement(By.name("Submit")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pthnavbca_PORTAL_ROOT_OBJECT")));

		//Navigating to Absence Request

		driver.findElement(By.id("pthnavbca_PORTAL_ROOT_OBJECT")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fldra_CO_EMPLOYEE_SELF_SERVICE")));

		driver.findElement(By.id("fldra_CO_EMPLOYEE_SELF_SERVICE")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fldra_HC_TIME_REPORTING")));

		driver.findElement(By.id("fldra_HC_TIME_REPORTING")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fldra_HC_RECORD_TIME")));

		driver.findElement(By.id("fldra_HC_RECORD_TIME")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@id=\"crefli_HC_GP_ABS_EESS_REQ_GBL_CN\"]/a")));

		driver.findElement(By.xpath("//*[@id=\"crefli_HC_GP_ABS_EESS_REQ_GBL_CN\"]/a")).click();

		driver.switchTo().frame("ptifrmtgtframe");

		Select drpName = new Select(driver.findElement(By.id("DERIVED_ABS_SS_PIN_TAKE_NUM")));

		drpName.selectByVisibleText(abName);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("DERIVED_ABS_SS_END_DT")));

		driver.findElement(By.id("DERIVED_ABS_SS_END_DT")).sendKeys(enDate);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("DERIVED_ABS_SS_BGN_DT")));

		//driver.findElement(By.xpath("//*[@id=\"DERIVED_ABS_SS_END_DT$prompt\"]/img")).click();

		//driver.findElement(By.xpath("//*[@id=\"bodyCalendar\"]/tbody/tr[2]/td[6]/a")).click();

		driver.findElement(By.id("DERIVED_ABS_SS_BGN_DT")).clear();

		driver.findElement(By.id("DERIVED_ABS_SS_BGN_DT")).sendKeys(stDate);

		if (!abName.equals("Non Working Weeks"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("DERIVED_ABS_SS_ABSENCE_REASON")));

			Select drp1name = new Select(driver.findElement(By.id("DERIVED_ABS_SS_ABSENCE_REASON")));
						
			drp1name.selectByVisibleText(abReason);
			
			Thread.sleep(4000);
			
			Select drp2name = new Select(driver.findElement(By.id("DERIVED_ABS_SS_ABSENCE_REASON")));
			
			drp2name.selectByVisibleText(abReason);
									
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("DERIVED_ABS_SS_ABSENCE_REASON")));

//			Select drp2name = new Select(driver.findElement(By.id("DERIVED_ABS_SS_ABSENCE_REASON")));

//			drp2name.selectByVisibleText(abReason);
			
			Thread.sleep(4000);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("DERIVED_ABS_SS_FCST_PB")));

			if(!abName.equals("Community Service Unpaid") && !abName.equals("Leave Without Pay") && !abName.equals("Other Paid Leave") && !abName.equals("Personal/Carer Lve Unpaid"))
			{

				driver.findElement(By.id("DERIVED_ABS_SS_FCST_PB")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@id=\"win0divDERIVED_ABS_SS_COMMENTS_LONG$18$\"]/div")));		
			}
		}

		else
		{
			driver.findElement(By.id("DERIVED_ABS_SS_FCST_PB")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@id=\"win0divDERIVED_ABS_SS_COMMENTS_LONG$18$\"]/div")));		
		}
		
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement Element	=	driver.findElement(By.id("DERIVED_ABS_SS_SAVE_BTN"));

		js.executeScript("arguments[0].scrollIntoView();", Element);	
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("DERIVED_ABS_SS_SAVE_BTN")));

		driver.findElement(By.name("DERIVED_ABS_SS_SAVE_BTN")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"win0divDERIVED_ABS_SS_HTMLAREA2\"]/div")));

		String message = driver.findElement(By.xpath("//*[@id=\"win0divDERIVED_ABS_SS_HTMLAREA2\"]/div")).getText();

		Assert.assertEquals(message,"The Absence Request was successfully saved. The request must be submitted in order for it to be approved.");

		driver.findElement(By.name("DERIVED_ABS_SS_OK_BTN")).click();

		System.out.println("Success");
		
		driver.close();

		driver.quit();
	}	
}
