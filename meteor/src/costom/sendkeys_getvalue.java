package costom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class sendkeys_getvalue {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		Navigation navigation=driver.navigate();
		Actions action=new Actions(driver);
		navigation.to("http://10.10.16.227:1000/product/detail/391");
		
		WebElement newbody_login=driver.findElement(By.xpath("//*[@id='needLogin']"));
		newbody_login.click();
		Thread.sleep(3000);
		WebElement username2=driver.findElement(By.xpath("//*[@id='telphone']"));
		username2.sendKeys("13911111155");
		WebElement password2=driver.findElement(By.xpath("//*[@id='password']"));
		password2.sendKeys("123qwe");
		WebElement loginbutton2=driver.findElement(By.xpath("//*[@id='fasong']"));
		loginbutton2.click();
		Thread.sleep(3000);
		WebElement paynub=driver.findElement(By.xpath("//*[@id='txtQty']"));
		action.sendKeys(paynub,"2000").perform();
		driver.findElement(By.xpath("//*[@id='newbody-options']/div[2]")).click();
		System.out.println(paynub.getAttribute("value"));
		System.out.println("done");
		driver.close();
	}
}
