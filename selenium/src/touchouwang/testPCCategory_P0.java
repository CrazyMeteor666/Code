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
import org.openqa.selenium.chrome.ChromeDriver;

public class testPCCategory_P0 {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		Navigation navigation=driver.navigate();
		navigation.to("http://test.touchouwang.net/category");
		
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
		log.write("项目列表\r\n");
		
		//case1：项目列表图片加载
		log.write("case1：项目列表图片加载\r\n");
		for (int i = 1; i <7; i++) {
			String xpath1="//*[@id='programlist']/div[";
			String xpath2=""+i;
			String xpath3="]/div[1]/a/img";
			String newxpath=xpath1+xpath2+xpath3;
			String pic_url=driver.findElement(By.xpath(newxpath)).getAttribute("src");
			URL url=new URL(pic_url);
			HttpURLConnection uConnection2=(HttpURLConnection) url.openConnection();
			if (uConnection2.getResponseCode()==200) {
				log.write(time.format(new Date())+"   项目列表第"+i+"张图片加载成功\r\n");
			} else {
				log.write("报错：项目列表第"+i+"张图片加载失败\r\n");
			}
		}
		log.write("\r\n");
		
		//case2：项目列表跳转
		log.write("case2：项目列表跳转\r\n");
		for (int i = 1; i < 7; i++) {
			String xpath1="//*[@id='programlist']/div[";
			String xpath2=""+i;
			String xpath3="]/div[2]/h3/a";
			String newxpath=xpath1+xpath2+xpath3;
			String product_name=driver.findElement(By.xpath(newxpath)).getText();
			driver.findElement(By.xpath(newxpath)).click();
			Thread.sleep(3000);
			if (driver.getTitle().equals("投筹网官网--"+product_name)==true) {
				log.write(time.format(new Date())+"   项目列表第"+i+"个项目跳转成功\r\n");
				navigation.back();
				Thread.sleep(3000);
			} else {
				log.write("报错：项目列表第"+i+"个项目跳转失败\r\n");
				navigation.back();
				Thread.sleep(3000);
			}
		}
		
		log.write("\r\n\r\n\r\n");
		log.write("------------------------------------------------------------------------------\r\n");
		log.flush(); 
		log.close();
		driver.close();
		System.out.println("testPCCategory done");
	}
}
