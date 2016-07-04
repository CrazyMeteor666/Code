package touchouwang;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;


public class testLoginDemo {
	public static void main(String[] args) throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		Navigation navigation=driver.navigate();
		navigation.to("https://www.touchouwang.net/");
		//检查是否打开首页
		System.out.println("检查是否打开首页(https://www.touchouwang.net/):"+"https://www.touchouwang.net/".equals(driver.getCurrentUrl()));
		//检查页面title
		System.out.println("检查页面title是否正确(投筹网官网--投筹网,房产融资平台)："+"投筹网官网--投筹网,房产融资平台".equals(driver.getTitle()));
		WebElement loginButton=driver.findElement(By.xpath("//*[@id='header']/div/div[2]/a[1]"));
		loginButton.click();
		Thread.sleep(3000);
		//检查是否跳转到登录页
		System.out.println("检查是否跳转到登录页："+"https://www.touchouwang.net/account/index/login".equals(driver.getCurrentUrl()));
		//检查登录页元素
		System.out.println("检查页面title："+"投筹网官网--登录注册-投筹网".equals(driver.getTitle()));
		
		//定位页面元素
		WebElement login=driver.findElement(By.xpath("//*[@id='fasong']"));//登录按钮
		WebElement qrCode=driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/p[2]/img"));//二维码
		WebElement password=driver.findElement(By.xpath("//*[@id='password']"));//密码输入框
		WebElement telphone=driver.findElement(By.id("telphone"));//帐号输入框
		WebElement sure_button=driver.findElement(By.xpath("//*[@id='sure_button']"));
		WebElement cancel_button=driver.findElement(By.xpath("//*[@id='erroimport']/div/div/div/button"));
		WebElement close_button=driver.findElement(By.xpath("//*[@id='erroimport']/div/h5/button"));
		
		
		//case1：不输入帐号密码直接登录
		telphone.click();
		password.click();
		login.click();
		qrCode.click();
		Thread.sleep(1000);
		System.out.println("\n");
		System.out.println("case1：不输入帐号密码直接登录");
		System.out.println("检查未输入帐号红字提示："+driver.findElement(By.xpath("//*[@id='telerror']")).getText().contains("用户名不能为空"));
		System.out.println("检查未输入密码红字提示："+driver.findElement(By.xpath("//*[@id='passworderror']")).getText().contains("密码必须由6位以上数字、字母组成"));
		driver.navigate().refresh();
		
		
		//case2：只输入帐号不输入密码登录
		telphone.sendKeys("13911111111");
		password.click();
		qrCode.click();
		Thread.sleep(1000);
		System.out.println("\n");
		System.out.println("case2：只输入帐号不输入密码");
		System.out.println("检查未输入帐号红字提示不出现："+driver.findElement(By.xpath("//*[@id='telerror']")).getText().contains("用户名不能为空"));
		System.out.println("检查未输入密码红字提示："+driver.findElement(By.xpath("//*[@id='passworderror']")).getText().contains("密码必须由6位以上数字、字母组成"));
		driver.findElement(By.id("telphone")).clear();
		//driver.navigate().refresh();
		
		
		//case3：输入错误格式的帐号不输入密码
		telphone.sendKeys("139111");
		password.click();
		qrCode.click();
		Thread.sleep(1000);
		System.out.println("\n");
		System.out.println("case3：只输入帐号不输入密码");
		System.out.println("检查未输入帐号红字提示不出现："+driver.findElement(By.xpath("//*[@id='telerror']")).getText().contains("用户名不能为空"));
		System.out.println("检查帐号格式错误红字提示："+driver.findElement(By.xpath("//*[@id='telerror']")).getText().contains("手机号码错误"));
		System.out.println("检查未输入密码红字是否出现："+driver.findElement(By.xpath("//*[@id='passworderror']")).getText().contains("密码必须由6位以上数字、字母组成"));
		//driver.navigate().refresh();
		
		
		//case4：输入正确的帐号错误的密码
		telphone.sendKeys("18025450956");
		password.sendKeys("123qwe");
		Thread.sleep(1000);
		System.out.println("检查未输入帐号红字不出现："+driver.findElement(By.xpath("//*[@id='telerror']")).getText().contains("用户名不能为空"));
		System.out.println("检查帐号格式错误红字不出现："+driver.findElement(By.xpath("//*[@id='telerror']")).getText().contains("手机号码错误"));
		login.click();
		Thread.sleep(1000);
		System.out.println("检查密码错误弹框标题："+driver.findElement(By.xpath("//*[@id='erroimport']/div/h5")).getText().contains("登录密码错误 "));
		System.out.println("检查密码错误弹框文案1："+driver.findElement(By.xpath("//*[@id='erroimport']/div/div/h6")).getText().contains("亲爱的用户您好："));
		System.out.println("检查密码错误弹框文案2："+driver.findElement(By.xpath("//*[@id='erroimport']/div/div/p")).getText().contains("你输入的登录密码错误，请重新输入!"));
		System.out.println("检查提示框确认按钮文案："+sure_button.getText().contains("确定"));
		System.out.println("检查提示框取消按钮文案："+cancel_button.getText().contains("取消"));
		cancel_button.click();
		Thread.sleep(500);
		
		login.click();
		Thread.sleep(500);
		System.out.println("检查密码错误弹框标题："+driver.findElement(By.xpath("//*[@id='erroimport']/div/h5")).getText().contains("登录密码错误 "));
		System.out.println("检查密码错误弹框文案1："+driver.findElement(By.xpath("//*[@id='erroimport']/div/div/h6")).getText().contains("亲爱的用户您好："));
		System.out.println("检查密码错误弹框文案2："+driver.findElement(By.xpath("//*[@id='erroimport']/div/div/p")).getText().contains("你输入的登录密码错误，请重新输入!"));
		System.out.println("检查提示框确认按钮文案："+sure_button.getText().contains("确定"));
		System.out.println("检查提示框取消按钮文案："+cancel_button.getText().contains("取消"));
		close_button.click();
		Thread.sleep(500);
		
		login.click();
		Thread.sleep(500);
		System.out.println("检查密码错误弹框标题："+driver.findElement(By.xpath("//*[@id='erroimport']/div/h5")).getText().contains("登录密码错误 "));
		System.out.println("检查密码错误弹框文案1："+driver.findElement(By.xpath("//*[@id='erroimport']/div/div/h6")).getText().contains("亲爱的用户您好："));
		System.out.println("检查密码错误弹框文案2："+driver.findElement(By.xpath("//*[@id='erroimport']/div/div/p")).getText().contains("你输入的登录密码错误，请重新输入!"));
		System.out.println("检查提示框确认按钮文案："+sure_button.getText().contains("确定"));
		System.out.println("检查提示框取消按钮文案："+cancel_button.getText().contains("取消"));
		sure_button.click();
		Thread.sleep(500);
		//driver.navigate().refresh();
		
		
		//case5：输入正确的帐号密码成功登录
		telphone.sendKeys("18025450956");
		password.sendKeys("123ok456ok");
		
		
		
		driver.close();
	}
}