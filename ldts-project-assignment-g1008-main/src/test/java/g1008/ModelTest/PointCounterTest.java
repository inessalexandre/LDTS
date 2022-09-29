package g1008.ModelTest;

import g1008.Model.PointCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PointCounterTest {
    PointCounter testCounter;
    @BeforeEach
    public void setUp(){
        testCounter = new PointCounter();
    }
    @Test
    public void getSetTest(){
        testCounter.setPoints(100);
        Assertions.assertEquals(testCounter.getPoints(), 100);
    }
    @Test
    public void processLinesTest(){
        testCounter.processLines(1);
        Assertions.assertEquals(testCounter.getPoints(), 40);
    }
    @Test
    public void processLevelAndGetTimerTest(){
        testCounter.processLines(29);
        testCounter.processLevel();
        Assertions.assertEquals(testCounter.getLevel(), 3);
        Assertions.assertEquals(testCounter.getLevelTimer(), 1600/3);
    }

    @Test
    public void processLevelAndGetTimerTest2() {
        testCounter.processLines(100);
        testCounter.processLevel();
        Assertions.assertEquals(testCounter.getLevel(), 8);
        Assertions.assertEquals(testCounter.getLevelTimer(), 1600/8);
    }

    @Test
    public void processLevelAndGetTimerTest3() {
        testCounter.processLines(229);
        testCounter.processLevel();
        Assertions.assertEquals(testCounter.getLevel(), 11);
        Assertions.assertEquals(testCounter.getLevelTimer(), 1600/11);
    }

    @Test
    public void processLevelAndGetTimerTest4() {
        testCounter.processLines(137);
        testCounter.processLevel();
        Assertions.assertEquals(testCounter.getLevel(), 9);
        Assertions.assertEquals(testCounter.getLevelTimer(), 1600/9);
    }

    @Test
    public void processLevelAndGetTimerTest5() {
        testCounter.processLines(53);
        testCounter.processLevel();
        Assertions.assertEquals(testCounter.getLevel(), 7);
        Assertions.assertEquals(testCounter.getLevelTimer(), 1600/7);
    }

    @Test
    public void processLevelAndGetTimerTest6() {
        testCounter.processLines(181);
        testCounter.processLevel();
        Assertions.assertEquals(testCounter.getLevel(), 10);
        Assertions.assertEquals(testCounter.getLevelTimer(), 1600/10);
    }

}
