package testCases;

import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;
public class TC003_LoginDDT extends BaseClass
{

	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = "Datadriven")//2nd part is the reference we have given in which package DataProviders class is created. 
	public void verify_loginDDT(String email,String pwd,String expected)
	{
		logger.info("*****Starting TC003_LoginDDT*****");
		try
		{
		//Homepage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickOnLogin();
		
		//Login
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickSubmit();
		
		//MyAccount
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		/* the data mentioned in the Opencart_TestData.xlsx under Testdata package,cant be access directly.For that we need to take the value to
		 DataProviders class using excelfunction which we mentioned in ExcelUtility class under Utilities package.
		 *Lets say there are multiple excel or testdata file/sheet is there.For that under the same DataProviders class,for each sheet we have to  
		 provide unique dataprovider name.
		 That dataprovider name we need to use here in test case under @Test
		 */
		
		//Assertion should be the last statement becaause immediate statement wont execute after assertion.Thatswhy it should be always in last
		
		/*here we have both valid and invalid scenarioes are there means 
		 * Data is valid--login success--test pass-- logout
		 *                login fail--test fail-- since login fail we wont see logout button,it will be on the same page
		 
		 
		 * Data is invalid--login success--test fail-- logout
		 *                  login fail--test pass-- since login fail we wont see logout button,it will be on the same page.
		 *                  
		 *     (based on the above scenario we have implemented the code as below)
		 * 
		 *  */            
		if (expected.equalsIgnoreCase("valid"))
		{
			if (targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else
				
			{
				
				Assert.assertTrue(false);
			}
		}
		else if(expected.equalsIgnoreCase("invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
			
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("*****Ending TC003_LoginDDT*****");
	}

	//once the program is finished we have to add this testcase in XML  
}
