package com.framework.pages;

import com.framework.utils.Base;
import com.framework.utils.CommonFunctions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class StoryTelWelcomeScreen extends Base {

    @AndroidFindBy(id = "grit.storytel.app:id/textview_title")
    private MobileElement pageHeader;

    CommonFunctions commonFunctions = new CommonFunctions();

    public void thenClickPhoneBackButton() {
        commonFunctions.clickBackButton();
    }

    public BookDetailsScreen afterWelcomePageOpensGoBackToBookDetailsPage() throws InterruptedException {
        Thread.sleep(9000);
        commonFunctions.clickBackButton();
        return new BookDetailsScreen();
    }
}

