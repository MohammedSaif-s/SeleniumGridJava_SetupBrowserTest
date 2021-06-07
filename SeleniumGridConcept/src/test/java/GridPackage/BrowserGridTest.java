package GridPackage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BrowserGridTest 
{
	WebDriver driver;
	static String nodeURL;
	static DesiredCapabilities cap;

	@Parameters({"portNo"})
	@Test
	public void setUp(@Optional("4415") String portNo) throws MalformedURLException
	{
		if (portNo.equalsIgnoreCase("4455"))
		{
			nodeURL = "http://192.168.1.100:4444/wd/hub";
			System.out.println("Chrome Browser Test Environment created");
			cap = new DesiredCapabilities();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.WINDOWS);

			ChromeOptions options = new ChromeOptions();
			options.merge(cap);

			driver = new RemoteWebDriver(new URL(nodeURL), options);
			driver.manage().window().maximize();
		}
		else if (portNo.equalsIgnoreCase("4466"))
		{
			nodeURL = "http://192.168.1.100:4444/wd/hub";
			System.out.println("InternetExplorer Browser Test Environment created");
			cap = new DesiredCapabilities().internetExplorer();
			cap.setBrowserName("internet explorer");
			cap.setPlatform(Platform.WINDOWS);

			driver = new RemoteWebDriver(new URL(nodeURL), cap);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		driver.get("https://google.com");
		System.out.println(driver.getTitle());
		driver.quit();
	}
}