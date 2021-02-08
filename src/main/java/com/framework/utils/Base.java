package com.framework.utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;

public class Base {

    public static ExtentReports extent;
    public static ExtentTest logger;
    public static AppiumDriver<MobileElement> driver;
    public static JSONObject testDataObject;
    public static WebDriverWait wait;


    public Base() {

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @BeforeSuite
    public void beforeSuite() {

        extent = new ExtentReports("Reports/index.html", true);
        extent.addSystemInfo("TESTERNAME", "ELF");
        extent.addSystemInfo("ReportNumber", "101");
        extent.loadConfig(new File("./extent-config.xml"));
    }

    @AfterSuite
    public void afterSuite() {
        extent.flush();
    }

    @BeforeClass
    public void testDataPullForTheClass() throws FileNotFoundException {
        File testDataFile = new File("src/main/resources/TestData.json");
        FileInputStream testDataStream = new FileInputStream(testDataFile);
        JSONTokener tokener = new JSONTokener(testDataStream);
        testDataObject = new JSONObject(tokener);
    }


    @Parameters({"PlatformName", "DeviceName"})
    @BeforeMethod
    public void Setup(String platform, String device, Method method) throws IOException {
        logger = extent.startTest(method.getName());
        logger.log(LogStatus.INFO, "Test has been done on:: " + platform);
        File fileConfig = new File("src/main/resources/app.properties");
        FileInputStream stream = new FileInputStream(fileConfig);
        Properties props = new Properties();
        props.load(stream);
        URL urlInconfigFile = new URL(props.getProperty("url"));

        if (platform.equalsIgnoreCase("Android")) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, device);
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
            //caps.setCapability(MobileCapabilityType.APP, props.getProperty("androidApp"));
            caps.setCapability("appPackage","grit.storytel.app");
            caps.setCapability("appActivity","grit.storytel.app.MainActivity");
            driver = new AndroidDriver<MobileElement>(urlInconfigFile, caps);
        } else if (platform.equalsIgnoreCase("iOS")) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, device);
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
            caps.setCapability(MobileCapabilityType.APP, props.getProperty("iOSApp"));
            caps.setCapability("appPackage","grit.storytel.app");
            caps.setCapability("appActivity","grit.storytel.app.MainActivity");
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            driver = new IOSDriver<MobileElement>(urlInconfigFile, caps);
        }
         wait = new WebDriverWait(driver, 10);
    }

    @AfterMethod
    public void afterMethod(ITestResult result, Method method) throws IOException {
        File shot = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(shot, new File("Screenshots/" + method.getName() + ".png"));

        if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(LogStatus.PASS, "!!!!!successfull!!!!!!");
            logger.log(LogStatus.INFO, logger.addScreenCapture("../Screenshots/" + method.getName() + ".png"));
        } else if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(LogStatus.FAIL, "Test has problems. Check please!!");
            logger.log(LogStatus.FAIL, result.getThrowable());
            logger.log(LogStatus.INFO, logger.addScreenCapture("../Screenshots/" + method.getName() + ".png"));
        }
        extent.endTest(logger);
        driver.quit();
    }
}
