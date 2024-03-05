package selenium;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LinkHandling {
	WebDriver driver;

	@Test
	public void handlingTheLinks() throws InterruptedException {
		WebDriverManager.edgedriver().setup();
		EdgeOptions opt = new EdgeOptions();
		opt.addArguments("--guest");
		driver = new EdgeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.amazon.in");
		WebElement searchBar = driver.findElement(By.xpath("//input[@type='text']"));
		searchBar.sendKeys("iphone");
		searchBar.submit();
		List<WebElement> phoneName = driver.findElements(By.xpath(
				"//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']"));
		List<WebElement> phonePrice = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
		for (int i = 0; i < phoneName.size(); i++) {
			String phonenames = phoneName.get(i).getAttribute("textContent");
			String phoneprice = phonePrice.get(i).getAttribute("textContent");
			System.out.println("The phone name is : " + phonenames);
			System.out.println("The phone price is : " + phoneprice);
		}

		linkTextAndAttribute(driver);

	}

	private void linkTextAndAttribute(WebDriver driver) throws InterruptedException {
		WebDriverManager.edgedriver().setup();
		EdgeOptions opt = new EdgeOptions();
		opt.addArguments("--guest");
		driver = new EdgeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.amazon.in");
		SoftAssert soft = new SoftAssert();
		List<WebElement> links = driver.findElements(By.xpath("//a[@class='nav-a  ']"));
		for (WebElement link : links) {
			// String textoflink = link.getText();
			String textoflink = link.getAttribute("textContent");
			String url = link.getAttribute("href");
			link.click();
			System.out.println("Text of the link is : " + "==>" + textoflink + "<==");
			System.out.println();
			System.out.println("Attribute of the link is : " + "==>" + url + "<==");
			boolean result = responseCode(url);
			soft.assertTrue(result, "Invalid link");
			driver.get("https://www.amazon.in");
			Thread.sleep(1000);
			driver.findElements(By.xpath("//a[@class='nav-a  ']"));
		}
		soft.assertAll();
	}

	private boolean responseCode(String url) {
		int responseCode = 0;
		try {
			responseCode = Request.Get(url).execute().returnResponse().getStatusLine().getStatusCode();
			System.out.println("The response code for " + url + " " + responseCode);
			if (responseCode == 200) {
				return true;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

}
