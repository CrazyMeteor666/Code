package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.*;
//import costom.*;

public class demo 
{
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");//�ֶ�ָ��chrome�������������·��
		WebDriver driver=new ChromeDriver();//ʵ����chrome���������
		//sleep cost=new sleep();
		//��������������������޸�����2������·����ʵ�������󼴿ɣ�selenium�����ں˰���ie��chrome��firefox��edge�ȣ�
		Navigation navigation=driver.navigate();//��ȡʵ������Ķ������ں�����������Ԫ��
		//driver.get("http://www.qq.com/");//��ָ��ҳ�淽��1
		navigation.to("http://www.baidu.com");//��ָ��ҳ�淽��2
		WebElement searchText=driver.findElement(By.id("kw"));
		searchText.sendKeys("selenium");
		Thread.sleep(3000);
		navigation.back();
		Thread.sleep(3000);
		navigation.forward();
		//driver.close();
		//cost.sleep(3000);
	}
}
