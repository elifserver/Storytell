package com.framework.pages;

import com.framework.utils.Base;
import com.framework.utils.CommonFunctions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.junit.Assert;


public class WelcomeScreen extends Base {

    @AndroidFindBy(id = "grit.storytel.app:id/carousel_description")
    public MobileElement listView;

    @AndroidFindBy(id = "grit.storytel.app:id/create_account_preview")
    public MobileElement tryItOut;

    CommonFunctions commonFunctions = new CommonFunctions();

    public WelcomeScreen swipeOnListViewForTimes(int loopCount) {
        commonFunctions.swipeForAPredefinedNumberOfTimesOnAnElement(listView, 3);
        return this;
    }

    public CountrySelectionScreen proceedToCountryScreen() {
        tryItOut.click();
        return new CountrySelectionScreen();
    }


}
