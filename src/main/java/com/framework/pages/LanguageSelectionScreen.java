package com.framework.pages;

import com.framework.utils.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.junit.Assert;
import org.openqa.selenium.By;

public class LanguageSelectionScreen extends Base {

    @AndroidFindBy(id = "grit.storytel.app:id/buttonDone")
    public MobileElement doneButton;

    /**
     * English and the selected country's own language is coming as selected
     * To get rid of the countries own language this uncheck method can be used
     *
     * @param lang
     */
    public LanguageSelectionScreen clickOnLanguageToUncheck(String lang) {
        MobileElement element = driver.findElement(By.xpath("//android.widget.CheckBox[@text=\'" + lang + "\']"));
        if (element.getAttribute("checked").equalsIgnoreCase("true")) {
            element.click();
        }
        return this;
    }

    public void andProceedForBookSelection() {
        doneButton.click();
    }

    public void isLanguageUncheked(String lang){
        MobileElement element = driver.findElement(By.xpath("//android.widget.CheckBox[@text=\'" + lang + "\']"));
        Assert.assertTrue(element.getAttribute("checked").equalsIgnoreCase("false"));
    }


}
