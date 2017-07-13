package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.MainActivityFragment.EndpointsAsyncTask;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

/**
 * Created by lamkeong on 7/13/2017.
 */
@RunWith(AndroidJUnit4.class)
public class AsyncTest {

    private EndpointsAsyncTask mAsyncTask;

    @Before
    public void createAsyncTask() {
        mAsyncTask = new MainActivityFragment().new EndpointsAsyncTask();
    }

    @Test
    public void testDoInBackground() throws Exception {
        String joke = mAsyncTask.execute().get();
        assertTrue("failed", joke != null);
    }
}

