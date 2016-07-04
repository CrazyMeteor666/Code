package touchouwang;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class testTYJ {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		Navigation navigation=driver.navigate();
		navigation.to("https://www.touchouwang.net/activity/ex_money/action");
		System.out.println("检查是否打开首页(https://www.touchouwang.net/activity/ex_money/action):"+"https://www.touchouwang.net/activity/ex_money/action".equals(driver.getCurrentUrl()));
		System.out.println("检查页面title是否正确(投筹网官网--体验金)："+"投筹网官网--体验金".equals(driver.getTitle()));
		//“注册即送1000元体验金”动画效果
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='neg-1000-02']"))).build().perform();
		Thread.sleep(2000);
		//System.out.println("检查页面动画效果："+driver.findElement(By.xpath("//*[@id='login-panel-header']")).getAttribute(arg0));
		
		//验证未登录时，点击3个“GO领取”按钮弹出登录框
		driver.findElement(By.xpath("//*[@id='neg-1000']/a")).click();
		Thread.sleep(2000);
		System.out.println("号码输入框是否显示："+driver.findElement(By.xpath("//*[@id='phoneNumber']")).isDisplayed());
		System.out.println("密码输入框是否显示："+driver.findElement(By.xpath("//*[@id='txtPassword']")).isDisplayed());
		System.out.println("验证码输入框是否显示："+driver.findElement(By.xpath("//*[@id='txtVerify']")).isDisplayed());
		System.out.println("验证码获取按钮是否显示："+driver.findElement(By.xpath("//*[@id='btnGetCode']")).isDisplayed());
		System.out.println("投筹网协议勾选框是否显示："+driver.findElement(By.xpath("//*[@id='login-panel']/div[6]/label/input")).isDisplayed());
		try {
			WebElement xieyi=driver.findElement(By.xpath("//*[@id='login-panel']/div[6]/a"));
			System.out.println("投筹网协议链接是否显示："+xieyi.isDisplayed());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("元素未找到"+e.getMessage());
		}
		
		
		driver.close();
	}

}
