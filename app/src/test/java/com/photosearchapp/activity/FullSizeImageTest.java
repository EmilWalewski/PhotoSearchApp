package com.photosearchapp.activity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class FullSizeImageTest {

    @Mock
    private FullSizeImage fullSizeImage = null;


    @Test
    public void fullSizeTest(){

        fullSizeImage = Mockito.mock(FullSizeImage.class);

        Mockito.doNothing().when(fullSizeImage).onCreate(null);

    }

    @After
    public void tearDown() throws Exception {

        fullSizeImage = null;

    }
}