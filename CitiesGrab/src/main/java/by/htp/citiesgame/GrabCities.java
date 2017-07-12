package by.htp.citiesgame;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class GrabCities  {
	List<String> listCities = new ArrayList<String>();
	private static WebDriver driver;
	
	public String getCity(int i){
		return listCities.get(i);
	}
	
	public GrabCities() {
		
		// intitBrowser()
		//System.setProperty("webdriver.gecko.driver", "c:\\temp\\geckodriver\\geckodriver.exe");
		//System.setProperty("webdriver.gecko.driver", "D:\\students\\msm\\geckodriver.exe");
		System.setProperty("webdriver.gecko.driver", "c:\\Users\\GAMMA-LF\\AppData\\Local\\Temp\\java\\geckodriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D0%B3%D0%BE%D1%80%D0%BE%D0%B4%D0%BE%D0%B2_%D0%A0%D0%BE%D1%81%D1%81%D0%B8%D0%B8");
		
		List<WebElement> searchElements = driver.findElements(By.xpath("//table[@class='standard sortable jquery-tablesorter']/tbody/tr/td[3]"));

		System.out.println("Page title is: " + driver.getTitle());
		System.out.println("Paragraphs: " + searchElements.size());
		
		//for(int i=6; i<searchElements.size(); i+=7){
		for(int i=0; i<searchElements.size(); i++){
			listCities.add(searchElements.get(i).getText());
			System.out.println(searchElements.get(i).getText());
		}

		driver.quit();

	}
}
