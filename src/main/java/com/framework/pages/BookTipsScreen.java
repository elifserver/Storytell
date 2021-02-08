package com.framework.pages;
import com.framework.utils.Base;
import com.framework.utils.CommonFunctions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import java.util.HashSet;

public class BookTipsScreen extends Base {

    //<editor-fold desc="Elements">
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Feelgood\"]/androidx.recyclerview.widget.RecyclerView")
    public MobileElement feelGoodSection;

    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    public MobileElement sideMenu;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Search']")
    public MobileElement search;
    //</editor-fold>

    String bookTypeSection = testDataObject.getJSONObject("newUserTriesToLikeABook").get("bookSection").toString();
    String locatorOfTheVisibleList = "//android.view.ViewGroup[@content-desc='" + bookTypeSection + "']/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup";
    CommonFunctions commonFunctions = new CommonFunctions();
    private String bookName;

    public  String getBookName(){
        return bookName;
    }

    public BookTipsScreen openTheSideMenu(){
        sideMenu.click();
        return this;
    }

    public SearchScreen chooseSearchFromSideMenu(){
        search.click();
        return new SearchScreen();
    }

    public BookTipsScreen scrollToBookTypeSection(String locator) throws InterruptedException {
        commonFunctions.scrollUntilElementIsVisible("accessibility id", locator);
        return this;
    }

    public BookTipsScreen swipeToBook(int bookNumber) {
        HashSet<MobileElement> bookSet = new HashSet<>();
        MobileElement newElement;
        int i=0;
        while (bookSet.size()<=bookNumber) {
            i++;
            try{
                newElement = driver.findElements("xpath", locatorOfTheVisibleList+"["+i+"]"+"/android.widget.ImageView[1]").get(0);
                bookSet.add(newElement);
                if(bookSet.size() == bookNumber) {
                    newElement.click();
                    break;
                }
                bookName = newElement.getAttribute("content-desc");
                commonFunctions.swipeForAPredefinedNumberOfTimesOnAnElement(feelGoodSection, 1);
            }
            catch (Exception e){
                commonFunctions.swipeForAPredefinedNumberOfTimesOnAnElement(feelGoodSection, 1);
                i=0;
                continue;
            }
        }
        return this;
    }

    public BookDetailsScreen proceedToSeeTheDetailsOfTheBook(){
        return new BookDetailsScreen();
    }

}
