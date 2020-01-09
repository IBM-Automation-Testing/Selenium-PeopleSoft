/* 
 * To Login as an employee, navigate to absence request and save a valid absence request
 *  
 *  */

package maven;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
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

public class sampleProgram{

	@Test
	public void test() throws InterruptedException, IOException {		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\VigneshwarUthaman\\Downloads\\Old Lapop Downloads\\chromedriver_win32\\chromedriver.exe");

		
		WebDriver driver = new ChromeDriver();

		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		String url = "http://ourpeoplespacenonprod.int.corp.sun:8888/psp/HCM92DEV/?cmd=login&languageCd=ENG";

		driver.get(url);

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//Logging in
		
		String userId = "U380275";

		driver.findElement(By.name("userid")).sendKeys(userId);
		
		String passWord = "CHANGE_m3_n0w";

		driver.findElement(By.name("pwd")).sendKeys(passWord);

		driver.findElement(By.name("Submit")).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//Navigating to Absence Request

		driver.findElement(By.id("pthnavbca_PORTAL_ROOT_OBJECT")).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("fldra_CO_EMPLOYEE_SELF_SERVICE")).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("fldra_HC_TIME_REPORTING")).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("fldra_HC_RECORD_TIME")).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@id=\"crefli_HC_GP_ABS_EESS_REQ_GBL_CN\"]/a")));

		driver.findElement(By.xpath("//*[@id=\"crefli_HC_GP_ABS_EESS_REQ_GBL_CN\"]/a")).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.switchTo().frame("ptifrmtgtframe");

		Select drpName = new Select(driver.findElement(By.id("DERIVED_ABS_SS_PIN_TAKE_NUM")));
		
		String abName = "Domestic & Family Viol Paid";
		
		String enDate = "01-24-2020";
		
		String stDate = "01-24-2020";
		
		String abReason = "Domestic & Family Viol Paid";

		drpName.selectByVisibleText(abName);

//		Thread.sleep(2000);

		driver.findElement(By.id("DERIVED_ABS_SS_END_DT")).sendKeys(enDate);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//driver.findElement(By.xpath("//*[@id=\"DERIVED_ABS_SS_END_DT$prompt\"]/img")).click();

		//driver.findElement(By.xpath("//*[@id=\"bodyCalendar\"]/tbody/tr[2]/td[6]/a")).click();

		driver.findElement(By.id("DERIVED_ABS_SS_BGN_DT")).clear();

		driver.findElement(By.id("DERIVED_ABS_SS_BGN_DT")).sendKeys(stDate);

		Thread.sleep(4000);

		if (!abName.equals("Non Working Weeks"))
		{

			Select drp1name = new Select(driver.findElement(By.id("DERIVED_ABS_SS_ABSENCE_REASON")));

			Thread.sleep(4000);

			drp1name.selectByVisibleText(abReason);

			Thread.sleep(4000);

			Select drp2name = new Select(driver.findElement(By.id("DERIVED_ABS_SS_ABSENCE_REASON")));

			drp2name.selectByVisibleText(abReason);

			Thread.sleep(4000);

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
		
		Thread.sleep(4000);

		driver.findElement(By.name("DERIVED_ABS_SS_SAVE_BTN")).click();

		Thread.sleep(4000);

		String message = driver.findElement(By.xpath("//*[@id=\"win0divDERIVED_ABS_SS_HTMLAREA2\"]/div")).getText();

		Assert.assertEquals(message,"The Absence Request was successfully saved. The request must be submitted in order for it to be approved.");

		driver.findElement(By.name("DERIVED_ABS_SS_OK_BTN")).click();

		System.out.println("Success");
		
		driver.close();

		driver.quit();
	}	
}
