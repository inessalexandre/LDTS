package g1008.Controller.Menus;

import com.googlecode.lanterna.screen.TerminalScreen;
import g1008.Model.Grid;

import java.util.List;
import java.util.Vector;

import static g1008.Controller.Game.refreshScreen;

public class StartMenu extends Menu{
    public StartMenu(TerminalScreen screen) {
        super(screen);
        inputOptions = "si";
    }

    @Override
    public void printMenu(){
        painter.paintText("Press 'S' to Start,", 26, 20);
        painter.paintText("'I' for instructions,", 25, 22);
        painter.paintText("Or 'Q' to Quit", 29, 24);
        Grid tetrisGrid = new Grid(18,5,17,10, 1);
        tetrisGrid.setMatrix(getGraphicMatrix());
        painter.paintGrid(tetrisGrid);
        refreshScreen(screen);
    }

    @Override
    public List<List<String>> getGraphicMatrix() {
        List<String> reference = new Vector<>();
        reference.add("PPPOOOGGGBBBYYYRRR");
        reference.add("-P-O---G-B-B-Y-R--");
        reference.add("-P-OOO-G-BB--Y-RRR");
        reference.add("-P-O---G-B-B-Y---R");
        reference.add("-P-OOO-G-B-BYYYRRR");
        return getMatrixFromColors(reference);
    }
}