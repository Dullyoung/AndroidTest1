package com.dullyoung.androidtest1

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.coorchice.library.SuperTextView
import com.dullyoung.androidtest1.model.bean.DetailInfo
import com.dullyoung.androidtest1.model.engine.DetailInfoEngine
import com.dullyoung.androidtest1.utils.CommonUtils
import com.dullyoung.androidtest1.view.acvitity.CarDetailActivity
import com.dullyoung.androidtest1.viewmodel.DetailViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runner.manipulation.Ordering
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(
    MockitoJUnitRunner::class
)
@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class ExampleUnitTest {
    //todo note:思路错了 这个test是做本地方法测试的

    @Test
    fun testFormat() {
        val date = 1597200772000L
        val dateString = CommonUtils.formatDate(date)
        print(dateString)
        assert(dateString == "12/08/2020")
    }


    // see https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/

//    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
//
//    @Before
//    fun setUp() {
//        Dispatchers.setMain(mainThreadSurrogate)
//    }
//
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
//        mainThreadSurrogate.close()
//    }

//    @Test
//    fun getOnlineData() {
//        var info: DetailInfo?
//        MainScope().launch(Dispatchers.Main) {
//            val engine = DetailInfoEngine()
//            info = engine.getInfo()
//            print("-------------")
//            Log.i("Test", "getOnlineData: ")
//            Assert.assertEquals(info, null);
//        }
//    }


}