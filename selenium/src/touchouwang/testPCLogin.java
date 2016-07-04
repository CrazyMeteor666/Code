package touchouwang;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;

public class testPCLogin {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		Navigation navigation=driver.navigate();

		// case1:首页登录
		System.out.println("case1:首页登录");
		navigation.to("http://test.touchouwang.net/");
		System.out.println("检查是否打开首页(https://test.touchouwang.net/):"+"http://test.touchouwang.net/".equals(driver.getCurrentUrl()));
		System.out.println("检查页面title是否正确(投筹网官网--投筹网,房产融资平台)："+"投筹网官网--投筹网,房产融资平台".equals(driver.getTitle()));
		WebElement homepage_login=driver.findElement(By.xpath("//*[@id='header']/div/div[2]/a[1]"));
		homepage_login.click();
		Thread.sleep(3000);
		System.out.println("检查是否跳转到登录页："+"http://test.touchouwang.net/account/index/login".equals(driver.getCurrentUrl()));
		System.out.println("检查页面title："+"投筹网官网--登录注册-投筹网".equals(driver.getTitle()));
		
		WebElement username=driver.findElement(By.xpath("//*[@id='telphone']"));
		username.sendKeys("13911111111");
		WebElement password=driver.findElement(By.xpath("//*[@id='password']"));
		password.sendKeys("123qwe");
		WebElement loginbutton=driver.findElement(By.xpath("//*[@id='fasong']"));
		loginbutton.click();
		Thread.sleep(3000);
		System.out.println("检查是否打开个人中心："+"http://test.touchouwang.net/personcenter/index".equals(driver.getCurrentUrl()));
		System.out.println("检查个人中心title："+"投筹网官网--个人中心-投筹网".equals(driver.getTitle()));
		//System.out.println("检查登录帐号："+driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[1]/text()")).getText().contains("13911111111"));
		driver.findElement(By.xpath("//*[@id='header']/div[1]/div/div[3]/a")).click();
		driver.findElement(By.xpath("//*[@id='js_sure_button']")).click();
		System.out.println("");

		// case2:新人宝详情页登录
		System.out.println("case2:新人宝详情页登录");
		navigation.to("http://test.touchouwang.net/product/detail/278");
		WebElement newbody_login=driver.findElement(By.xpath("//*[@id='needLogin']"));
		newbody_login.click();
		Thread.sleep(3000);
		WebElement username2=driver.findElement(By.xpath("//*[@id='telphone']"));
		username2.sendKeys("13911111111");
		WebElement password2=driver.findElement(By.xpath("//*[@id='password']"));
		password2.sendKeys("123qwe");
		WebElement loginbutton2=driver.findElement(By.xpath("//*[@id='fasong']"));
		loginbutton2.click();
		Thread.sleep(3000);
		System.out.println("获取账户余额："+driver.findElement(By.xpath("//*[@id='newbody-user-balance']/p")).getText());
		System.out.println("是否不能购买："+driver.findElement(By.xpath("//*[@id='btnInvest']")).getText().contains("收益中"));
		driver.findElement(By.xpath("//*[@id='header']/div[1]/div/div[3]/a")).click();
		driver.findElement(By.xpath("//*[@id='js_sure_button']")).click();
		System.out.println("");

		// case3:乐商宝详情页登录
		System.out.println("case3:乐商宝详情页登录");
		navigation.to("http://test.touchouwang.net/product/detail/276");
		WebElement lsb_login=driver.findElement(By.xpath("//*[@id='buy_form']/div[1]/p/a"));
		lsb_login.click();
		Thread.sleep(3000);
		WebElement username3=driver.findElement(By.xpath("//*[@id='telphone']"));
		username3.sendKeys("13911111111");
		WebElement password3=driver.findElement(By.xpath("//*[@id='password']"));
		password3.sendKeys("123qwe");
		WebElement loginbutton3=driver.findElement(By.xpath("//*[@id='fasong']"));
		loginbutton3.click();
		Thread.sleep(3000);
		System.out.println("获取账户余额："+driver.findElement(By.xpath("//*[@id='myMon']")).getText());
		System.out.println("是否显示余额："+driver.findElement(By.xpath("//*[@id='myMon']")).isDisplayed());
		System.out.println("");


		//关闭浏览器
		driver.close();
		System.out.println("脚本执行完毕。");
	}
}
