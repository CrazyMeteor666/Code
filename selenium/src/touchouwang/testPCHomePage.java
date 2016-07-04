package touchouwang;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;

public class testPCHomePage {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		Navigation navigation=driver.navigate();
		navigation.to("http://test.touchouwang.net/");

		// case1:banner、列表图片加载
		
		
		// case2:banner点击跳转（先实现点击单个banner跳转，全banner验证待实现）
		WebElement banner=driver.findElement(By.xpath("//*[@id='scrollbanner']/div[1]/div[1]/a"));
		String banner_url = null;
		System.out.println(banner.getAttribute(banner_url));
		//String banner_url=driver.get(banner.getAttribute(arg0));
		
		
		// case3:列表项目状态一致性
	}
}
