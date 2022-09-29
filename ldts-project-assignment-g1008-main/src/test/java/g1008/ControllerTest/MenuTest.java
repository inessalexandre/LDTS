package g1008.ControllerTest;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import g1008.Controller.Menus.GameOverMenu;
import g1008.Controller.Menus.InstructionsMenu;
import g1008.Controller.Menus.Menu;
import g1008.Controller.Menus.StartMenu;
import g1008.Model.PointCounter;
import g1008.View.Painter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class MenuTest {
    @Test
    public void printMenuTest(){
        TerminalScreen screen1 = (TerminalScreen) Mockito.mock(TerminalScreen.class);
        TextGraphics board1 = (TextGraphics) Mockito.mock(TextGraphics.class);
        Mockito.when(screen1.newTextGraphics()).thenReturn(board1);
        Menu menu1 = new StartMenu(screen1);
        menu1.printMenu();
        Mockito.verify(board1, Mockito.times(1)).putString(new TerminalPosition(26,20),"Press 'S' to Start,");
        Mockito.verify(board1, Mockito.times(1)).putString(new TerminalPosition(25,22),"'I' for instructions,");
        Mockito.verify(board1, Mockito.times(1)).putString(new TerminalPosition(29,24),"Or 'Q' to Quit");

        TerminalScreen screen2 = (TerminalScreen) Mockito.mock(TerminalScreen.class);
        TextGraphics board2 = (TextGraphics) Mockito.mock(TextGraphics.class);
        Mockito.when(screen2.newTextGraphics()).thenReturn(board2);
        Menu menu2 = new InstructionsMenu(screen2);
        menu2.printMenu();
        Mockito.verify(board2, Mockito.times(1)).putString(new TerminalPosition(24,20),"Rotate Piece: Arrow Up");
        Mockito.verify(board2, Mockito.times(1)).putString(new TerminalPosition(19,22),"Move Piece: Arrows Left,Down,Right");
        Mockito.verify(board2, Mockito.times(1)).putString(new TerminalPosition(24,24),"Instant-Drop: SpaceBar");
        Mockito.verify(board2, Mockito.times(1)).putString(new TerminalPosition(26,26),"Save Piece: 'C'");
        Mockito.verify(board2, Mockito.times(1)).putString(new TerminalPosition(25,29),"Press 'S' to Start");
        Mockito.verify(board2, Mockito.times(1)).putString(new TerminalPosition(27,31),"Or 'Q' to Quit");


        TerminalScreen screen3 = (TerminalScreen) Mockito.mock(TerminalScreen.class);
        TextGraphics board3 = (TextGraphics) Mockito.mock(TextGraphics.class);
        Mockito.when(screen3.newTextGraphics()).thenReturn(board3);
        Menu menu3 = new GameOverMenu(screen3);
        menu3.printMenu();
        Mockito.verify(board3, Mockito.times(1)).putString(new TerminalPosition(26,25),"Press 'S' to Re-Start,");
        Mockito.verify(board3, Mockito.times(1)).putString(new TerminalPosition(29,27),"Or 'Q' to Quit");

    }

    @Test
    public void checkForInputTestS() throws IOException {
        TerminalScreen screen = (TerminalScreen) Mockito.mock(TerminalScreen.class);
        Painter painter = (Painter) Mockito.mock(Painter.class);

        Mockito.when(screen.readInput()).thenReturn(KeyStroke.fromString("i"));
        Menu menu1 = new StartMenu(screen);
        menu1.checkForInput();
        try {
            Mockito.verify(screen, Mockito.times(1)).readInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void checkForInputTestI() throws IOException {
        TerminalScreen screen = (TerminalScreen) Mockito.mock(TerminalScreen.class);
        Painter painter = (Painter) Mockito.mock(Painter.class);

        Mockito.when(screen.readInput()).thenReturn(KeyStroke.fromString("s"));
        Menu menu2 = new InstructionsMenu(screen);
        menu2.checkForInput();
        try {
            Mockito.verify(screen, Mockito.times(1)).readInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void checkForInputTestG() throws IOException {
        TerminalScreen screen = (TerminalScreen) Mockito.mock(TerminalScreen.class);
        Painter painter = (Painter) Mockito.mock(Painter.class);

        Mockito.when(screen.readInput()).thenReturn(KeyStroke.fromString("s"));
        Menu menu3 = new GameOverMenu(screen);
        menu3.checkForInput();
        try {
            Mockito.verify(screen, Mockito.times(1)).readInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getTetrisMatrixTest(){
        TerminalScreen screen1 = (TerminalScreen) Mockito.mock(TerminalScreen.class);
        Menu testMenu1 = new StartMenu(screen1);
        List<List<String>> testMatrix1 = testMenu1.getGraphicMatrix();
        Assertions.assertEquals(testMatrix1.get(0).get(0), "#910091");

        TerminalScreen screen2 = (TerminalScreen) Mockito.mock(TerminalScreen.class);
        Menu testMenu2 = new InstructionsMenu(screen2);
        List<List<String>> testMatrix2 = testMenu2.getGraphicMatrix();
        Assertions.assertEquals(testMatrix2.get(0).get(0), "#910091");

        TerminalScreen screen3 = (TerminalScreen) Mockito.mock(TerminalScreen.class);
        Menu testMenu3 = new GameOverMenu(screen3);
        List<List<String>> testMatrix3 = testMenu3.getGraphicMatrix();
        Assertions.assertEquals(testMatrix3.get(0).get(0), "#000000");
    }

    @Test void getMatrixFromColorsTest(){
        TerminalScreen screen = (TerminalScreen) Mockito.mock(TerminalScreen.class);
        Menu testMenu = new StartMenu(screen);
        List <String>reference = new Vector<>();
        reference.add("G");
        reference.add("P");
        reference.add("O");
        reference.add("B");
        reference.add("Y");
        reference.add("R");
        reference.add("L");
        reference.add("-");

        List<List<String>> testMatrix = testMenu.getMatrixFromColors(reference);
        Assertions.assertEquals(testMatrix.get(0).get(0), "#00FF00");
        Assertions.assertEquals(testMatrix.get(1).get(0), "#910091");
        Assertions.assertEquals(testMatrix.get(2).get(0), "#FF7F00");
        Assertions.assertEquals(testMatrix.get(3).get(0), "#0000FF");
        Assertions.assertEquals(testMatrix.get(4).get(0), "#FFFF00");
        Assertions.assertEquals(testMatrix.get(5).get(0), "#FF0000");
        Assertions.assertEquals(testMatrix.get(6).get(0), "#00FFFF");
        Assertions.assertEquals(testMatrix.get(7).get(0), "#000000");

    }

    @Test
    public void updateGameOverTest(){
        TerminalScreen screen = (TerminalScreen) Mockito.mock(TerminalScreen.class);
        PointCounter pointCounter = (PointCounter) Mockito.mock(PointCounter.class);
        GameOverMenu gameOverTest = new GameOverMenu(screen);
        gameOverTest.updateLevelAndScore(pointCounter);
        Mockito.verify(pointCounter, Mockito.times(1)).getPoints();
        Mockito.verify(pointCounter, Mockito.times(1)).getLevel();
    }
}
