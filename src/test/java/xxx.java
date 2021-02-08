import com.framework.pages.CountrySelectionScreen;
import com.framework.pages.WelcomeScreen;
import com.framework.utils.Base;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class xxx extends Base {

    WelcomeScreen welcomeScreen;
    CountrySelectionScreen countrySelectionScreen;


    @Test
    public void proceedCheckFromWelcomeScreenToToCountryScreen(){
        welcomeScreen = new WelcomeScreen();


        JSONObject testObj = testDataObject;
        int swipeCount = Integer.parseInt(testObj.getJSONObject("newUserTriesToLikeABook").get("swipeCount").toString());
        String country = testObj.getJSONObject("newUserTriesToLikeABook").get("country").toString();
        String countryPageHeader = testObj.getJSONObject("pageHeaders").get("countryPage").toString();

        welcomeScreen.swipeOnListViewForTimes(swipeCount)
                .proceedToCountryScreen()
                .checkPageHeader(countryPageHeader);
    }


    @Test
    public void isLanguageUncheckSuccessful() {
        welcomeScreen = new WelcomeScreen();
        JSONObject testObj = testDataObject;
        int swipeCount = Integer.parseInt(testObj.getJSONObject("newUserTriesToLikeABook").get("swipeCount").toString());
        String country = testObj.getJSONObject("newUserTriesToLikeABook").get("country").toString();
        String language = testObj.getJSONObject("newUserTriesToLikeABook").get("language").toString();

        welcomeScreen.swipeOnListViewForTimes(swipeCount)
                .proceedToCountryScreen()
                .scrollToCountry(country).selectCountryAndProceed()
                .clickOnLanguageToUncheck(language)
                .isLanguageUncheked(language);
    }
}
