package com.mytaxi.android_demo;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.rule.GrantPermissionRule;
import android.view.WindowManager;

import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by rsinghal on 10.06.18.
 */

public class CallDriverTest {

    private String userName;
    private String userPassword;
    private String searchText;
    private String driverName;

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(MainActivity.class);

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION);

    @Before
    public void initVariables() {

        userName = "whiteelephant261";
        userPassword = "video1";
        searchText = "sa";
        driverName= "Sarah Friedrich";
    }

    @Test
    public void callDriver() throws InterruptedException {

        // Validate presence of Username Edit Text Box and enter username
        ViewInteraction usernameTextView =  onView(allOf(withId(R.id.edt_username),isDisplayed()));
        usernameTextView.perform(click());
        usernameTextView.perform(clearText(),typeText(userName), closeSoftKeyboard());

        // Validate presence of Password Edit Text Box and enter password
        ViewInteraction passwordTextView =  onView(withId(R.id.edt_password));
        passwordTextView.perform(click());
        passwordTextView.perform(clearText(),typeText(userPassword), closeSoftKeyboard());

        // Validate and click Login Button
        ViewInteraction loginButton =  onView(allOf(withId(R.id.btn_login), isDisplayed()));
        loginButton.perform(click());

        sleep(9000);
        // Validate presence of Search Bar Locator and enter search term
        ViewInteraction searchBarTextView =  onView(allOf(withId(R.id.textSearch),isDisplayed()));
        searchBarTextView.perform(clearText(),typeText(searchText), closeSoftKeyboard());

        //Select required element from list
        onView(withText(driverName)).inRoot(RootMatchers.isPlatformPopup()).perform(click());

        // Validate and click Call Button
        ViewInteraction callButton =  onView(allOf(withId(R.id.fab), isDisplayed()));
        callButton.perform(click());

        // Validate presence of Action call Intent and number displayed
//        intended(allOf(
//                hasAction(Intent.ACTION_CALL),
//                hasData("01748819231")));


    }



}

