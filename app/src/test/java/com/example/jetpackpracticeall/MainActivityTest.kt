package com.example.jetpackpracticeall

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowToast

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    @Mock
    lateinit var viewModel: MainViewModel
    private lateinit var activity: MainActivity

    /*
    set up is activity robolectic for views
     */
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .resume()
            .get()
    }

    // robolectric for not null
    @Test
    fun assertViewModelNotNull() {
        assertNotNull(activity.viewModel)
    }

    // robolectric / mockito for viewmodel interaction
    @Test
    fun testViewModelGetDataInActivity() {
        val expectedData = "Mocked Data"
        Mockito.`when`(viewModel.getData()).thenReturn("Mocked Data")
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity {
            activity.viewModel = viewModel
            val result = activity.viewModel.getData()
            verify(viewModel).getData()
            assertEquals(expectedData, result)
        }
    }

    @Test
    fun testViewModelGetDataIntInActivity() {
        val expectedData = 5
        Mockito.`when`(viewModel.getDataInt()).thenReturn(5)
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity {
            activity.viewModel = viewModel
            val result = activity.viewModel.getDataInt()
            verify(viewModel).getDataInt()
            assertEquals(expectedData, result)
        }
    }


    // robolectric for views
    @Test
    @Throws(Exception::class)
    fun shouldHaveDefaultMargin() {
        val textView = activity.findViewById<View>(R.id.hello_textview) as TextView
        val bottomMargin = (textView.layoutParams as LinearLayout.LayoutParams).bottomMargin
        assertEquals(5, bottomMargin)
        val topMargin = (textView.layoutParams as LinearLayout.LayoutParams).topMargin
        assertEquals(5, topMargin)
        val rightMargin = (textView.layoutParams as LinearLayout.LayoutParams).rightMargin
        assertEquals(10, rightMargin)
        val leftMargin = (textView.layoutParams as LinearLayout.LayoutParams).leftMargin
        assertEquals(10, leftMargin)
    }

    @Test
    @Throws(Exception::class)
    fun checkActivityNotNull() {
        assertNotNull(activity)
    }

    @Test
    @Throws(Exception::class)
    fun testButtonClickShouldShowToast() {
        val bt = activity.findViewById<Button>(R.id.bt_mainActivity)
        bt.performClick()
        assertEquals(ShadowToast.getTextOfLatestToast(), "lala")
    }
}