package com.dullyoung.androidtest1

import android.content.Intent
import android.provider.ContactsContract.Directory.PACKAGE_NAME
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dullyoung.androidtest1.model.engine.DetailInfoEngine
import com.dullyoung.androidtest1.view.acvitity.CarDetailActivity
import com.dullyoung.androidtest1.view.acvitity.MainActivity
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.internal.wait
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import java.lang.Thread.sleep


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val intentsTestRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.dullyoung.androidtest1", appContext.packageName)
    }

    @Test
    fun checkIntent() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        //click singapore
        onView(withId(R.id.stv_singapore))
            .perform(click())

        intended(
            allOf(
                hasComponent(hasShortClassName(".view.acvitity.CarDetailActivity")),
                toPackage(appContext.packageName),
                hasExtra("type", CarDetailActivity.CountryType.Singapore.type)
            )
        )

        //back
        onView(withId(R.id.iv_back))
            .perform(click())

        //click thailand
        onView(withId(R.id.stv_thailand))
            .perform(click())

        intended(
            allOf(
                hasComponent(hasShortClassName(".view.acvitity.CarDetailActivity")),
                toPackage(appContext.packageName),
                hasExtra("type", CarDetailActivity.CountryType.Thailand.type)
            )
        )

        //for error test
        intended(
            allOf(
                hasComponent(hasShortClassName(".view.acvitity.CarDetailActivity")),
                toPackage(appContext.packageName),
                hasExtra("type", "haha")
            )
        )

    }

    @Test
    fun getOnlineData() {
        MainScope().launch(Dispatchers.Main) {
            val engine = DetailInfoEngine()
            val info = engine.getInfo()
            assert(info!=null)
        }
    }
}