package g1008.ViewTest;

import g1008.Model.Grid;
import g1008.Model.Pieces.LPiece;
import g1008.View.Painter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;



public class PainterTest {


    @Test
    void paintGrid() {
        TextGraphics board= Mockito.mock(TextGraphics.class);
        Painter painter = new Painter(board);
        Grid grid1 = new Grid(1,1,10,10,10);
        painter.paintGrid(grid1);
        Mockito.verify(board, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#505050"));

        TextGraphics board2= Mockito.mock(TextGraphics.class);
        Painter painter2 = new Painter(board2);
        Grid grid2 = new Grid(2,2,10,10,10);
        painter2.paintGrid(grid2);
        Mockito.verify(board2, Mockito.times(4)).setBackgroundColor(TextColor.Factory.fromString("#505050"));
    }
    @Test
    void paintGridList() {
        TextGraphics board= Mockito.mock(TextGraphics.class);
        Painter painter = new Painter(board);
        Grid grid1 = new Grid(1,1,10,10,10);
        Grid grid2 = new Grid(2,2,10,10,10);
        List<Grid> grids = new ArrayList<>();
        grids.add(grid1);
        grids.add(grid2);
        painter.paintGridList(grids);
        Mockito.verify(board, Mockito.times(5)).setBackgroundColor(TextColor.Factory.fromString("#505050"));
    }
    @Test
    void paintPiece() {
        LPiece lPiece = new LPiece();
        TextGraphics board= Mockito.mock(TextGraphics.class);
        Painter painter = new Painter(board);
        String lPieceColor = "#FF7F00";
        Grid grid1 = new Grid(1,1,10,10,10);
        painter.paintPiece(lPiece,grid1);
        Mockito.verify(board, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString(lPieceColor));
    }

    @Test
    void paintTextTest(){
        TextGraphics board= Mockito.mock(TextGraphics.class);
        Painter painter = new Painter(board);
        painter.paintText("Welcome", 10, 10);
        Mockito.verify(board, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
    }

}
