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

public class testPCXRB_P1 {
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
		
		driver.findElement(By.xpath("//*[@id='header']/div[2]/div[2]/a[1]")).click();
		Thread.sleep(3000);
		WebElement username2=driver.findElement(By.xpath("//*[@id='telphone']"));
		username2.sendKeys("13911111155");
		WebElement password2=driver.findElement(By.xpath("//*[@id='password']"));
		password2.sendKeys("123qwe");
		WebElement loginbutton2=driver.findElement(By.xpath("//*[@id='fasong']"));
		loginbutton2.click();
		Thread.sleep(3000);
		
		WebElement number=driver.findElement(By.xpath("//*[@id='txtQty']"));
		
		//case1：新人宝加减号变更购买份数
		log.write("case1：新人宝加减号变更购买份数\r\n");
		WebElement jia=driver.findElement(By.xpath("//*[@id='buy_form']/span/a[2]"));
		WebElement jian=driver.findElement(By.xpath("//*[@id='buy_form']/span/a[1]"));
		for (int i = 0; i < 5; i++) {
			jia.click();
			Thread.sleep(100);
		}
		if (driver.findElement(By.xpath("//*[@id='txtQty']")).getAttribute("value").equals("5000.00")==true) {
			log.write(time.format(new Date())+"   新人宝详情页加号修改份数生效\r\n");
		} else {
			log.write("报错：新人宝详情页加号修改份数失败\r\n");
		}
		for (int i = 0; i < 5; i++) {
			jian.click();
			Thread.sleep(100);
		}
		if (driver.findElement(By.xpath("//*[@id='txtQty']")).getAttribute("value").equals("1000.00")==true) {
			log.write(time.format(new Date())+"   新人宝详情页减号修改份数生效\r\n");
		} else {
			log.write("报错：新人宝详情页减号修改份数失败\r\n");
		}
		
		log.write("\r\n");
		//case2：加减号超过限制
		number.clear();
		log.write("case2：加减号超过限制\r\n");
		for (int i = 0; i < 11; i++) {
			jia.click();
			Thread.sleep(100);
		}
		driver.findElement(By.xpath("//*[@id='btnInvest']")).click();
		Thread.sleep(1000);
		if (driver.getCurrentUrl().equals("投筹网官网--购买-投筹网")==false) {
			log.write(time.format(new Date())+"   新人宝投资金额超过1w上限无法购买\r\n");
		} else {
			log.write("报错：新人宝投资金额超过1w可以购买");
		}
		if (driver.findElement(By.xpath("//*[@id='txtQty']")).getAttribute("value").equals("11000.00")==true&&driver.findElement(By.xpath("//*[@id='invest-left']")).getText().equals("已超出可投限额！")==true) {
			log.write(time.format(new Date())+"   新人宝详情页加号超过1w上限提示超出可投限额\r\n");
		} else {
			log.write("报错：新人宝详情页加号超过1w上限无提示\r\n");
		}
		for (int i = 0; i < 20; i++) {
			jian.click();
			Thread.sleep(100);
		}
		if (driver.findElement(By.xpath("//*[@id='txtQty']")).getAttribute("value").equals("1000.00")==true) {
			log.write(time.format(new Date())+"   新人宝详情页减号超过1000下限保持1000金额不变\r\n");
			driver.findElement(By.xpath("//*[@id='btnInvest']")).click();
			Thread.sleep(3000);
			if (driver.findElement(By.xpath("//*[@id='newmathCout']")).getAttribute("value").equals("1000")==true) {
				log.write(time.format(new Date())+"   新人宝详情页减号到下限后跳转购买页，金额正确\r\n");
				navigation.back();
			} else {
				log.write("报错：新人宝详情页减号到下限后跳转购买页，金额错误\r\n");
				navigation.back();
			}
		} else {
			log.write("报错：新人宝详情页减号超过1000下限金额出错\r\n");
		}
		
		log.write("\r\n");
		//case3：新人宝详情页手动输入金额
		log.write("case3：新人宝详情页手动输入金额\r\n");
		driver.findElement(By.xpath("//*[@id='txtQty']")).clear();
		driver.findElement(By.xpath("//*[@id='txtQty']")).sendKeys("100");
		driver.findElement(By.xpath("//*[@id='newbody-options']/div[4]")).click();
		driver.findElement(By.xpath("//*[@id='btnInvest']")).click();
		Thread.sleep(1000);
		if (driver.getTitle().equals("投筹网官网--购买-投筹网")==false) {
			log.write(time.format(new Date())+"   输入金额不为1000倍数（100）报错："+driver.findElement(By.xpath("//*[@id='invest-left']")).getText().equals("金额应为1000的整数倍")+"\r\n");
		} else {
			log.write("报错：新人宝金额输入100块可以购买\r\n");
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
			log.write("报错：新人宝金额输入1111块可以购买\r\n");
			navigation.back();
		}
		
		driver.findElement(By.xpath("//*[@id='txtQty']")).clear();
		driver.findElement(By.xpath("//*[@id='txtQty']")).sendKeys("15000");
		driver.findElement(By.xpath("//*[@id='newbody-options']/div[4]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id='btnInvest']")).click();
		Thread.sleep(1000);
		if (driver.getTitle().equals("投筹网官网--购买-投筹网")==false) {
			log.write(time.format(new Date())+"   输入金额超出1w上限（1w5）报错："+driver.findElement(By.xpath("//*[@id='invest-left']")).getText().equals("已超出可投限额！")+"\r\n");
		} else {
			log.write("报错：新人宝金额输入1w块可以购买\r\n");
			navigation.back();
		}
		
		driver.findElement(By.xpath("//*[@id='txtQty']")).clear();
		driver.findElement(By.xpath("//*[@id='txtQty']")).sendKeys("10000");
		driver.findElement(By.xpath("//*[@id='newbody-options']/div[4]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id='btnInvest']")).click();
		Thread.sleep(3000);
		if (driver.findElement(By.xpath("//*[@id='newmathCout']")).getAttribute("value").equals("10000")==true && driver.findElement(By.xpath("//*[@id='manjianhou']")).getText().equals("10,000.00")==true) {
			log.write(time.format(new Date())+"   新人宝跳转购买金额正确\r\n");
		} else {
			log.write("报错：新人宝跳转购买页金额错误\r\n");
		}
		navigation.back();
		Thread.sleep(2000);
		
		log.write("\r\n");
		//case4：投资过用户无法购买新人宝
		log.write("case4：投资过用户无法购买新人宝\r\n");
		driver.findElement(By.xpath("//*[@id='header']/div[1]/div/div[3]/a")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id='js_sure_button']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id='header']/div[2]/div[2]/a[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='telphone']")).sendKeys("13911111111");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("123qwe");
		driver.findElement(By.xpath("//*[@id='fasong']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='buy_form']/span/a[1]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id='buy_form']/span/a[2]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id='btnInvest']"));
		Thread.sleep(500);
		if (driver.getCurrentUrl().equals("投筹网官网--购买-投筹网")==false) {
			log.write(time.format(new Date())+"   已投资用户无法购买新人宝\r\n");
		} else {
			log.write("报错：已投资用户可以购买新人宝\r\n");
		}
		
		
		log.write("\r\n\r\n\r\n");
		log.write("------------------------------------------------------------------------------\r\n");
		log.flush(); 
		log.close();
		driver.close();
		System.out.println("testPCXRB done");
	}
}
