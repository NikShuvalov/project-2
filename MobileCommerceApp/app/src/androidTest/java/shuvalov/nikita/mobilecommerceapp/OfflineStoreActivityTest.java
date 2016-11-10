package shuvalov.nikita.mobilecommerceapp;

import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import shuvalov.nikita.mobilecommerceapp.offline_store_front.OfflineStoreActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by NikitaShuvalov on 11/10/16.
 */
@RunWith(AndroidJUnit4.class)
public class OfflineStoreActivityTest {

    @Rule
    public ActivityTestRule<OfflineStoreActivity> mActivityTestRule = new ActivityTestRule<>(OfflineStoreActivity.class);

    @Test
    public void offlineStoreActivityTest() {
        ViewInteraction storeRecyclerView = onView(withId(R.id.offline_store_recycler));
        storeRecyclerView.check((ViewAssertion) isDisplayed());

        storeRecyclerView.perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));




    }
}


//import android.support.test.espresso.ViewInteraction;
//        import android.support.test.rule.ActivityTestRule;
//        import android.support.test.runner.AndroidJUnit4;
//        import android.test.suitebuilder.annotation.LargeTest;
//
//        import org.junit.Rule;
//        import org.junit.Test;
//        import org.junit.runner.RunWith;
//
//        import static android.support.test.espresso.Espresso.onView;
//        import static android.support.test.espresso.action.ViewActions.click;
//        import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
//        import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
//        import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
//        import static android.support.test.espresso.matcher.ViewMatchers.withId;
//        import static android.support.test.espresso.matcher.ViewMatchers.withParent;
//        import static android.support.test.espresso.matcher.ViewMatchers.withText;
//        import static org.hamcrest.Matchers.allOf;
//
//@LargeTest
//@RunWith(AndroidJUnit4.class)
//public class MainActivityTest {
//
//    @Rule
//    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
//
//    @Test
//    public void mainActivityTest() {
//        ViewInteraction appCompatButton = onView(
//                allOf(withId(R.id.offline_store_button), withText("offline store"),
//                        withParent(allOf(withId(R.id.activity_main),
//                                withParent(withId(android.R.id.content)))),
//                        isDisplayed()));
//        appCompatButton.perform(click());
//
//        onView(allOf(withId(R.id.offline_store_recycler),
//                withParent(allOf(withId(R.id.activity_offline_store),
//                        withParent(withId(android.R.id.content)))),
//                isDisplayed()));
//
//    }
