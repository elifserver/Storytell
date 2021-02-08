package com.framework.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;


import com.framework.utils.Base;
import org.junit.Assert;

public class BookDetailsScreen extends Base {
    //<editor-fold desc="Elements">
    @AndroidFindBy(id = "grit.storytel.app:id/textViewBookName")
    private MobileElement book;

    @AndroidFindBy(id="grit.storytel.app:id/btnBookshelfToggle")
    private MobileElement likeIcon;


    @AndroidFindBy(id = "grit.storytel.app:id/snackbar_text")
    private MobileElement snackBarText;

    @AndroidFindBy(xpath="//android.view.ViewGroup/android.widget.ImageButton")
    private  MobileElement backIcon;

    @AndroidFindBy(id="grit.storytel.app:id/bAuthor")
    private MobileElement authorNameButton;
    //</editor-fold>

    public String getBookName () {
        System.out.println("bookName:::" + book.getText());
        return book.getText();
    }

    public CreateAccountScreen likeTheBook(){
        likeIcon.click();
        return new CreateAccountScreen();
    }

    public void goBackToBookTipsScreen(){
        backIcon.click();
    }

    public BookDetailsScreen likeTheBookAndCheckIfBookIsSavedToBookShelve(){
        likeIcon.click();
        Assert.assertEquals("There is a problem on saving to the shelve. Please check!",
                             snackBarText.getText(), "Saved to your bookshelf");
        return this;
    }

    public BookDetailsScreen assertYouPickTheCorrectBook(String expectedBookName){
        Assert.assertEquals(expectedBookName,this.book.getText());
        return this;
    }

    public AuthorScreen proceedForCheckingAuthorDetails(){
        authorNameButton.click();
        return new AuthorScreen();
    }
}
