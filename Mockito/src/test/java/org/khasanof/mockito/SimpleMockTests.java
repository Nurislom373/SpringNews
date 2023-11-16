package org.khasanof.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Nurislom
 * @see org.khasanof.mockito
 * @since 11/16/2023 10:18 PM
 */
public class SimpleMockTests {

    @Test
    void firstTest() {
        var list = mock(List.class);

        when(list.get(0)).thenReturn("first");
        when(list.get(1)).thenThrow(new RuntimeException(""));

        System.out.println(list.get(0));

        verify(list).get(0);
    }

    @Test
    void secondTest() {
        var list = mock(List.class);

        Integer getArgument = 11111;
        Integer containsArgument = 10;

        when(list.get(anyInt())).thenReturn("element");

        when(list.contains(argThat(isValid()))).thenReturn(true);

        list.get(getArgument);
        list.contains(containsArgument);

        verify(list).get(getArgument);
        verify(list).contains(containsArgument);
    }

    @Test
    void thirdTest() {
        List list = mock(List.class);

        String once = "once";
        String twice = "twice";
        String threeTimes = "threeTime";

        when(list.add(anyString())).thenReturn(true);

        list.add(once);

        list.add(twice);
        list.add(twice);

        list.add(threeTimes);
        list.add(threeTimes);
        list.add(threeTimes);

        //following two verifications work exactly the same - times(1) is used by default
        verify(list).add(once);
        verify(list, times(1)).add(once);

        //exact number of invocations verification
        verify(list, times(2)).add(twice);
        verify(list, times(3)).add(threeTimes);

        //verification using never(). never() is an alias to times(0)
        verify(list, never()).add("never happened");

        //verification using atLeast()/atMost()
        verify(list, atMostOnce()).add(once);
        verify(list, atLeastOnce()).add(threeTimes);
        verify(list, atLeast(2)).add(threeTimes);
        verify(list, atMost(5)).add(threeTimes);
    }

    ArgumentMatcher<Integer> isValid() {
        return (var) -> var % 2 == 0;
    }

}
