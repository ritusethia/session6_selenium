package VariousConcept;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Hw2 {
	WebDriver driver;
	String browser;
	String url;
	By UserName_field = By.xpath("//input[@name='username']");
	By Password_field = By.xpath("//input[@name='password']");
	By Signin_field = By.xpath("//button[@name='login']");
	By customer_field = By.xpath("//span[text ()= 'Customers']");
	By add_customer_field = By.xpath("//a[text ()= 'Add Customer']");
	By fullname_field = By.xpath("//input[@id = 'account']");
	By company_field = By.xpath("//select[@id = 'cid']");


	public void readconfig() {
//		FileReader/ BufferReader/InputStream/Scanner;
		try {
			InputStream inputStream = new FileInputStream("src\\test\\java\\config\\config.properties");
			Properties prop = new Properties();
			prop.load(inputStream);
			browser = prop.getProperty("browser");
			url = prop.getProperty("url");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void init() throws IOException {
		readconfig();
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);

		driver.get("https://techfios.com/billing/?ng=login/after/dashboard");

	}

	@Test
	public void newconcepts() throws InterruptedException {
		driver.findElement(UserName_field).sendKeys("demo@techfios.com");
		driver.findElement(Password_field).sendKeys("abc123");
		driver.findElement(Signin_field).click();
		Thread.sleep(2000);
		driver.findElement(customer_field).click();
		driver.findElement(add_customer_field).click();
//		Assert.assertEquals(actual, expected);
		driver.findElement(fullname_field).sendKeys("test" + numberGenerator(999));
		selectdropdown(driver.findElement(company_field), "Tesla");

	}

	public void selectdropdown(WebElement element, String visibletext) {
		Select sel = new Select(element);

		sel.selectByVisibleText(visibletext);

	}

	public int numberGenerator(int bound) {
		Random rnd = new Random();
		return rnd.nextInt(999);

	}
}
