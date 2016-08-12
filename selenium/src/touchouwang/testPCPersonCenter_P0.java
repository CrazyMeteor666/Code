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
		
		log.write("------------------------------------------------------------------------------\r\n");
		log.write("\r\n\r\n\r\n");
		log.write("个人中心\r\n");

		//登录
		driver.findElement(By.xpath("//*[@id='header']/div[2]/div[2]/a[1]")).click();
		Thread.sleep(3000);
		WebElement username2=driver.findElement(By.xpath("//*[@id='telphone']"));
		username2.sendKeys("13911111111");
		WebElement password2=driver.findElement(By.xpath("//*[@id='password']"));
		password2.sendKeys("123qwe");
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
		if (driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[1]/text()")).getText().equals("13911111111")==true) {
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

		//case4：获取可用余额、投资冻结资金、已投资金额、累计收益金额
		log.write("case4：获取可用余额、投资冻结资金、已投资金额、累计收益金额");
		
		
		
		log.write("\r\n\r\n\r\n");
		log.write("------------------------------------------------------------------------------\r\n");
		log.flush(); 
		log.close();
		driver.close();
		System.out.println("testPCPersonCenter done");
	}
}
