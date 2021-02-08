package com.framework.pages;

import com.framework.utils.Base;
import com.framework.utils.CommonFunctions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SearchScreen extends Base {
    @AndroidFindBy(id = "grit.storytel.app:id/autoCompleteTextView")
    private MobileElement searchField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='The Adventures of Harry Stevenson']")
    public MobileElement searchedBook;

    CommonFunctions commonFunctions = new CommonFunctions();

    private String bookName;

    public SearchScreen searchFor(String searchCriteria){
        searchField.click();
        searchField.sendKeys(searchCriteria);
        return this;

    }

    /**
     *
     * @param bookName
     */
    public void scrollTillBookIsVisibleAndSelect(String bookName){
        this.bookName = bookName;
        String locator = "//android.widget.TextView[@text='" + bookName + "']";
        commonFunctions.clickBackButton();
        commonFunctions.scrollUntilElementIsVisible("xpath",locator).click();
    }


}
