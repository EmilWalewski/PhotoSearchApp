package com.photosearchapp.activity;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImageTemplateTest {

    @Rule
    public ActivityTestRule<ImageTemplate> imageTemplateActivityTestRule = new ActivityTestRule<>(ImageTemplate.class);

    private ImageTemplate imageTemplate = null;

    @Before
    public void setUp() throws Exception {

        imageTemplate = imageTemplateActivityTestRule.getActivity();

    }

    @Test
    public void fullSizeTest(){

        assertNotNull(imageTemplate.findViewById(R.id.rowImageID));
        assertNotNull(imageTemplate.findViewById(R.id.rowImageTextViewID));
    }

    @After
    public void tearDown() throws Exception {

        imageTemplate = null;

    }
}