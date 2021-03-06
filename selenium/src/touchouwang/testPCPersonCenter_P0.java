package touchouwang;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class testPCPersonCenter_P0 {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		Navigation navigation=driver.navigate();
		navigation.to("https://www.touchouwang.net/");
		
		File file=new File("D:\\testlog.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter log=new FileWriter(file,true);
		
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Actions action=new Actions(driver);
		
		log.write("------------------------------------------------------------------------------\r\n");
		log.write("\r\n\r\n\r\n");
		log.write("个人中心\r\n");

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

		//case1：个人中心，我的投资图片加载
		log.write("case1：个人中心，我的投资图片加载\r\n");
		for (int i = 1; i < 4; i++) {
			String xpath1="/html/body/div[3]/div[2]/div[2]/div[2]/div[";
			String xpath2=""+i;
			String xpath3="]/img";
			String newxpath=xpath1+xpath2+xpath3;
			String product_pic=driver.findElement(By.xpath(newxpath)).getAttribute("src");
			URL product_pic_url=new URL(product_pic);
			try {
				HttpURLConnection uConnection2=(HttpURLConnection) product_pic_url.openConnection();
				if (uConnection2.getResponseCode()==200) {
					log.write(time.format(new Date())+"   个人中心我的投资第"+i+"张图加载成功\r\n");
				} else {
					log.write("报错：个人中心我的投资第"+i+"张图加载失败\r\n");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//case2：验证登录正确性
		log.write("case2：验证登录正确性\r\n");
		if (driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[1]")).getText().equals("18025450956")==true) {
			log.write(time.format(new Date())+"   个人中心登录帐号正确\r\n");
		}else{
			log.write("报错：个人中心登录帐号错误\r\n");
		}

		//case3：个人中心首页获取优惠券数量
		log.write("case3：个人中心首页获取优惠券数量\r\n");
		if (driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[1]/ul/li[1]/a/em")).getText().equals("0")==false) {
			log.write(time.format(new Date())+"   个人中心获取到未用优惠券数量\r\n");
		}else{
			log.write("报错：个人中心未用优惠券数量获取失败\r\n");
		}

		//case4：可用余额、投资冻结资金、已投资金额、累计收益金额
		log.write("case4：可用余额、投资冻结资金、已投资金额、累计收益金额\r\n");
		if (driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[1]/ul/li[1]/strong")).getText().equals("0.00")==true) {
			log.write(time.format(new Date())+"   可用余额正确\r\n");
		}else{
			log.write("报错：个人中心可用余额错误\r\n");
		}
		if (driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[1]/ul/li[2]/strong")).getText().equals("0.00")==true) {
			log.write(time.format(new Date())+"   投资冻结金额正确\r\n");
		}else{
			log.write("报错：投资冻结金额错误\r\n");
		}
		if (driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[1]/ul/li[3]/strong")).getText().equals("29,000.00")==true) {
			log.write(time.format(new Date())+"   已投资金额正确\r\n");
		}else{
			log.write("报错：已投资金额错误\r\n");
		}
		if (driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[1]/ul/li[4]/strong")).getText().equals("177.87")==true) {
			log.write(time.format(new Date())+"   累计收益金额正确\r\n");
		}else{
			log.write("报错：计收益金额错误\r\n");
		}
		
		//case5：右边充值、提现按钮跳转
		log.write("case5：右边充值、提现按钮跳转\r\n");
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[1]/div[1]/a[1]")).click();
		Thread.sleep(3000);
		if (driver.getCurrentUrl().equals("https://www.touchouwang.net/recharge/index")==true) {
			log.write(time.format(new Date())+"   充值按钮跳转正确\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}else{
			log.write("报错：充值按钮跳转错误\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}

		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[1]/div[1]/a[2]")).click();
		Thread.sleep(3000);
		if (driver.getCurrentUrl().equals("https://www.touchouwang.net/cash/index")==true) {
			log.write(time.format(new Date())+"   提现按钮跳转正确\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}else{
			log.write("报错：提现按钮跳转错误\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}

		//case6：我的投资项目图片鼠标悬停出现按钮
		log.write("case6:我的投资项目图片鼠标悬停出现按钮\r\n");
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[2]/div[1]/img"))).build().perform();
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[2]/div[1]/div[1]/button")).click();
		if (IsElementPresent(By.xpath("/html/body/div[3]/div[2]/div[2]/div[2]/div[1]/div[2]"))==true) {
			log.write(time.format(new Date())+"   点击查看进度按钮展开项目进度\r\n");
		} else {
			log.write("报错：点击查看进度按钮项目进度未展开\r\n");
		}
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[2]/div[1]/img"))).build().perform();
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[2]/div[1]/div[1]/a")).click();
		Thread.sleep(3000);
		if (driver.getTitle().equals("投筹网官网--投资项目详情-投筹网")==true) {
			log.write(time.format(new Date())+"   我的投资项目图片鼠标悬停出现按钮-查看详情跳转正确\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}else{
			log.write("报错：我的投资项目图片鼠标悬停出现按钮-查看详情跳转错误\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}

		//case7：头像下方列表各按钮跳转
		log.write("case7：头像下方列表各按钮跳转\r\n");
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[1]/ul/li[1]/a")).click();
		Thread.sleep(3000);
		if (driver.getCurrentUrl().equals("https://www.touchouwang.net/myaccount/mybonus")==true) {
			log.write(time.format(new Date())+"   我的优惠券按钮跳转正确\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}else{
			log.write("报错：我的优惠券按钮跳转错误\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}

		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[1]/ul/li[2]/a")).click();
		Thread.sleep(3000);
		if (driver.getCurrentUrl().equals("https://www.touchouwang.net/myaccount/mymessages")==true) {
			log.write(time.format(new Date())+"   我的消息按钮跳转正确\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}else{
			log.write("报错：我的消息按钮跳转错误\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}

		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/ul[1]/li[1]/a")).click();
		Thread.sleep(3000);
		if (driver.getCurrentUrl().equals("https://www.touchouwang.net/personcenter/investlist")==true) {
			log.write(time.format(new Date())+"   我投资的项目按钮跳转正确\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}else{
			log.write("报错：我投资的项目按钮跳转错误\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}

		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/ul[1]/li[2]/a")).click();
		Thread.sleep(3000);
		if (driver.getCurrentUrl().equals("https://www.touchouwang.net/personcenter/reservation")==true) {
			log.write(time.format(new Date())+"   我预约的项目按钮跳转正确\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}else{
			log.write("报错：我预约的项目按钮跳转错误\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}

		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/ul[2]/li[1]/a")).click();
		Thread.sleep(3000);
		if (driver.getCurrentUrl().equals("https://www.touchouwang.net/recharge/index")==true) {
			log.write(time.format(new Date())+"   充值按钮跳转正确\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}else{
			log.write("报错：充值按钮跳转错误\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}

		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/ul[2]/li[2]/a")).click();
		Thread.sleep(3000);
		if (driver.getCurrentUrl().equals("https://www.touchouwang.net/cash/index")==true) {
			log.write(time.format(new Date())+"   提现按钮跳转正确\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}else{
			log.write("报错：提现按钮跳转错误\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}

		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/ul[2]/li[3]/a")).click();
		Thread.sleep(3000);
		if (driver.getCurrentUrl().equals("https://www.touchouwang.net/personcenter/fundslist")==true) {
			log.write(time.format(new Date())+"   资金记录按钮跳转正确\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}else{
			log.write("报错：资金记录按钮跳转错误\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}

		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/ul[3]/li[1]/a")).click();
		Thread.sleep(3000);
		if (driver.getCurrentUrl().equals("https://www.touchouwang.net/myaccount/index")==true) {
			log.write(time.format(new Date())+"   用户资料按钮跳转正确\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}else{
			log.write("报错：用户资料按钮跳转错误\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}

		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/ul[3]/li[2]/a")).click();
		Thread.sleep(3000);
		if (driver.getCurrentUrl().equals("https://www.touchouwang.net/myaccount/mybonus")==true) {
			log.write(time.format(new Date())+"   我的优惠券按钮跳转正确\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}else{
			log.write("报错：我的优惠券按钮跳转错误\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}

		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/ul[3]/li[3]/a")).click();
		Thread.sleep(3000);
		if (driver.getCurrentUrl().equals("https://www.touchouwang.net/myaccount/bankcard")==true) {
			log.write(time.format(new Date())+"   我的银行卡按钮跳转正确\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}else{
			log.write("报错：我的银行卡按钮跳转错误\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}

		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/ul[3]/li[4]/a")).click();
		Thread.sleep(3000);
		if (driver.getCurrentUrl().equals("https://www.touchouwang.net/myaccount/changepassword")==true) {
			log.write(time.format(new Date())+"   密码管理按钮跳转正确\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}else{
			log.write("报错：密码管理按钮跳转错误\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}

		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/ul[3]/li[5]/a")).click();
		Thread.sleep(3000);
		if (driver.getCurrentUrl().equals("https://www.touchouwang.net/myaccount/mymessages")==true) {
			log.write(time.format(new Date())+"   我的消息按钮跳转正确\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}else{
			log.write("报错：我的消息按钮跳转错误\r\n");
			navigation.to("https://www.touchouwang.net/personcenter");
		}


		
		log.write("\r\n\r\n\r\n");
		log.write("------------------------------------------------------------------------------\r\n");
		log.flush(); 
		log.close();
		driver.close();
		System.out.println("testPCPersonCenter done");
	}

	private static boolean IsElementPresent(By xpath) {
		WebDriver driver=new ChromeDriver();
		try {
			driver.findElement(xpath);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
