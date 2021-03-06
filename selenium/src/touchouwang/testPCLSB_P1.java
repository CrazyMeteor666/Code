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

public class testPCLSB_P1 {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		Navigation navigation=driver.navigate();
		Actions action=new Actions(driver);
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
		
		driver.findElement(By.xpath("//*[@id='header']/div[2]/div[2]/a[1]")).click();
		Thread.sleep(3000);
		WebElement username2=driver.findElement(By.xpath("//*[@id='telphone']"));
		username2.sendKeys("13911111111");
		WebElement password2=driver.findElement(By.xpath("//*[@id='password']"));
		password2.sendKeys("123qwe");
		WebElement loginbutton2=driver.findElement(By.xpath("//*[@id='fasong']"));
		loginbutton2.click();
		Thread.sleep(3000);
		
		//case1：预览图加载
		log.write("case1：预览图加载\r\n");
		String pic_url=driver.findElement(By.xpath("//*[@id='smp-1']")).getAttribute("src");
		URL url=new URL(pic_url);
		HttpURLConnection uConnection2=(HttpURLConnection) url.openConnection();
		if (uConnection2.getResponseCode()==200) {
			log.write(time.format(new Date())+"   预览图加载成功\r\n");
		} else {
			log.write(time.format(new Date())+"   预览图加载失败\r\n");
		}
		log.write("\r\n");
		
		//case2：调整购买金额
		log.write("case2：调整购买金额\r\n");
		WebElement number=driver.findElement(By.xpath("//*[@id='newmathCout']"));
		action.sendKeys(number,"100").perform();
		driver.findElement(By.xpath("//*[@id='btnPay']")).click();
		Thread.sleep(1000);
		if (driver.getTitle().equals("投筹网官网--购买-投筹网")==false) {
			log.write(time.format(new Date())+"   输入金额不为1000倍数（100）报错："+driver.findElement(By.xpath("//*[@id='buy_form']/div[1]/p[3]")).getText().contains("金额需为1000的整数倍")+"\r\n");
		} else {
			log.write("报错：乐商宝输入金额100块，可以购买\r\n");
			navigation.back();
		}
		
		number.clear();
		action.sendKeys(number,"1111").perform();
		driver.findElement(By.xpath("//*[@id='btnPay']")).click();
		Thread.sleep(1000);
		if (driver.getTitle().equals("投筹网官网--购买-投筹网")==false) {
			log.write(time.format(new Date())+"   输入金额不为1000倍数（1111）报错："+driver.findElement(By.xpath("//*[@id='buy_form']/div[1]/p[3]")).getText().contains("金额需为1000的整数倍")+"\r\n");
		} else {
			log.write("报错：乐商宝输入金额1111块，可以购买\r\n");
			navigation.back();
		}
		
		number.clear();
		action.sendKeys(number,"2000").perform();
		driver.findElement(By.xpath("//*[@id='btnPay']")).click();
		Thread.sleep(3000);
		if (driver.findElement(By.xpath("//*[@id='newmathCout']")).getAttribute("value").equals("2000")==true && driver.findElement(By.xpath("//*[@id='manjianhou']")).getText().equals("2,000.00")==true) {
			log.write(time.format(new Date())+"   乐商宝跳转购买页金额正确\r\n");
		} else {
			log.write("报错：乐商宝跳转购买页金额错误\r\n");
		}
		
		log.write("\r\n\r\n\r\n");
		log.write("------------------------------------------------------------------------------\r\n");
		log.flush(); 
		log.close();
		driver.close();
		System.out.println("testPCLSB done");
	}
}
