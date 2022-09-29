package com.aor.numbers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositiveFilterTest {
    Integer testnumber;
    Integer testnumber2;
    PositiveFilter filtro = new PositiveFilter();
    boolean res;
    @BeforeEach
    public void helper() {
        testnumber = 2;
        testnumber2=-5;
    }
    @Test
    public void Accept() {
        res = filtro.accept(testnumber);
        Assertions.assertEquals(true,res);
    }

    @Test
    public void NoAccept() {
        res = filtro.accept(testnumber2);
        Assertions.assertEquals(false,res);
    }
}
