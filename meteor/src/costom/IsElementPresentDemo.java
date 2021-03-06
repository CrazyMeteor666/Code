package costom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class IsElementPresentDemo {
	private static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		Navigation navigation=driver.navigate();
		navigation.to("https://www.touchouwang.net/");
		Actions action=new Actions(driver);
		
		//登录
		driver.findElement(By.xpath("//*[@id='header']/div[2]/div[2]/a[1]")).click();
		Thread.sleep(3000);
		WebElement username2=driver.findElement(By.xpath("//*[@id='telphone']"));
		username2.sendKeys("18025450956");
		WebElement password2=driver.findElement(By.xpath("//*[@id='password']"));
		password2.sendKeys("123ok456ok");
		WebElement loginbutton2=driver.findElement(By.xpath("//*[@id='fasong']"));
		loginbutton2.click();
		Thread.sleep(3000);
		
		System.out.println(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[2]/div[1]/div[2]")).getAttribute("class"));
		
		//action.moveToElement(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[2]/div[1]/img"))).build().perform();
		//driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[2]/div[1]/div[1]/button")).click();
		
		//System.out.println(IsElementPresent(By.xpath("/html/body/div[3]/div[2]/div[2]/div[2]/div[1]/div[2]")));
		driver.close();
	}
	private static boolean IsElementPresent(By xpath) {
	    try {
	        driver.findElement(xpath);
	        System.out.println("1");
	        return true;
	    } catch (Exception e) {
	    	System.out.println("2");
	        return false;
	    }
	}
}
