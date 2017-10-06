package com.codurance.training.tasks;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyDateShould {

    @Test
    public void isComparableLess() throws Exception {
        MyDate b4 = new MyDate("01012000");
        MyDate after = new MyDate("02012000");
        assertThat(b4, Matchers.lessThan(after));
    }

    @Test
    public void isComparableGreater() throws Exception {
        MyDate b4 = new MyDate("01012000");
        MyDate after = new MyDate("02012000");
        assertThat(after, Matchers.greaterThan(b4));
    }

    @Test
    public void isComparableEqual() throws Exception {
        MyDate someDate = new MyDate("01012000");
        MyDate anEqualDate = new MyDate("01012000");
        assertThat(someDate, Matchers.comparesEqualTo(anEqualDate));
    }
}