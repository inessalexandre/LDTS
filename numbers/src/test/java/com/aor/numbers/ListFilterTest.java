package com.aor.numbers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class ListFilterTest {
    List<Integer> list;
    List<Integer> result;
    List<Integer> expected;
    @BeforeEach
    public void helper() {
        list = Arrays.asList(1,2,-4);
        expected = Arrays.asList(1,2);
    }
    @Test
    public void Filter() {
        GenericListFilter stubfillter = Mockito.mock(GenericListFilter.class);
        Mockito.when(stubfillter.accept(1)).thenReturn(true);
        Mockito.when(stubfillter.accept(2)).thenReturn(true);
        Mockito.when(stubfillter.accept(-4)).thenReturn(false);
        GenericListFilter filter = new PositiveFilter();
        ListFilter listfilter = new ListFilter(filter);
        result = listfilter.filter(list,stubfillter);
        Assertions.assertEquals(result,expected);
    }
}