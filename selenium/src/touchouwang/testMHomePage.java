package touchouwang;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class testMHomePage {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--user-agent=iphone");
		WebDriver driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		Navigation navigation=driver.navigate();
		navigation.to("http://test.touchouwang.net/");
	}
}