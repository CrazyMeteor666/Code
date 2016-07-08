package touchouwang;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class testPCHomePage {
	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		Navigation navigation=driver.navigate();
		navigation.to("http://test.touchouwang.net/");

		// case1:banner、列表图片加载
		String banner_pic=driver.findElement(By.xpath("//*[@id='scrollbanner']/div[1]/div[1]/a/img")).getAttribute("src");
		URL banner_pic_url=new URL(banner_pic);
		try {
			HttpURLConnection uConnection = (HttpURLConnection) banner_pic_url.openConnection();
			if (uConnection.getResponseCode()==200) {
				System.out.println("首页banner加载成功！");
			}
			else {
				System.out.println("首页banner加载失败！");
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
					System.out.println("首页项目列表第"+i+"张图加载成功");
				} else {
					System.out.println("首页项目列表第"+i+"张图加载失败");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		// case2:banner点击跳转（先实现点击单个banner跳转，全banner验证待实现）
		WebElement banner=driver.findElement(By.xpath("//*[@id='scrollbanner']/div[1]/div[1]/a"));
		String banner_url = banner.getAttribute("href");
		banner.click();
		Thread.sleep(3000);
		System.out.println("检查banner跳转是否正确："+banner_url.equals(driver.getCurrentUrl()));
		
		
		// case3:列表项目状态一致性
		
		
		driver.close();
	}
	/*public boolean IsImageVisible(WebDriver driver,WebElement image){  
	    Boolean imageLoaded1 = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", image);   
	    if (!imageLoaded1)   
	    {   
	        MyLog.logger.info("----Image is not present.-----");   
	        return false;  
	    }   
	    else   
	    {   
	        MyLog.logger.info("----image is visible.----");   
	    }  
	    return true;  
	}*/
}
