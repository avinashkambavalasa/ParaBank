package ParaBankPS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


public class BasePB {
	
		public static WebDriver driver;	
		public static Properties prop;
	
		public BasePB() throws IOException {
			try {
				 prop = new Properties();
				 FileInputStream ip = new FileInputStream("D:/Avinash/Selenium/Workspace/ParaBank/src/ParaBankPS/configuration.properties");
				 prop.load(ip);
			}catch (FileNotFoundException e) {
					 e.printStackTrace();
			}catch (IOException e) {
					 e.printStackTrace();
			}
		
			System.setProperty(Constants.key, Constants.value);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("incognito");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);		
			
			String implicittime = prop.getProperty("implicit_wait_time");
			long implicitwaittime = Long.parseLong(implicittime);
		
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(implicitwaittime,TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Constants.Page_Load_TimeOut, TimeUnit.SECONDS);
			 
		}
		
		
		public static void initialization() throws InterruptedException {
			
			String baseurl = prop.getProperty("URL");
			driver.get(baseurl);
		
			driver.findElement(By.xpath("//input[@name='username' and @class='input']")).sendKeys(prop.getProperty("username"));
			driver.findElement(By.xpath("//input[@name='password' and @class='input']")).sendKeys(prop.getProperty("password"));
			driver.findElement(By.xpath("//input[@value='Log In' and @type='submit']")).click();
			
		}
		
		
}





/*
		//Click on update contact info
		WebElement link_update_contact_info = 
				driver.findElement(By.linkText("Update Contact Info"));
		
		link_update_contact_info.click();
		
		//Locators for all the fields from Contact Info
		//WebElement txtbx_firstname = driver.findElement(By.id("customer.firstName"));
	//	WebElement txtbx_lastname = driver.findElement(By.id("customer.lastName"));
		//WebElement txtbx_add_street = driver.findElement(By.id("customer.address.street"));
		//WebElement txtbx_add_city = driver.findElement(By.id("customer.address.city"));
		//WebElement txtbx_add_state = driver.findElement(By.id("customer.address.state"));
		//WebElement txtbx_add_zip_code = driver.findElement(By.id("customer.address.zipCode"));
		//WebElement txtbx_phn_number = driver.findElement(By.id("customer.phoneNumber"));
		

		//System.out.println(txtbx_firstname.getAttribute("value"));
		
		//Find Elements
		List<WebElement> txtbx_collection = 
				driver.findElements(By.xpath("//input[@type='text']"));
		
		String[] arr_input = 
			{"Akash","Tyagi","add1","add2","add3","add4","976983939"};
		
		for(int i=0;i<txtbx_collection.size();i++) {
			txtbx_collection.get(i).clear();
			txtbx_collection.get(i).sendKeys(arr_input[i]);
		}
		WebElement sendpayment = driver.findElement(By.xpath("//input[@value='Update Profile']"));
		/*
		txtbx_collection.get(0).sendKeys("Akash");
		txtbx_collection.get(1).sendKeys("Tyagi");
		txtbx_collection.get(2).sendKeys("add1");
	*/
		