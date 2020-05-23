package com.example.notes;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.InstanceOf;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    MainActivity mainActivity = Mockito.mock(MainActivity.class);
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    /*@Test
    public void test(){
        MainActivity mainActivity1 = new MainActivity();
        Question i = Mockito.mock(Question.class);
        mainActivity1.addItem(i);
        System.out.println(mainActivity1.getList().size());
    }*/

}