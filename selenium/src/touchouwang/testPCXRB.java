package touchouwang;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;

public class testPCXRB {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		Navigation navigation=driver.navigate();
		navigation.to("http://test.touchouwang.net/product/detail/372?action=newbody");
		
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
		log.write("新人宝详情页\r\n");
		
		//case1：新人宝加减号变更购买份数
		log.write(time.format(new Date())+"   case1：新人宝加减号变更购买份数\r\n");
		WebElement jia=driver.findElement(By.xpath("//*[@id='buy_form']/span/a[2]"));
		WebElement jian=driver.findElement(By.xpath("//*[@id='buy_form']/span/a[1]"));
		for (int i = 0; i < 5; i++) {
			jia.click();
			Thread.sleep(100);
		}
		if (driver.findElement(By.xpath("//*[@id='payNumb']")).getAttribute("value")=="5") {
			log.write(time.format(new Date())+"   新人宝详情页加号修改份数生效\r\n");
		} else {
			log.write("报错：新人宝详情页加号修改份数失败");
		}
		for (int i = 0; i < 5; i++) {
			jian.click();
			Thread.sleep(100);
		}
		if (driver.findElement(By.xpath("//*[@id='payNumb']")).getAttribute("value")=="1") {
			log.write(time.format(new Date())+"   新人宝详情页减号修改份数生效\r\n");
		} else {
			log.write("报错：新人宝详情页减号修改份数失败");
		}
		
		//case2：加减号超过限制
		log.write(time.format(new Date())+"   case2：加减号超过限制\r\n");
		for (int i = 0; i < 15; i++) {
			jia.click();
			Thread.sleep(100);
		}
		if (driver.findElement(By.xpath("//*[@id='payNumb']")).getAttribute("value")=="10"&&driver.findElement(By.xpath("//*[@id='invest-left']")).getText()=="已超出可投限额！"&&driver.findElement(By.xpath("//*[@id='btnInvest']")).getAttribute("disabled")=="disabled") {
			log.write(time.format(new Date())+"   新人宝详情页加号超过10000上限提示超出可投限额\r\n");
		} else {
			log.write("报错：新人宝详情页加号超过10000上限提示失败");
		}
		for (int i = 0; i < 20; i++) {
			jian.click();
			Thread.sleep(100);
		}
		if (driver.findElement(By.xpath("//*[@id='payNumb']")).getAttribute("value")=="1") {
			log.write(time.format(new Date())+"   新人宝详情页减号超过1000下限保持1000金额不变\r\n");
			driver.findElement(By.xpath("//*[@id='btnInvest']")).click();
			Thread.sleep(3000);
			if (driver.findElement(By.xpath("//*[@id='newmathCout']")).getAttribute("value")=="1000") {
				log.write(time.format(new Date())+"   新人宝详情页减号到下限后跳转购买页，金额正确\r\n");
			} else {
				log.write("报错：新人宝详情页减号到下限后跳转购买页，金额错误");
			}
		} else {
			log.write("报错：新人宝详情页减号超过1000下线金额出错");
		}
		
		//case3：新人宝详情页手动输入金额
		log.write(time.format(new Date())+"   case3：新人宝详情页手动输入金额\r\n");
		driver.findElement(By.xpath("//*[@id='txtQty']")).sendKeys("2000");
	}
}