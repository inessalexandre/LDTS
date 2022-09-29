package g1008.Controller.Menus;

import com.googlecode.lanterna.screen.TerminalScreen;
import g1008.Model.Grid;

import java.util.List;
import java.util.Vector;

import static g1008.Controller.Game.refreshScreen;

public class InstructionsMenu extends Menu{
    public InstructionsMenu(TerminalScreen screen) {
        super(screen);
        inputOptions = "s";
    }

    @Override
    public void printMenu() {
        painter.paintText("Rotate Piece: Arrow Up", 24, 20);
        painter.paintText("Move Piece: Arrows Left,Down,Right", 19, 22);
        painter.paintText("Instant-Drop: SpaceBar", 24, 24);
        painter.paintText("Save Piece: 'C'", 26, 26);
        painter.paintText("Press 'S' to Start", 25, 29);
        painter.paintText("Or 'Q' to Quit", 27, 31);
        Grid howToPlayGrid = new Grid(28,11,10,5, 1);
        howToPlayGrid.setMatrix(getGraphicMatrix());
        painter.paintGrid(howToPlayGrid);
        refreshScreen(screen);
    }

    @Override
    public List<List<String>> getGraphicMatrix() {
        List<String> reference = new Vector<>();
        reference.add("P---P-BBB-L---L---OOOOO-RRR-");
        reference.add("P---PB---BL---L-----O--R---R");
        reference.add("PPPPPB---BL-L-L-----O--R---R");
        reference.add("P---PB---BLL-LL-----O--R---R");
        reference.add("P---P-BBB-L---L-----O---RRR-");
        reference.add("----------------------------");
        reference.add("----YYYY-G------L--Y---Y----");
        reference.add("----Y---YG-----L-L-Y---Y----");
        reference.add("----YYYY-G----L---L-Y-Y-----");
        reference.add("----Y----G----LLLLL--Y------");
        reference.add("----Y----GGGGGL---L--Y------");
        return getMatrixFromColors(reference);
    }
}
