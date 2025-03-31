package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage
{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtfirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
    WebElement txtlastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtemail;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txttelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtconfirmPassword;
	
	@FindBy (xpath="//input[@name='agree']")
	WebElement chkprivacyPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	WebElement confirmMsg;
	
	
	public void setFirstName(String fname)
	{
		txtfirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtlastName.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtemail.sendKeys(email);
		
	}
	
	public void setTelephone(String phoneNo)
	{
		txttelephone.sendKeys(phoneNo);
	}
	
	public void setPassword(String pwd)
	{
		txtpassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd)
	{
		
		txtconfirmPassword.sendKeys(pwd);
	}
	
	public void setPrivacyPolicy()
	{
		
		chkprivacyPolicy.click();
	}
	public void clickContinue()
	{
		//sol1
		btnContinue.click();
		

		
	
	
		/*
		 * 
		 * if the above click opeartion doesnt work then we can follow any of the below approch
		 solution 2 using submit button 
		 	btnContinue.submit();
		 	
		 
		 Solution 3  using actions class method
	    Actions act=new Actions(driver);
		act.moveToElement(btnContinue).perform();
		
		
		solution 4  java script 
        JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", btnContinue);
		
		solution 5 keyboard action 
		btnContinue.sendKeys(Keys.RETURN);
		
		soluion 5
		
			WebDriverWait myWait= new WebDriverWait(driver, Duration.ofSeconds(10));
	myWait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
				
		 */
		
  	
	}
	public String getConfirmationMsg()
	  {
		String msg=confirmMsg.getText();
		return msg;
	  }
		
	
}
