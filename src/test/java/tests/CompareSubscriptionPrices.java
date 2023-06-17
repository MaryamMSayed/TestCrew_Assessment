package tests;

import constants.ConstantVariables;
import data.LoadProperties;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.SubscriptionsPage;
import tests_helper.Helper;



import static tests.BaseClass.driver;

public class CompareSubscriptionPrices {

    String ksa = LoadProperties.country.getProperty("country1");

    String bahrain = LoadProperties.country.getProperty("country2");
    String kuwait = LoadProperties.country.getProperty("country3");

    @BeforeClass
    public void startDriver() {
        BaseClass.initialize();
        BaseClass.openurl(ConstantVariables.URl);
    }

    @Test(description = "Given user at home " +
            "When i click on Country selection button" +
            "And i select country named []"
            + "Then Packages names will be displayed accordingly"
            + "And Packages currencies will be changed to the selected country's currency", priority = 1)
    @Description("User will select a country name [KSA] and validate displayed packages and currency ")
    @Severity(SeverityLevel.CRITICAL)
    public void selectKSA() {
        new SubscriptionsPage(driver).openCountrySelectionPopUp().selectedCountry(ksa);
    }

   @Test(description = "Given user at home " +
            "When i click on Country selection button" +
            "And i select country named []"
            + "Then Packages names will be displayed accordingly"
            + "And Packages currencies will be changed to the selected country's currency", priority = 2)
    @Description("User will select a country name [bahrain] and validate displayed packages and currency ")
    @Severity(SeverityLevel.CRITICAL)
    public void selectBahrain() {
        new SubscriptionsPage(driver).openCountrySelectionPopUp().selectedCountry(bahrain);
    }

    @Test(description = "Given user at home " +
            "When i click on Country selection button" +
            "And i select country named []"
            + "Then Packages names will be displayed accordingly"
            + "And Packages currencies will be changed to the selected country's currency", priority = 3)
    @Description("User will select a country name [Kuwait] and validate displayed packages and currency ")
    @Severity(SeverityLevel.CRITICAL)
    public void selectKuwait() {
        new SubscriptionsPage(driver).openCountrySelectionPopUp().selectedCountry(kuwait);
    }

    @AfterMethod
    public void screenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Failed!");
            System.out.println("Taking Screenshot....");
            Helper.captureScreenshot(driver, result.getName());

        }
    }

    @AfterClass
    public void stopDriver() {

        BaseClass.quit();
    }
}
