package com.example.bicyclefinder;

import android.content.Context;
import android.os.SystemClock;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.core.content.ContextCompat.startActivity;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


      @Rule
      public ActivityTestRule<LoginActivity> mActivityRule =
              new ActivityTestRule(LoginActivity.class);

    @Test
    public void TestLoginUser() {

        //Context of the app under test.
        //Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //assertEquals("com.example.bicyclefinder", appContext.getPackageName());
        
          onView(withId(R.id.mainHeader)).check(matches(withText("Bicycle finder")));
          onView(withId(R.id.mainEmailEditText)).perform(typeText("test@test.com"));
          onView(withId(R.id.mainPasswordEditText)).perform(typeText("testtest"));
          onView(withId(R.id.mainLoginButton)).perform(click());

          SystemClock.sleep(3000);

          onView(withId(R.id.loggedInHeader)).check(matches(withText("Oversigt over cykler")));
    }


}