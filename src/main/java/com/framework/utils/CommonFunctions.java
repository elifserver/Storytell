package com.framework.utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class CommonFunctions extends Base {
    MobileElement element;

    public void swipeForAPredefinedNumberOfTimesOnAnElement(MobileElement element, int loopCount) {
        Point p = wait.until(ExpectedConditions.visibilityOf(element)).getLocation();
        //Point p = listView.getLocation();
        TouchAction t = new TouchAction(driver);
        for (int i = 0; i < loopCount; i++) {
            t.press(PointOption.point(p.x + 300, p.y));
            t.waitAction(WaitOptions.waitOptions(Duration.ofMillis(400)));
            t.moveTo(PointOption.point(p.x + 100, p.y));
            t.release();
            t.perform();
        }
    }

    public MobileElement scrollUntilElementIsVisible(String by, String locator) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int endX = startX;
        int startY = (int) (size.height * 0.6);
        int endY = (int) (size.height * 0.2);

        TouchAction t = new TouchAction(driver);

        for (int i = 0; i < 30; i++) {

            try {
                t.longPress((PointOption.point(startX, startY)));
                t.moveTo(PointOption.point(endX, endY));
                t.release();
                t.perform();
                element = (MobileElement) driver.findElement(by, locator);
                break;
            } catch (NoSuchElementException ex) {
                continue;
            }
        }
        return element;
    }

    public void clickBackButton() {
        ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.BACK));
    }

}
