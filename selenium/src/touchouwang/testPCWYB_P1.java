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

public class testPCWYB_P1 {
	public static void main(String[] args) throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		Navigation navigation=driver.navigate();
		navigation.to("http://test.touchouwang.net/product/detail/399");
		
		
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
		log.write("稳盈宝详情页\r\n");
		
		driver.findElement(By.xpath("//*[@id='header']/div[2]/div[2]/a[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='telphone']")).sendKeys("13911111111");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("123qwe");
		driver.findElement(By.xpath("//*[@id='fasong']")).click();
		Thread.sleep(3000);
		
		//case1：加减号变更购买份数
		log.write("case1：稳盈宝加减号变更购买份数\r\n");
		WebElement jia=driver.findElement(By.xpath("//*[@id='buy_form']/span/a[2]"));
		WebElement jian=driver.findElement(By.xpath("//*[@id='buy_form']/span/a[1]"));
		for (int i = 0; i < 5; i++) {
			jia.click();
			Thread.sleep(100);
		}
		if (driver.findElement(By.xpath("//*[@id='txtQty']")).getAttribute("value").equals("5000.00")==true) {
			log.write(time.format(new Date())+"   稳盈宝详情页加号修改份数生效\r\n");
		} else {
			log.write("报错：稳盈宝详情页加号修改份数失败\r\n");
		}
		for (int i = 0; i < 5; i++) {
			jian.click();
			Thread.sleep(100);
		}
		if (driver.findElement(By.xpath("//*[@id='txtQty']")).getAttribute("value").equals("1000.00")==true) {
			log.write(time.format(new Date())+"   稳盈宝详情页减号修改份数生效\r\n");
		} else {
			log.write("报错：稳盈宝详情页减号修改份数失败\r\n");
		}
		
		log.write("\r\n");
		//case2：减号超过限制
		driver.findElement(By.xpath("//*[@id='txtQty']")).clear();
		log.write("case2：减号超过限制\r\n");
		for (int i = 0; i < 11; i++) {
			jia.click();
			Thread.sleep(100);
		}
		driver.findElement(By.xpath("//*[@id='btnInvest']")).click();
		Thread.sleep(1000);
		if (driver.getTitle().equals("投筹网官网--购买-投筹网")==true) {
			log.write(time.format(new Date())+"   稳盈宝超过1w可以购买\r\n");
			navigation.back();
		} else {
			log.write("报错：稳盈宝宝投资金额超过1w不能购买");
		}
		for (int i = 0; i < 20; i++) {
			driver.findElement(By.xpath("//*[@id='buy_form']/span/a[1]")).click();
			Thread.sleep(100);
		}
		if (driver.findElement(By.xpath("//*[@id='txtQty']")).getAttribute("value").equals("1000.00")==true) {
			log.write(time.format(new Date())+"   稳盈宝详情页减号超过1000下限保持1000金额不变\r\n");
			driver.findElement(By.xpath("//*[@id='btnInvest']")).click();
			Thread.sleep(3000);
			if (driver.findElement(By.xpath("//*[@id='newmathCout']")).getAttribute("value").equals("1000")==true) {
				log.write(time.format(new Date())+"   稳盈宝详情页减号到下限后跳转购买页，金额正确\r\n");
				navigation.back();
			} else {
				log.write("报错：稳盈宝详情页减号到下限后跳转购买页，金额错误\r\n");
				navigation.back();
			}
		} else {
			log.write("报错：稳盈宝详情页减号超过1000下限金额出错\r\n");
		}
		
		log.write("\r\n");
		//case3：手动输入金额
		log.write("case3：手动输入金额\r\n");
		driver.findElement(By.xpath("//*[@id='txtQty']")).clear();
		driver.findElement(By.xpath("//*[@id='txtQty']")).sendKeys("100");
		driver.findElement(By.xpath("//*[@id='newbody-options']/div[4]")).click();
		driver.findElement(By.xpath("//*[@id='btnInvest']")).click();
		Thread.sleep(1000);
		if (driver.getTitle().equals("投筹网官网--购买-投筹网")==false) {
			log.write(time.format(new Date())+"   输入金额不为1000倍数（100）报错："+driver.findElement(By.xpath("//*[@id='invest-left']")).getText().equals("金额应为1000的整数倍")+"\r\n");
		} else {
			log.write("报错：稳盈宝金额输入100块可以购买\r\n");
			navigation.back();
		}
		
		driver.findElement(By.xpath("//*[@id='txtQty']")).clear();
		driver.findElement(By.xpath("//*[@id='txtQty']")).sendKeys("1111");
		driver.findElement(By.xpath("//*[@id='newbody-options']/div[4]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id='btnInvest']")).click();
		Thread.sleep(1000);
		if (driver.getTitle().equals("投筹网官网--购买-投筹网")==false) {
			log.write(time.format(new Date())+"   输入金额不为1000倍数（1111）报错："+driver.findElement(By.xpath("//*[@id='invest-left']")).getText().equals("金额应为1000的整数倍")+"\r\n");
		} else {
			log.write("报错：稳盈宝金额输入1111块可以购买\r\n");
			navigation.back();
		}
		
		driver.findElement(By.xpath("//*[@id='txtQty']")).clear();
		driver.findElement(By.xpath("//*[@id='txtQty']")).sendKeys("15000");
		driver.findElement(By.xpath("//*[@id='newbody-options']/div[4]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id='btnInvest']")).click();
		Thread.sleep(3000);
		if (driver.findElement(By.xpath("//*[@id='newmathCout']")).getAttribute("value").equals("15000")==true && driver.findElement(By.xpath("//*[@id='manjianhou']")).getText().equals("15,000.00")==true) {
			log.write(time.format(new Date())+"   稳盈宝跳转购买金额正确\r\n");
		} else {
			log.write("报错：稳盈宝跳转购买页金额错误\r\n");
		}
		navigation.back();
		Thread.sleep(2000);
		
		
		log.write("\r\n\r\n\r\n");
		log.write("------------------------------------------------------------------------------\r\n");
		log.flush(); 
		log.close();
		driver.close();
		System.out.println("testPCWYB done");
	}
}
