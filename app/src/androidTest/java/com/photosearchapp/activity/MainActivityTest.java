package com.photosearchapp.activity;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {

        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void mainActivityTest(){

        assertNotNull(mainActivity.findViewById(R.id.btnSearch));
        assertNotNull(mainActivity.findViewById(R.id.searchField));
        assertNotNull(mainActivity.findViewById(R.id.recyclerViewID));
    }

    @After
    public void tearDown() throws Exception {

        mainActivity = null;

    }
}