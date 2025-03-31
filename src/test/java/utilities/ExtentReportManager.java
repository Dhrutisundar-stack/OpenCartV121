package utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
public ExtentSparkReporter sparkReporter;
public ExtentReports extent;
public ExtentTest test;
String repName;

public void onStart(ITestContext testContext)
{  
	/*this 3 line is the actual code to geneate timestam ,instaed of this i have written the single line @39,which is also fine.
	 * SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	Date dt=new Date();
	String currentdatetimestamp=df.format(dt);
		*/	
	
	String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
	repName="Test-Report-"+ timestamp + ".html";
	sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);//specify location of report
	
	sparkReporter.config().setDocumentTitle("opencart Automation Report");//Title of the report
	sparkReporter.config().setReportName("opencart Functional Testing");//name of the report
	sparkReporter.config().setTheme(Theme.DARK);
	
	extent=new ExtentReports();
	extent.attachReporter(sparkReporter);
	extent.setSystemInfo("Application", "opencart");
	extent.setSystemInfo("Module", "Admin");
	extent.setSystemInfo("Sub Module", "Customers");
	extent.setSystemInfo("User Name", System.getProperty("user.name"));
	extent.setSystemInfo("Environment", "QA");
	
	String os=testContext.getCurrentXmlTest().getParameter("os");
	extent.setSystemInfo("Opearating System", os);
	
	String browser=testContext.getCurrentXmlTest().getParameter("os");
	extent.setSystemInfo("browser", browser);
	
	List<String>includedGroups= testContext.getCurrentXmlTest().getIncludedGroups();
	if (!includedGroups.isEmpty())
	{
		
		extent.setSystemInfo("Groups",includedGroups.toString() );
	}
}
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());//to display groups in report
		test.log(Status.PASS, result.getName()+"got successfully executed");
	}
	public void onTestFailure(ITestResult result) 
	{
		test=extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());//to display groups in report 
		test.log(Status.FAIL, result.getName()+"got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try
		{
			String imgPath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(IOException e1) 
		{
			e1.printStackTrace();
		}
	  }
	
	  public void onTestSkipped(ITestResult result) {
			test=extent.createTest(result.getClass().getName());
			test.assignCategory(result.getMethod().getGroups());//to display groups in report 
			test.log(Status.SKIP, result.getName()+"got skipped");
			test.log(Status.INFO, result.getThrowable().getMessage());
			
			
		  }
	
	  
	  public void onFinish(ITestContext context) {
		  extent.flush();
		  //the below code till catch block in this section will open the report directly.No need for us to go and open the report manually.
		  String pathOfExtentReport=System.getProperty("user.dir")+"\\reports"+repName;
		  File extentReport=new File(pathOfExtentReport);
		  //we have written the try catch block because sometime there will be no reports due to some issue,so to handle that we have mention
		  //it on catch block where as try block contains the the code to open the report autometically on the desktop.

		  
		  //if you want to open the re[ort autometically then include this below piece of code.Some issue is there with the code which unable to resolve hence commeneted out.Bt this is the deneric way.
		  //		  try
//		  {
//			  
//			 
//			    Desktop.getDesktop().browse(extentReport.toURI());
//		  }
//		  catch(IOException e)
//		  {
//			  e.printStackTrace();
//			  
//		  }
//	  
/*		 //  This below peace of code isresponsible for create the email message 
try{
	  URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
	  ImageHtmlEmail email=new ImageHtmlEmail();
	  email.setDataSourceResolver(new DataSourceUrlResolver(url));
	  email.setHostName("smtp.googlemail.com");
	  email.setSmtpPort(465);
	  email.setAuthenticator(new DefaultAuthenticator("dsbiswal90@gmail.com", "Dhruti@123"));
	  email.setSSLOnConnect(true);
	  email.setFrom("dsbiswal90@gmail.com");//sender
	  email.setSubject("Test Results");
	  email.setMsg("Please find attached report...");
	  email.addTo("dhrutisundarbiswal@gmail.com");//receiver
	  email.attach(url, "extent report", "please check report...");
	  email.send();
 }
catch(Exception e)
{
	e.printStackTrace();
}
		 
*/
}
}
	  


