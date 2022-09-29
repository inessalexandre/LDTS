package g1008.ControllerTest;

import g1008.Controller.Game;
import g1008.Model.Pieces.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.io.IOException;


public class GameTest {
    Game game = new Game();
    IPiece Ipiece = new IPiece();
    JPiece Jpiece = new JPiece();
    LPiece Lpiece = new LPiece();
    OPiece Opiece = new OPiece();
    SPiece Spiece = new SPiece();
    TPiece Tpiece = new TPiece();
    ZPiece Zpiece = new ZPiece();


    @Test
    public void gameTest()  {
        try {
            TerminalScreen screen = (TerminalScreen) Mockito.mock(TerminalScreen.class);
            TextGraphics graphics = (TextGraphics) Mockito.mock(TextGraphics.class);
            Mockito.when(screen.newTextGraphics()).thenReturn(graphics);
            Mockito.when(graphics.putString(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyString())).thenReturn(graphics);
        } catch (Exception e){
            e.printStackTrace();
        }
        }

    @Test
    public void getRandomPieceTest() {
        boolean random = true;
        if (game.getRandomPiece() == Lpiece || game.getRandomPiece() == Jpiece || game.getRandomPiece() == Ipiece || game.getRandomPiece() == Zpiece || game.getRandomPiece() == Spiece || game.getRandomPiece() == Tpiece || game.getRandomPiece() == Opiece) {
            random = false;
        }
        Assertions.assertTrue(random);
        Assertions.assertNotNull(this.game.getRandomPiece());
    }

    @Test
    public void getKeyTest() {
        Assertions.assertNull(game.getKey());
    }
}

