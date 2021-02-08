package com.framework.pages;

import com.framework.utils.Base;
import com.github.javafaker.Faker;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CreateAccountScreen extends Base{
    //<editor-fold desc="Elements">
    @AndroidFindBy(id = "grit.storytel.app:id/edit_text_email")
    private MobileElement accounEmail;

    @AndroidFindBy(id = "grit.storytel.app:id/edit_text_password")
    private MobileElement accountPassword;

    @AndroidFindBy(id = "grit.storytel.app:id/button_sign_in")
    private MobileElement signInButton;
    //</editor-fold>

    Faker faker = new Faker();

    public ConsentScreen createAccount(){
        accounEmail.sendKeys(faker.name().username().toString() + "@gmail.com");
        accountPassword.sendKeys((faker.phoneNumber().toString().substring(1,9)));
        signInButton.click();
        return new ConsentScreen();
    }
}
