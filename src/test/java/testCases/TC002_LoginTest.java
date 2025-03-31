package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass

{
@Test(groups = { "Sanity","Master"})
public void verify_login()
{
logger.info("*****Starting TC002_LoginTest ");

try
{
//Home page
HomePage hp=new HomePage(driver);
hp.clickMyAccount();
hp.clickOnLogin();
//Login Page
LoginPage lp=new LoginPage(driver);
lp.setEmail(p.getProperty("emailid"));
lp.setPassword(p.getProperty("password"));
lp.clickSubmit();

MyAccountPage macc=new MyAccountPage(driver);
boolean targetPage= macc.isMyAccountPageExists();
Assert.assertEquals(targetPage, true,"Login Failed");//AssertEquals max can take 3 parameters ,if the actual vs expected false last parametre will display
//Assert.assertTrue(targetPage);
}
catch(Exception e)
{
Assert.fail();	
}
logger.info("*****Finished TC002_LoginTest ");

}
}
