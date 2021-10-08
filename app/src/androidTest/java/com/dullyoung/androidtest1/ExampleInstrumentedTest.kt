package com.dullyoung.androidtest1

import android.content.Intent
import android.provider.ContactsContract.Directory.PACKAGE_NAME
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
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

        //for Singapore custom button is visibility ,so check it isDisplayed

//        onView(
//            allOf(
//                withId(R.id.stv_custom),
//                isDisplayed()
//            )
//        ).check(matches(isDisplayed()))


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

        //for Thailand custom button is invisibility ,so check it doesNotExist
        val textView = onView(
            allOf(
                withId(R.id.stv_custom),
                isDisplayed()
            )
        )
        textView.check(ViewAssertions.doesNotExist())

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
            val expectInfo =
                "{\"data\":{\"id\":12,\"type\":\"subscription\",\"make\":\"Mazda\",\"model\":\"Mazda2\",\"carplate_number\":\"SLT5334G\",\"price\":\"1045.98\",\"start_time\":1587340800,\"end_time\":1589846400,\"next_billing_date\":1608307200,\"mileage\":null,\"total_outstanding_fine_count\":null,\"total_outstanding_fine_amount\":null,\"earliest_payment_due_date\":null,\"total_per_km_rate\":\"0.00\",\"days_left\":null,\"driven_this_month\":0,\"usage_due_this_month\":0,\"base_price\":null,\"road_tax\":682,\"insurance_excess\":2000,\"records\":[{\"key\":\"agreement-documents\",\"label\":\"Agreement Documents\"}],\"has_subscribed_insurance\":true,\"help\":[{\"key\":\"faq\",\"label\":\"FAQ\",\"value\":\"https:\\/\\/carro.sg\\/leap#faq\"},{\"key\":\"terms_and_conditions\",\"label\":\"Terms and conditions\",\"value\":\"https:\\/\\/carro.sg\\/leap\\/terms\"}],\"updated_at\":1597200772,\"drivers\":[{\"name\":\"Driver A\",\"phone\":\"90685298\",\"gender\":\"MALE\",\"id_number\":\"*****631Z\",\"driver_type\":\"MAIN_DRIVER\",\"date_of_birth\":324727300,\"marital_status\":\"MARRIED\",\"driving_experience\":\"10\",\"driving_license_number\":\"*****631Z\",\"driving_license_registration_date\":324727300}]},\"success\":{\"message\":\"Retrieve subscription vehicle successfully!\"}}"
            assert(Gson().toJson(info).equals(expectInfo))
        }
    }


}