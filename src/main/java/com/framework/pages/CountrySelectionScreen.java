package com.framework.pages;

import com.framework.utils.Base;
import com.framework.utils.CommonFunctions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CountrySelectionScreen extends Base {
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Your country']")
    private MobileElement pageHeader;

    @AndroidFindBy(id = "grit.storytel.app:id/buttonDone")
    private MobileElement doneButton;

    CommonFunctions commonFunctions = new CommonFunctions();

    public CountrySelectionScreen scrollToCountry(String countryName) {
        String locatorCountry = "//android.widget.RadioButton[@text=\'" + countryName + "\']";
        commonFunctions.scrollUntilElementIsVisible("xpath",locatorCountry).click();
        return this;
    }

    public LanguageSelectionScreen selectCountryAndProceed(){
        doneButton.click();
        return new LanguageSelectionScreen();
    }

    public void checkPageHeader(String expected){

        Assert.assertEquals(expected,wait.until(ExpectedConditions.visibilityOf(pageHeader)).getText());
    }

}
