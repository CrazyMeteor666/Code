package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.*;
//import costom.*;

public class demo 
{
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");//手动指定chrome浏览器驱动绝对路径
		WebDriver driver=new ChromeDriver();//实例化chrome浏览器对象
		//sleep cost=new sleep();
		//如需适配其他浏览器，修改以上2行驱动路径和实例化对象即可（selenium适用内核包括ie，chrome，firefox，edge等）
		Navigation navigation=driver.navigate();//获取实例化后的对象，用于后续操作对象元素
		//driver.get("http://www.qq.com/");//打开指定页面方法1
		navigation.to("http://www.baidu.com");//打开指定页面方法2
		WebElement searchText=driver.findElement(By.id("kw"));
		searchText.sendKeys("selenium");
		Thread.sleep(3000);
		navigation.back();
		Thread.sleep(3000);
		navigation.forward();
		//driver.close();
		//cost.sleep(3000);
	}
}
