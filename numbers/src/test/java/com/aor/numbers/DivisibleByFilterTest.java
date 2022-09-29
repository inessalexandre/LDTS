package com.aor.numbers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DivisibleByFilterTest {
    Integer testnumber;
    Integer divisor = 3;
    boolean res;
    DivisibleByFilter filtro;
    @BeforeEach
    public void helper() {
        testnumber = 10;
        filtro = new DivisibleByFilter(divisor);
    }
    @Test
    public void Accept() {
        res = filtro.accept(testnumber);
        Assertions.assertEquals(false,res);
    }

}
