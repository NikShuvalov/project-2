package shuvalov.nikita.mobilecommerceapp;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.offline_store_button), withText("offline store"),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.offline_store_recycler),
                        withParent(allOf(withId(R.id.activity_offline_store),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buy_button), withText("$49.99"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.buy_button), withText("$19.99"), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(R.id.tool_bar),
                                withParent(withId(R.id.activity_offline_detail)))),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.shopping_cart), withContentDescription("Shopping cart"), isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.back_to_store_button), withText("Continue shopping"),
                        withParent(allOf(withId(R.id.activity_shopping_cart),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.search), withContentDescription("Search"), isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withId(R.id.search_src_text),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("bott"), closeSoftKeyboard());

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withId(R.id.search_src_text), withText("bott"),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete2.perform(pressImeActionButton());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.offline_store_recycler),
                        withParent(allOf(withId(R.id.activity_offline_store),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.buy_button), withText("$54.99"), isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction actionMenuItemView3 = onView(
                allOf(withId(R.id.shopping_cart), withContentDescription("Shopping cart"), isDisplayed()));
        actionMenuItemView3.perform(click());

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.holder_layout),
                        withParent(allOf(withId(R.id.shopping_cart_recycler),
                                withParent(withId(R.id.activity_shopping_cart)))),
                        isDisplayed()));
        relativeLayout.perform(longClick());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(android.R.id.button2), withText("No"),
                        withParent(allOf(withId(R.id.buttonPanel),
                                withParent(withId(R.id.parentPanel)))),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction relativeLayout2 = onView(
                allOf(withId(R.id.holder_layout),
                        withParent(allOf(withId(R.id.shopping_cart_recycler),
                                withParent(withId(R.id.activity_shopping_cart)))),
                        isDisplayed()));
        relativeLayout2.perform(longClick());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        withParent(allOf(withId(R.id.buttonPanel),
                                withParent(withId(R.id.parentPanel)))),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.back_to_store_button), withText("Continue shopping"),
                        withParent(allOf(withId(R.id.activity_shopping_cart),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(R.id.tool_bar),
                                withParent(withId(R.id.activity_offline_detail)))),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.search_close_btn), withContentDescription("Clear query"),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        appCompatImageView.perform(click());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Reset"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction actionMenuItemView4 = onView(
                allOf(withId(R.id.shopping_cart), withContentDescription("Shopping cart"), isDisplayed()));
        actionMenuItemView4.perform(click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.checkout_button), withText("Pay $67.37"),
                        withParent(allOf(withId(R.id.activity_shopping_cart),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.back_to_store_button), withText("Continue shopping"),
                        withParent(allOf(withId(R.id.activity_shopping_cart),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton10.perform(click());

    }

}
