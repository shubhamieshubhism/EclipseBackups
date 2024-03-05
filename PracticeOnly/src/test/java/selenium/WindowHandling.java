package selenium;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandling {
	@Test
	public void HandlingTheWindow() {
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.naukri.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//a[.='Services']")).click();
		String mainid = driver.getWindowHandle();
		System.out.println(mainid);
		
		Set<String> allid = driver.getWindowHandles();
		System.out.println(allid);
		
		for (String id : allid) {
			if(!mainid.equals(id)) {
				driver.switchTo().window(id);
				driver.findElement(By.xpath("//span[.='FIND JOBS']")).click();
			}
		}
		driver.switchTo().window(mainid);
	
	}
	
	@Test
	public void windowHandlingViaName() {
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demo.automationtesting.in/Windows.html ");
		driver.manage().window().maximize();
		String mainid = driver.getWindowHandle();
		String title1 = driver.getTitle();
		System.out.println(title1);
		driver.findElement(By.xpath("//a[.='Open New Seperate Windows']")).click();
		driver.findElement(By.xpath("//button[.='click']")).click();
		
		Set<String> allid = driver.getWindowHandles();
		for (String id : allid) {
			driver.switchTo().window(id);
			String title= driver.getTitle();
			System.out.println(title);
			if(title.contains("Selenium")) {
				break;
			}
		}
		driver.switchTo().window(mainid);
		
	}

}
