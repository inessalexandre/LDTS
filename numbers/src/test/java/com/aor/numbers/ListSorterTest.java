package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ListSorterTest {
    private List<Integer> list;
    private List<Integer> expected;
    private List<Integer> list2;
    private List<Integer> expectedd;

    @BeforeEach
    public void setUp() {
        list2= Arrays.asList(1,2,4,2);
        list = Arrays.asList(3,2,6,1,4,5,7);
        expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        expectedd = Arrays.asList(1, 2, 2, 4);
    }

    @Test
    public void sort() {
        ListSorter sorter = new ListSorter();
        List<Integer> sorted = sorter.sort(list);

        Assertions.assertEquals(expected, sorted);
    }

    @Test
    public void sort1() {
        ListSorter sorter = new ListSorter();
        List<Integer> sorted = sorter.sort(Arrays.asList(1,2,4,2));
        Assertions.assertEquals(Arrays.asList(1,2,2,4), sorted);
    }
}