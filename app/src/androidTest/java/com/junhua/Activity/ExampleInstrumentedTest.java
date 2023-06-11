package com.junhua.Activity;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.junhua.common.ArrayExtensionKt;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.junhua.myapplication", appContext.getPackageName());
    }

    @Test
    public void testArray() {
        boolean b = ArrayExtensionKt.baseTypeIsIn(1, "2232","1", 321, 321,1);
        System.out.println("------"+b);
    }
}