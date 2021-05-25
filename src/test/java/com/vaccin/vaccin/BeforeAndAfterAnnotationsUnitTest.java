package com.vaccin.vaccin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BeforeAndAfterAnnotationsUnitTest {

    // ...

    private List<String> list;

    @Before
    public void init() {
        System.out.println("startup");
        list = new ArrayList<>(Arrays.asList("test1", "test2"));
    }

    @After
    public void teardown() {
        System.out.println("teardown");
        list.clear();
    }

    @Test
    public void whenCheckingListSize_thenSizeEqualsToInit() {
        System.out.println("executing test");
        assertEquals(2, list.size());

        list.add("another test");
    }

    @Test
    public void whenCheckingListSizeAgain_thenSizeEqualsToInit() {
        System.out.println("executing another test");
        assertEquals(2, list.size());

        list.add("yet another test");
    }
}