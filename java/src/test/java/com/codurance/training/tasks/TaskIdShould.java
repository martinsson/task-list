package com.codurance.training.tasks;

import com.codurance.training.tasks.domain.TaskId;
import org.junit.Test;

public class TaskIdShould {

    @Test
    public void beValid() throws Exception {
        new TaskId("a");
    }

    @Test(expected = Exception.class)
    public void notAllowSpaces() throws Exception {
        new TaskId("a b");
    }

    @Test(expected = Exception.class)
    public void notAllowSpecialChars() throws Exception {
        new TaskId("a!");
    }
}