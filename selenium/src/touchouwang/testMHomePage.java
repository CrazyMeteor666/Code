package touchouwang;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class testMHomePage {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--user-agent=iphone");
		WebDriver driver=new ChromeDriver(options);
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
		log.write("移动端首页\r\n");

		//case1：banner、列表图片加载
		log.write("case1：banner、列表图片加载\r\n");
		String banner=driver.findElement(By.xpath("//*[@id='swiper']/div[1]/div[3]/a/img")).getAttribute("src");
		URL banner_url=new URL(banner);
		try {
			HttpURLConnection uConnection = (HttpURLConnection) banner_url.openConnection();
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
			String xpath1="/html/body/article[";
			String xpath2=i+"";
			String xpath3="]/div[2]/img";
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

		
	}
}
