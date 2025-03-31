	package testCases;
	
	import org.testng.Assert;
	import org.testng.annotations.Test;
	
	import pageObjects.AccountRegistrationPage;
	import pageObjects.HomePage;
	import testBase.BaseClass;
	public class TC001_AccountRegistrationTest extends BaseClass
	{
	
	
	 @Test(groups = {"Regression","Master"})
	 public void verify_account_Registration()
	 {
		 logger.info("*** test case execution started***");
		 try {
		 HomePage hp=new HomePage(driver);
		 hp.clickMyAccount();
		 logger.info("*clicked on my account *");
		 hp.clickOnRegister();
		 logger.info("*clicked on register links *");
		 
		 AccountRegistrationPage accreg=new AccountRegistrationPage(driver);
		 logger.info("Providing customer details");
		 accreg.setFirstName(randomString().toUpperCase());
		 accreg.setLastName(randomString().toLowerCase());
		 accreg.setEmail(randomString()+"@email.com");
		 accreg.setTelephone(randomNumber());
		 
		 String pwd=randomAlphaNumeric();
		 
		 accreg.setPassword(pwd);
		 accreg.setConfirmPassword(pwd);
		 accreg.setPrivacyPolicy();
		 accreg.clickContinue();
		 
		 logger.info("Validating expected message");
		 

			Thread.sleep(3000);
		 String pageTitle=accreg.getConfirmationMsg();
		 
//		 if (pageTitle.equals("Your Account Has Been Created!!!"))
//		 {
//			 Assert.assertTrue(true);
//		 }
//		 else
//			 
//		 {
//			logger.error("Test failed");
//			logger.debug("Debug logs..");
//			Assert.assertFalse(false);
//		 }
		 Assert.assertEquals(pageTitle, "Your Account Has Been Created!");	
		 
		 }
		 catch (Exception e) 
		 {
			logger.error("Test failed"+e);
			logger.debug("Debug logs.."+e);
			Assert.fail();
			
		}
		 logger.info("test case execution finished***");
	 }
	
	}
