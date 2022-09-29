package g1008.Controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import g1008.Controller.Menus.GameOverMenu;
import g1008.Controller.Menus.InstructionsMenu;
import g1008.Controller.Menus.Menu;
import g1008.Controller.Menus.StartMenu;
import g1008.Model.Grid;
import g1008.Model.Pieces.*;
import g1008.Model.PointCounter;
import g1008.View.MusicPlayer;
import g1008.View.Painter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private final int playWidth = 10;
    private final int playHeight = 20;
    private final int screenWidth = playWidth * 4 + 32;
    private final int screenHeight = playHeight * 2 + 6;
    private boolean gameOver;
    private boolean canSavePiece;
    private boolean inRotation;
    private Painter painter;
    private Menu startMenu;
    private Menu instructionsMenu;
    private GameOverMenu gameOverMenu;
    private Grid playGrid;
    private Grid saveGrid;
    private List<Grid> gridWaitList;
    private List<Piece> piecesWaitList;
    private Terminal terminal;
    private TerminalScreen screen;
    private Piece mainPiece;
    private Piece savePiece;
    private long timeCounter;
    private long fallTime;
    private long inRotationTimeCounter;
    private PointCounter pointCounter;
    private MusicPlayer musicPlayer;

    public Game() {
        TerminalSize terminalSize = new TerminalSize(screenWidth, screenHeight);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        try {
            terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
        painter = new Painter(screen.newTextGraphics());
        startMenu = new StartMenu(screen);
        instructionsMenu = new InstructionsMenu(screen);
        gameOverMenu = new GameOverMenu(screen);
        this.musicPlayer = new MusicPlayer();
        startMusic();
    }

    public void run() {
        char option = startMenu.run();
        if(option == 'i')
            instructionsMenu.run();
        playGrid = new Grid(playWidth, playHeight,
                (screenWidth - playWidth * 4) / 2, (screenHeight - playHeight * 2) / 2, 2);
        saveGrid = new Grid(5, 5, 4, playGrid.getY(), 1);
        mainPiece = getRandomPiece();
        createGridAndPiecesWaitList();
        pointCounter = new PointCounter();
        timeCounter = System.currentTimeMillis();
        fallTime = pointCounter.getLevelTimer();
        inRotationTimeCounter = 0;
        gameOver = false;
        canSavePiece = true;
        loopGame();
    }

    public void loopGame() {
        screen.clear();
        painter.paintText("Score: ", 4, 9);
        painter.paintText("Level ", 16, 44);
        while (!gameOver) {
            painter.paintText(Integer.toString(pointCounter.getPoints()), 4, 10);
            painter.paintText(Integer.toString(pointCounter.getLevel()),22,44);
            painter.paintGrid(playGrid);
            painter.paintGrid(saveGrid);
            painter.paintGridList(gridWaitList);
            painter.paintPiece(mainPiece, playGrid);
            refreshScreen(screen);
            checkForInput();
            if (System.currentTimeMillis() - timeCounter >= fallTime)
                movePieceDown();
        }
        gameOverMenu.updateLevelAndScore(pointCounter);
        gameOverMenu.run();
        run();
    }

    public void createGridAndPiecesWaitList() {
        gridWaitList = new LinkedList<>();
        piecesWaitList = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            Piece wPiece = getRandomPiece();
            gridWaitList.add(new Grid(wPiece,
                    screenWidth - playGrid.getX() + 2, playGrid.getY() + 6 * i, 1));
            piecesWaitList.add(wPiece);
        }
    }

    public void movePieceDown() {
        mainPiece.moveDown();
        if (!playGrid.isValid(mainPiece)) {
            mainPiece.moveUp();
            if(System.currentTimeMillis() - timeCounter >= fallTime){
                if(inRotation && inRotationTimeCounter == 0)
                    inRotationTimeCounter = System.currentTimeMillis();
                if(inRotation && System.currentTimeMillis() - inRotationTimeCounter < 2000)
                    return;
            }
            inRotation = false;
            inRotationTimeCounter = 0;
            lockMainPiece();
        }
        timeCounter = System.currentTimeMillis();
    }

    private void fullDrop() {
        while (playGrid.isValid(mainPiece))
            mainPiece.moveDown();
        mainPiece.moveUp();
        lockMainPiece();
    }

    private void lockMainPiece(){
        musicPlayer.pieceDropped();
        if(isDead()){
            gameOver = true;
            return;
        }
        playGrid.addPiece(mainPiece);
        pointCounter.processLines(playGrid.removeLines());
        pointCounter.processLevel();
        fallTime = pointCounter.getLevelTimer();
        updateWaitLists();
        canSavePiece = true;
    }

    private boolean isDead(){
        for(int j = 0; j< 5; j++)
            for (int i = 0; i < 5; i++)
                if(mainPiece.getForm().get(i).charAt(j)== '0' && mainPiece.getY()+i-2<0)
                    return true;
        return false;
    }

    private void updateWaitLists() {
        mainPiece = piecesWaitList.remove(0);
        piecesWaitList.add(getRandomPiece());
        gridWaitList.clear();
        for (int i = 0; i < 4; i++) {
            gridWaitList.add(new Grid(piecesWaitList.get(i),
                    screenWidth - playGrid.getX() + 2, playGrid.getY() + 6 * i, 1));
        }
    }

    public void checkForInput() {
        KeyStroke key = getKey();
        if (key == null)
            return;
        switch (key.getKeyType()) {
            case ArrowUp -> rotatePiece();
            case ArrowRight -> mainPiece.moveRight(playGrid);
            case ArrowLeft -> mainPiece.moveLeft(playGrid);
            case ArrowDown -> movePieceDown();
            case Character -> {
                switch (key.getCharacter()) {
                    case 'q', 'Q' -> quitGame(screen);
                    case 'c', 'C' -> savePiece();
                    case ' ' -> fullDrop();
                }
            }
        }
    }

    private void rotatePiece() {
        mainPiece.rotate(playGrid);
        if(!inRotation)
            inRotation = true;
    }

    static public void quitGame(TerminalScreen screen) {
        try {
            screen.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    private void savePiece() {
        if (canSavePiece) {
            if (savePiece == null) {
                savePiece = mainPiece;
                updateWaitLists();
            } else {
                Piece temp = savePiece;
                savePiece = mainPiece;
                mainPiece = temp;
                mainPiece.setX(6);
                mainPiece.setY(0);
            }
            updateSaveGrid();
            canSavePiece = false;
        }
    }

    public void updateSaveGrid() {
        saveGrid = new Grid(savePiece, 4, playGrid.getY(), 1);
    }

    public Piece getRandomPiece() {
        int randomInt = (int) ((Math.random() * (8 - 1)) + 1);
        return switch (randomInt) {
            case 1 -> new LPiece();
            case 2 -> new JPiece();
            case 3 -> new IPiece();
            case 4 -> new ZPiece();
            case 5 -> new SPiece();
            case 6 -> new TPiece();
            case 7 -> new OPiece();
            default -> throw new IllegalStateException("Unexpected value: " + randomInt);
        };
    }

    static public void refreshScreen(TerminalScreen screen) {
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KeyStroke getKey() {
        try {
            return terminal.pollInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void startMusic(){
        musicPlayer.startMusic();
    }
}
