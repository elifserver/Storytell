package com.framework.pages;

import com.framework.utils.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConsentScreen extends Base {
    //<editor-fold desc="Elements">
    @AndroidFindBy(id="grit.storytel.app:id/button_positive")
    private MobileElement acceptButton;

    @AndroidFindBy(id="grit.storytel.app:id/button_negative")
    private MobileElement marketingConsentDenyButton;
    //</editor-fold>

    public ConsentScreen andAcceptTermsAndConditions(){
        wait.until(ExpectedConditions.visibilityOf(acceptButton)).click();
        //acceptButton.click();
        return this;
    }

    public StoryTelWelcomeScreen onlyExceptMarkettingConsent(){
        marketingConsentDenyButton.click();
        return new StoryTelWelcomeScreen();
    }
}
