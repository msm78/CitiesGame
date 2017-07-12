package by.htp.tutbytest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppTest {
	
	private static WebDriver driver;

	@BeforeClass
	public static void intitBrowser(){
		
		//System.setProperty("webdriver.gecko.driver", "c:\\temp\\geckodriver\\geckodriver.exe");
		System.setProperty("webdriver.gecko.driver", "D:\\students\\msm\\geckodriver.exe");
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void tutbyTest(){
		driver.get("https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D0%B3%D0%BE%D1%80%D0%BE%D0%B4%D0%BE%D0%B2_%D0%A0%D0%BE%D1%81%D1%81%D0%B8%D0%B8");
		

		//List<WebElement> searchElements = driver.findElements(By.cssSelector("html body div div p:not([class='service'])"));
		//List<WebElement> searchElements = driver.findElements(By.cssSelector(".body>p"));
		
		List<WebElement> searchElements = driver.findElements(By.xpath("//table[@class='standard sortable jquery-tablesorter']/tbody/tr/td[3]"));

		System.out.println("Page title is: " + driver.getTitle());
		System.out.println("Paragraphs: " + searchElements.size());
		
		List<String> listCities = new ArrayList<String>();
		for(WebElement se: searchElements){
			listCities.add(se.getText());
			//System.out.println(se.getText());
		}
		
		for(int i=6; i<listCities.size(); i+=7){
			System.out.println(listCities.get(i));
		}
		//System.out.println(searchElements[0].getText());
		
	}
	
	@AfterClass
	public void closeBrowser(){
		driver.quit();
	}
}
