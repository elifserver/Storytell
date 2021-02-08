package com.framework.pages;

import com.framework.utils.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthorScreen extends Base {

    //<editor-fold desc="Elements">
    @AndroidFindBy(id="grit.storytel.app:id/buttonFilter")
    private MobileElement filterIcon;

    @AndroidFindBy(id="grit.storytel.app:id/buttonDone")
    private MobileElement doneButton;

    @AndroidFindBy(id="grit.storytel.app:id/textViewNoResult")
    private MobileElement emptyResultSetWarning;
    //</editor-fold>

    public AuthorScreen goToFilters()
    {
        wait.until(ExpectedConditions.visibilityOf(filterIcon)).click();
        return this;
    }

    public AuthorScreen removeFromFilters(String text){
        MobileElement element = driver.findElement("xpath","//android.widget.CheckBox[@text='" + text + "']");
        if (element.getAttribute("checked").equals("true")) {
            element.click();
            doneButton.click();
        }
        return new AuthorScreen();
    }

    public void assertYouGetExpectedWarning(String expectedWarning){
        String messageOnThePage = wait.until(ExpectedConditions.visibilityOf(emptyResultSetWarning)).getText();
        Assert.assertEquals(messageOnThePage, expectedWarning );
    }


}
