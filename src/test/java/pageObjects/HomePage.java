package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{

//page objects consist of3 things
//1-constructor
public HomePage(WebDriver driver)
{
	
	super(driver);

}

//2-locators
@FindBy(xpath="//span[normalize-space()='My Account']")
		WebElement linkMyAccount;

@FindBy(xpath="//a[text()='Register']")
WebElement linkRegister;

@FindBy(xpath="//a[text()='Login']")
WebElement linkLogin;


//3-method to perform action on the webElement
public void clickMyAccount()
{
	linkMyAccount.click();
}
public void clickOnRegister()
{
	linkRegister.click();
}

public void clickOnLogin()
{
	linkLogin.click();
}
}
