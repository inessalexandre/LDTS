package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class ListDeduplicatorTest {
    private List<Integer> list;
    private List<Integer> expected;

    @BeforeEach
    public void setUp() {
        list = Arrays.asList(1, 2, 4, 2, 5);
        expected = Arrays.asList(1, 2, 4, 5);
    }

    @Test
    public void deduplicate() {
        /*class StubListSorter implements GenericListSorter {
            @Override
            public List<Integer> sort(List<Integer> list) {
                return Arrays.asList(1, 2, 4, 5);
            }
        }

         */
        GenericListSorter sorter = Mockito.mock(GenericListSorter.class);
        Mockito.when(sorter.sort()).thenReturn(Arrays.asList(1, 2, 2, 4, 5));
        ListDeduplicator deduplicator = new ListDeduplicator();
        List<Integer> distinct = deduplicator.deduplicate(list, sorter);
        Assertions.assertEquals(expected, distinct);

    }
    @Test
    public void bug_sorter_8726() {
        List<Integer> list = Arrays.asList(1, 2, 4, 2);
        expected = Arrays.asList(1, 2, 4);
        /*class StubListSorter implements GenericListSorter{
            @Override
            public List<Integer> sort(List<Integer> list) {
                return Arrays.asList(1, 2, 2, 4);
            }
        }

         */

        GenericListSorter sorter = Mockito.mock(GenericListSorter.class);
        Mockito.when(sorter.sort()).thenReturn(Arrays.asList(1, 2, 2, 4));
        ListDeduplicator deduplicator = new ListDeduplicator();
        List<Integer> distinct = deduplicator.deduplicate(list, sorter);
        Assertions.assertEquals(expected, distinct);

    }
}