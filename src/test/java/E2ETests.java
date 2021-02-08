import com.framework.utils.Base;
import com.framework.pages.*;
import org.json.JSONObject;
import org.testng.annotations.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class E2ETests extends Base {
    //<editor-fold desc="Page Initialization">
    private static WelcomeScreen welcomeScreen;
    private BookTipsScreen bookTipsScreen;
    private BookDetailsScreen bookDetailsScreen;
    //</editor-fold>

    @Test
    public void newUserTriesToLikeABook() throws InterruptedException {
        //<editor-fold desc="Page Initialization">
        welcomeScreen = new WelcomeScreen();
        bookTipsScreen = new BookTipsScreen();
        bookDetailsScreen = new BookDetailsScreen();
        //</editor-fold>

        //<editor-fold desc="Test Data Feed">
        JSONObject testObj = testDataObject;
        int swipeCount = Integer.parseInt(testObj.getJSONObject("newUserTriesToLikeABook").get("swipeCount").toString());
        String country = testObj.getJSONObject("newUserTriesToLikeABook").get("country").toString();
        String language = testObj.getJSONObject("newUserTriesToLikeABook").get("language").toString();
        String bookSection = testObj.getJSONObject("newUserTriesToLikeABook").get("bookSection").toString();
        int bookIndexToSelect = Integer.parseInt(testObj.getJSONObject("newUserTriesToLikeABook").get("bookIndexToSelect").toString());
        String searchWord = testObj.getJSONObject("newUserTriesToLikeABook").get("searchWord").toString();
        String bookName = testObj.getJSONObject("newUserTriesToLikeABook").get("bookName").toString();
        String filter = testObj.getJSONObject("newUserTriesToLikeABook").get("filter").toString();
        String warning = testObj.getJSONObject("newUserTriesToLikeABook").get("warning").toString();
        //</editor-fold>

        welcomeScreen.swipeOnListViewForTimes(swipeCount)
                .proceedToCountryScreen()
                .scrollToCountry(country).selectCountryAndProceed()
                .clickOnLanguageToUncheck(language).andProceedForBookSelection();

        bookTipsScreen.scrollToBookTypeSection(bookSection)
                .swipeToBook(bookIndexToSelect).proceedToSeeTheDetailsOfTheBook();

        assertThat(bookTipsScreen.getBookName().equalsIgnoreCase(bookDetailsScreen.getBookName()));

        bookDetailsScreen.likeTheBook()
                .createAccount().andAcceptTermsAndConditions().onlyExceptMarkettingConsent()
                .afterWelcomePageOpensGoBackToBookDetailsPage()
                .likeTheBookAndCheckIfBookIsSavedToBookShelve()
                .goBackToBookTipsScreen();

        bookTipsScreen.openTheSideMenu()
                .chooseSearchFromSideMenu().searchFor(searchWord)
                .scrollTillBookIsVisibleAndSelect(bookName);

        bookDetailsScreen.assertYouPickTheCorrectBook(bookName)
                .proceedForCheckingAuthorDetails()
                .goToFilters().removeFromFilters(filter)
                .assertYouGetExpectedWarning(warning);
    }
}

