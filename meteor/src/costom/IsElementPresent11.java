package costom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;

public class IsElementPresent11 {
	public static void main(String[] args) {
		IsElementPresent(By.xpath(""));
	}

	private static boolean IsElementPresent(By by) {
		// TODO Auto-generated method stub
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
