package testBase;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;//Log4j

import org.apache.logging.log4j.LogManager;//Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;



public class BaseClass
{
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
		

	
		@BeforeClass(groups = {"Sanity","Regression","Master"})
		@Parameters({"os","browser"})
		public void setup(String os,String br) throws IOException
	 {
			FileReader file=new FileReader("./src/test/resources/config.properties");
		     p=new Properties();
		     p.load(file);
		   
		     
	     logger=LogManager.getLogger(this.getClass());//log4j2
	     

	    
	     if(p.getProperty("execution_env").equalsIgnoreCase("remote"))//execution_env came from config.properties file
	     {
	    	 //Setting Up the Remote Machine Details
	    	DesiredCapabilities capabilities= new DesiredCapabilities();
	    	//setting os
	    	 try {
	             // Grid URL (modify if needed)
	             URL gridUrl = new URL("http://localhost:4444/wd/hub");

	             // Browser options
	             switch (br.toLowerCase()) {
	                 case "chrome":
	                     ChromeOptions chromeOptions = new ChromeOptions();
	                     chromeOptions.setCapability("platformName", "linux"); // Ensure correct platform
	                     chromeOptions.setBrowserVersion("134.0"); // Ensure correct browser version
	                     driver = new RemoteWebDriver(gridUrl, chromeOptions);
	                     break;

	                 case "firefox":
	                     FirefoxOptions firefoxOptions = new FirefoxOptions();
	                     firefoxOptions.setCapability("platformName", "linux");
	                     firefoxOptions.setBrowserVersion("136.0");
	                     driver = new RemoteWebDriver(gridUrl, firefoxOptions);
	                     break;

	                 case "edge":
	                     EdgeOptions edgeOptions = new EdgeOptions();
	                     edgeOptions.setCapability("platformName", "linux");
	                     driver = new RemoteWebDriver(gridUrl, edgeOptions);
	                     break;

	                 default:
	                     System.out.println("No matching browser found for remote execution.");
	                     return;
	             }

	         } catch (Exception e) {
	             logger.error("Error connecting to Selenium Grid: " + e.getMessage());
	             e.printStackTrace();
	         }
	     
	    	
	     } else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
	         // Local Execution
	         switch (br.toLowerCase()) {
	             case "chrome":
	                 WebDriverManager.chromedriver().setup();
	                 driver = new ChromeDriver();
	                 break;
	             case "firefox":
	                 WebDriverManager.firefoxdriver().setup();
	                 driver = new FirefoxDriver();
	                 break;
	             case "edge":
	                 WebDriverManager.edgedriver().setup();
	                 driver = new EdgeDriver();
	                 break;
	             default:
	                 System.out.println("Invalid browser");
	                 return;
	         }
	     }
	     
		 driver.manage().deleteAllCookies();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 driver.get(p.getProperty("URL"));//reading URL from properties file
		 driver.manage().window().maximize();
	 }
	 @AfterClass (groups = {"Sanity","Regression","Master"})
	 public void tearDown() throws InterruptedException
	 {
		 
		 if (driver != null) //here if clause written,it will check if the browser is not closed,if so then it will close
		 {
		 
			 driver.quit();
		 }
		 
	 }
	 public String randomString()
		{
			String genaredString=RandomStringUtils.randomAlphabetic(3);
		 return genaredString;
		}
	 public String randomNumber()
	 {
		 
		String generatedNumber= RandomStringUtils.randomNumeric(4);
	    
	    return generatedNumber;
	 }
	 
	 public String randomAlphaNumeric()
	 {
		 String genaredString=RandomStringUtils.randomAlphabetic(3); 
		 String generatedNumber= RandomStringUtils.randomNumeric(4);
	  return (genaredString+"$"+generatedNumber);
	 }
	 
	 public String captureScreen(String tname) throws IOException
	 {
		 String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 TakesScreenshot takeScreenshot=(TakesScreenshot)driver;
		 File sourceFile=takeScreenshot.getScreenshotAs(OutputType.FILE);
		 
		 String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+ ".png";
		 File tageFile=new File(targetFilePath); 
		 
		 sourceFile.renameTo(tageFile);
		 
		 return targetFilePath;
	 }
	 
}
