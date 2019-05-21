package com.dicoding.exam.footballexam.main

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.dicoding.exam.footballexam.R.id.*
import com.dicoding.exam.footballexam.view.activity.MainActivity
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testBottomNavigation(){
        //Testing untuk memastikan bahwa Bottom Navigation bisa ditampilkan
        Espresso.onView(ViewMatchers.withId(navigation)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //Testing untuk memberikan tindakan klik pada Bottom Navigation
        Espresso.onView(ViewMatchers.withId(navigation)).perform(ViewActions.click())

    }

    @Test
    fun testFrameLayout(){
        //Testing untuk memastikan bahwa FrameLayout bisa ditampilkan
        Espresso.onView(ViewMatchers.withId(container)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //Testing untuk memberikan tindakan klik pada FrameLayout
        Espresso.onView(ViewMatchers.withId(container)).perform(ViewActions.click())
    }

    @Test
    fun testToolbar(){
        //Testing untuk memastikan bahwa Toolbar bisa ditampilkan
        Espresso.onView(ViewMatchers.withId(toolbar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //Testing untuk memastikan bahwa Textview  bisa ditampilkan
        Espresso.onView(ViewMatchers.withId(toolbar_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //Testing untuk memastikan bahwa ImageView Search  bisa ditampilkan
        Espresso.onView(ViewMatchers.withId(toolbar_search)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //Testing untuk memberikan tindakan klik pada ImageView Search
        Espresso.onView(ViewMatchers.withId(toolbar_search)).perform(ViewActions.click())
    }


}