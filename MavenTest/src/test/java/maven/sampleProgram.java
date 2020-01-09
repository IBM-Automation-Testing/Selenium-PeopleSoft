package maven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sampleProgram {
  @Test
  public void f() {
	  
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\VigneshwarUthaman\\Downloads\\Old Lapop Downloads\\chromedriver_win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();  
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/test/guru99home/");  
		String title = driver.getTitle();				 
		Assert.assertTrue(title.contains("Demo Guru99 Page")); 		
		driver.close();driver.quit();
	  
	  
  }
}
