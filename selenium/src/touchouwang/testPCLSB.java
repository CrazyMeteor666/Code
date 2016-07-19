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

public class testPCLSB {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		Navigation navigation=driver.navigate();
		navigation.to("http://test.touchouwang.net/product/detail/377");
		
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
		log.write("乐商宝详情页\r\n");
		
		//case1：预览图加载
		log.write(time.format(new Date())+"   case1：预览图加载\r\n");
		String pic_url=driver.findElement(By.xpath("//*[@id='sim866403']")).getAttribute("src");
		URL url=new URL(pic_url);
		HttpURLConnection uConnection2=(HttpURLConnection) url.openConnection();
		if (uConnection2.getResponseCode()==200) {
			log.write(time.format(new Date())+"   预览图加载成功\r\n");
		} else {
			log.write(time.format(new Date())+"   预览图加载失败\r\n");
		}
		
		//case2：调整购买金额
		log.write(time.format(new Date())+"   case2：调整购买金额\r\n");
		WebElement number=driver.findElement(By.xpath("//*[@id='newmathCout']"));
		number.sendKeys("100");
		if (driver.findElement(By.xpath("//*[@id='btnPay']")).getAttribute("disabled")=="disabled") {
			log.write(time.format(new Date())+"   输入金额不为1000倍数报错："+driver.findElement(By.xpath("//*[@id='buy_form']/div[1]/p[3]")).getText().contains("金额需为1000的整数倍"));
		} else {
			log.write("报错：乐商宝输入金额100块，可以购买");
		}
		number.sendKeys("1111");
		if (driver.findElement(By.xpath("//*[@id='btnPay']")).getAttribute("disabled")=="disabled") {
			log.write(time.format(new Date())+"   输入金额不为1000倍数报错："+driver.findElement(By.xpath("//*[@id='buy_form']/div[1]/p[3]")).getText().contains("金额需为1000的整数倍"));
		} else {
			log.write("报错：乐商宝输入金额111块，可以购买");
		}
		number.sendKeys("20000");
		driver.findElement(By.xpath("//*[@id='btnPay']")).click();
		Thread.sleep(3000);
		if (driver.findElement(By.xpath("//*[@id='newmathCout']")).getAttribute("value")=="2000" && driver.findElement(By.xpath("//*[@id='manjianhou']")).getText()=="2000.00") {
			log.write(time.format(new Date())+"   乐商宝跳转购买页金额正确\r\n");
		} else {
			log.write("报错：乐商宝跳转购买页金额错误");
		}
		
		log.write("\r\n\r\n\r\n");
		log.write("------------------------------------------------------------------------------\r\n");
		log.flush(); 
		log.close();
		driver.close();
		System.out.println("testPCLSB done");
	}
}
