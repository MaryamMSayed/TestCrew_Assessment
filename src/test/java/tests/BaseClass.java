package tests;

import constants.ConstantVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BaseClass {
    public static WebDriver driver=null;
    public static void initialize()
    {
        if(driver==null)
        {
            if(ConstantVariables.browserName.equalsIgnoreCase("chrome"))
            {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
                driver= new ChromeDriver();
            }
            else if (ConstantVariables.browserName.equalsIgnoreCase("firefox"))
            {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver");
                driver= new FirefoxDriver();
            }
        }
        assert driver != null;
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);


    }
    public static void quit()
    {
        driver.quit();
        driver=null; // destroy the driver object after quit operation
    }
    public static void close()
    {
        driver.close();
        driver=null;  // destroy the driver object after quit operation
    }
    public  static void openurl(String URL)
    {
        driver.get(URL);
    }
}
