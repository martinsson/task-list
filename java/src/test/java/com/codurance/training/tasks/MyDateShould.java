package com.codurance.training.tasks;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyDateShould {

    MyDate b4 = new MyDate("01012000");
    MyDate after = new MyDate("02012000");

    @Test
    public void isComparableLess() throws Exception {
        assertThat(b4, Matchers.lessThan(after));
        assertThat(after, Matchers.greaterThan(b4));
    }


    @Test
    public void isComparableEqual() throws Exception {
        MyDate someDate = new MyDate("25012000");
        MyDate anEqualDate = new MyDate("25012000");
        assertThat(someDate, Matchers.comparesEqualTo(anEqualDate));
    }
}