package g1008.Controller.Menus;

import com.googlecode.lanterna.screen.TerminalScreen;
import g1008.Model.Grid;
import g1008.Model.PointCounter;

import java.util.List;
import java.util.Vector;

import static g1008.Controller.Game.refreshScreen;

public class GameOverMenu extends Menu{
    int level;
    int score;

    public GameOverMenu(TerminalScreen screen) {
        super(screen);
        inputOptions = "s";
        level = 1;
        score = 0;
    }

    @Override
    public void printMenu() {
        painter.paintText("Final Level: " + Integer.toString(level), 29, 20);
        painter.paintText("Final Score: " + Integer.toString(score), 29, 22);
        painter.paintText("Press 'S' to Re-Start,", 26, 25);
        painter.paintText("Or 'Q' to Quit", 29, 27);
        Grid gameOverGrid = new Grid(23,11,14,5, 1);
        gameOverGrid.setMatrix(getGraphicMatrix());
        painter.paintGrid(gameOverGrid);
        refreshScreen(screen);
    }

    @Override
    public List<List<String>> getGraphicMatrix() {
        List<String> reference = new Vector<>();
        reference.add("-RRRR---R---R---R-RRRRR");
        reference.add("R------R-R--RR-RR-R----");
        reference.add("R-RRR-R---R-R-R-R-RRRRR");
        reference.add("R---R-RRRRR-R---R-R----");
        reference.add("-RRRR-R---R-R---R-RRRRR");
        reference.add("-----------------------");
        reference.add("-RRR--R---R-RRRRR-RRRR-");
        reference.add("R---R-R---R-R-----R---R");
        reference.add("R---R-R---R-RRRRR-RRRR-");
        reference.add("R---R--R-R--R-----R--R-");
        reference.add("-RRR----R---RRRRR-R---R");
        return getMatrixFromColors(reference);
    }

    public void updateLevelAndScore(PointCounter pointCounter){
        level = pointCounter.getLevel();
        score = pointCounter.getPoints();
    }
}
