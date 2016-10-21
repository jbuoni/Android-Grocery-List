package edu.gatech.seclass.glm.Activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.seclass.glm.R;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.add_list), withContentDescription("Add List"), isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.groceryListName), isDisplayed()));
        appCompatEditText.perform(replaceText("Test List"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("Done"),
                        withParent(allOf(withClassName(is("android.widget.LinearLayout")),
                                withParent(withClassName(is("android.widget.LinearLayout"))))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.editGroceryListButton), withText("View/Edit"),
                        withParent(childAtPosition(
                                withId(R.id.groceryListContainer),
                                2)),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.add_item), withContentDescription("Add Item"), isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.aspnType), isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(android.R.id.text1), withText("Milk"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.aspnItem), isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("Gallon Skim Milk"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.aQuant), isDisplayed()));
        appCompatEditText2.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction actionMenuItemView3 = onView(
                allOf(withId(R.id.add_item), withContentDescription("Add Item"), isDisplayed()));
        actionMenuItemView3.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.listItemContainer),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0)),
                        0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction editText = onView(
                allOf(withId(R.id.itemName), withText("Gallon Skim Milk"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listItemContainer),
                                        0),
                                1),
                        isDisplayed()));
        editText.check(matches(withText("Gallon Skim Milk")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.itemQuantity), withText("1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listItemContainer),
                                        0),
                                2),
                        isDisplayed()));
        editText2.check(matches(withText("1")));

        ViewInteraction actionMenuItemView4 = onView(
                allOf(withId(R.id.search), withContentDescription("Search For Item"), isDisplayed()));
        actionMenuItemView4.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.searchBar), isDisplayed()));
        appCompatEditText3.perform(replaceText("Coors"), closeSoftKeyboard());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(android.R.id.text1), withText("12 Pack of Coors"),
                        childAtPosition(
                                withId(R.id.searchResultsListView),
                                0),
                        isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction linearLayout2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.listItemContainer),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0)),
                        0),
                        isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.itemName), withText("12 Pack of Coors"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listItemContainer),
                                        0),
                                1),
                        isDisplayed()));
        editText3.check(matches(withText("12 Pack of Coors")));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.itemQuantity), withText("1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listItemContainer),
                                        0),
                                2),
                        isDisplayed()));
        editText4.check(matches(withText("1")));

        ViewInteraction actionMenuItemView5 = onView(
                allOf(withId(R.id.search), withContentDescription("Search For Item"), isDisplayed()));
        actionMenuItemView5.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.searchBar), isDisplayed()));
        appCompatEditText4.perform(replaceText("Granny"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.searchBar), withText("Granny"), isDisplayed()));
        appCompatEditText5.perform(pressImeActionButton());

        ViewInteraction actionMenuItemView6 = onView(
                allOf(withId(R.id.add_item), withContentDescription("Add Item"), isDisplayed()));
        actionMenuItemView6.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.itemName), isDisplayed()));
        appCompatEditText6.perform(replaceText("Granny Smith Apples"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner3 = onView(
                allOf(withId(R.id.aspnType), isDisplayed()));
        appCompatSpinner3.perform(click());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(android.R.id.text1), withText("Fruit"), isDisplayed()));
        appCompatTextView4.perform(click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.aQuant), isDisplayed()));
        appCompatEditText7.perform(replaceText("5"), closeSoftKeyboard());

        ViewInteraction actionMenuItemView7 = onView(
                allOf(withId(R.id.add_item), withContentDescription("Add Item"), isDisplayed()));
        actionMenuItemView7.perform(click());

        ViewInteraction linearLayout3 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.listItemContainer),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0)),
                        2),
                        isDisplayed()));
        linearLayout3.check(matches(isDisplayed()));

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.itemName), withText("Granny Smith Apples"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listItemContainer),
                                        2),
                                1),
                        isDisplayed()));
        editText5.check(matches(withText("Granny Smith Apples")));

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.itemQuantity), withText("5"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listItemContainer),
                                        2),
                                2),
                        isDisplayed()));
        editText6.check(matches(withText("5")));

        ViewInteraction appCompatCheckBox = onView(
                allOf(withId(R.id.itemCheckBox),
                        withParent(childAtPosition(
                                withId(R.id.listItemContainer),
                                0)),
                        isDisplayed()));
        appCompatCheckBox.perform(click());

        ViewInteraction appCompatCheckBox2 = onView(
                allOf(withId(R.id.itemCheckBox),
                        withParent(childAtPosition(
                                withId(R.id.listItemContainer),
                                2)),
                        isDisplayed()));
        appCompatCheckBox2.perform(click());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView5 = onView(
                allOf(withId(R.id.title), withText("Select Grocery List"), isDisplayed()));
        appCompatTextView5.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.groceryListName), withText("Test List"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.groceryListContainer),
                                        2),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Test List")));

        ViewInteraction linearLayout4 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.groceryListContainer),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0)),
                        2),
                        isDisplayed()));
        linearLayout4.check(matches(isDisplayed()));

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.editGroceryListButton), withText("View/Edit"),
                        withParent(childAtPosition(
                                withId(R.id.groceryListContainer),
                                2)),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction checkBox = onView(
                allOf(withId(R.id.itemCheckBox),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listItemContainer),
                                        0),
                                0),
                        isDisplayed()));
        checkBox.check(matches(isDisplayed()));

        ViewInteraction checkBox2 = onView(
                allOf(withId(R.id.itemCheckBox),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listItemContainer),
                                        1),
                                0),
                        isDisplayed()));
        checkBox2.check(matches(isDisplayed()));

        ViewInteraction checkBox3 = onView(
                allOf(withId(R.id.itemCheckBox),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listItemContainer),
                                        2),
                                0),
                        isDisplayed()));
        checkBox3.check(matches(isDisplayed()));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
