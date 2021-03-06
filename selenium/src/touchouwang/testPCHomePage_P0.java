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
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class testPCHomePage_P0 {
	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		Navigation navigation=driver.navigate();
		navigation.to("http://test.touchouwang.net/");
		
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
		log.write("首页\r\n");

		// case1：banner、列表图片加载
		log.write("case1：banner、列表图片加载\r\n");
		String banner_pic=driver.findElement(By.xpath("//*[@id='scrollbanner']/div[1]/div[1]/a/img")).getAttribute("src");
		URL banner_pic_url=new URL(banner_pic);
		try {
			HttpURLConnection uConnection = (HttpURLConnection) banner_pic_url.openConnection();
			if (uConnection.getResponseCode()==200) {
				log.write(time.format(new Date())+"   首页banner加载成功！\r\n");
			}
			else {
				log.write("报错：首页banner加载失败！\r\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 1; i < 4; i++) {
			String xpath1="//*[@id='programlist']/div[";
			String xpath2=i+"";
			String xpath3="]/div[1]/a/img";
			String newxpath=xpath1+xpath2+xpath3;
			String list_pic1=driver.findElement(By.xpath(newxpath)).getAttribute("src");
			URL list_pic1_url=new URL(list_pic1);
			try {
				HttpURLConnection uConnection2=(HttpURLConnection) list_pic1_url.openConnection();
				if (uConnection2.getResponseCode()==200) {
					log.write(time.format(new Date())+"   首页项目列表第"+i+"张图加载成功\r\n");
				} else {
					log.write("报错：首页项目列表第"+i+"张图加载失败\r\n");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		log.write("\r\n");
		// case2：banner点击跳转（先实现点击单个banner跳转，全banner验证待实现）
		log.write("case2：banner点击跳转（先实现点击单个banner跳转，全banner验证待实现）\r\n");
		WebElement banner=driver.findElement(By.xpath("//*[@id='scrollbanner']/div[1]/div[1]/a"));
		String banner_url = banner.getAttribute("href");
		banner.click();
		Thread.sleep(3000);
		if (banner_url.equals(driver.getCurrentUrl())==true) {
			log.write(time.format(new Date())+"   首页banner跳转正确\r\n");
		} else {
			log.write("报错：首页banner跳转错误\r\n");
		}
		navigation.back();
		
		log.write("\r\n");
		// case3:项目点击可跳转
		log.write("case3:项目点击可跳转\r\n");
		for (int m = 1; m < 4; m++) {
			String xpath4="//*[@id='programlist']/div[";
			String xpath5=m+"";
			String xpath6="]/div[2]/h3/a";
			String newxpath2=xpath4+xpath5+xpath6;
			String product_name=driver.findElement(By.xpath(newxpath2)).getText();
			driver.findElement(By.xpath(newxpath2)).click();
			Thread.sleep(3000);
			if (driver.getTitle().equals("投筹网官网--"+product_name)==true) {
				log.write(time.format(new Date())+"   首页列表第"+m+"个项目跳转成功\r\n");
				navigation.back();
				Thread.sleep(3000);
			} else {
				log.write("报错：首页列表第"+m+"个项目跳转失败\r\n");
				navigation.back();
				Thread.sleep(3000);
			}
		}
		
		
		
		log.write("\r\n\r\n\r\n");
		log.write("------------------------------------------------------------------------------\r\n");
		log.flush(); 
		log.close();
		driver.close();
		System.out.println("testPCHomePage done");
	}
}
