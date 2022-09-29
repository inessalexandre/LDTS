package g1008.Controller.Menus;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import g1008.View.Painter;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import static g1008.Controller.Game.quitGame;

public abstract class Menu {

    protected Painter painter;
    protected TerminalScreen screen;
    protected String inputOptions;

    public Menu(TerminalScreen screen) {
        this.screen = screen;
        this.painter = new Painter(screen.newTextGraphics());
    }

    public Character run(){
        screen.clear();
        printMenu();
        return Character.toLowerCase(checkForInput());
    }

    public abstract void printMenu();

    public abstract List<List<String>> getGraphicMatrix();

    public List<List <String>> getMatrixFromColors(List<String> reference){
        List<List <String>> matrix = new Vector<>();
        for(int j = 0; j<reference.size(); j++){
            matrix.add(new Vector<>());
            for(int i = 0; i<reference.get(j).length(); i++)
                switch (reference.get(j).charAt(i)){
                    case 'P' -> matrix.get(j).add("#910091");
                    case 'O' -> matrix.get(j).add("#FF7F00");
                    case 'G' -> matrix.get(j).add("#00FF00");
                    case 'B' -> matrix.get(j).add("#0000FF");
                    case 'Y' -> matrix.get(j).add("#FFFF00");
                    case 'R' -> matrix.get(j).add("#FF0000");
                    case 'L' -> matrix.get(j).add("#00FFFF");
                    case '-' -> matrix.get(j).add("#000000");
                }
        }
        return matrix;
    }

    public Character checkForInput(){
        KeyStroke key = readInput();
        if(key == null || key.getKeyType()!= KeyType.Character)
            return checkForInput();
        if (key.getCharacter() == 'q' || key.getCharacter() == 'Q')
            quitGame(screen);
        if(inputOptions.indexOf(key.getCharacter()) != -1 ||
                inputOptions.indexOf(Character.toLowerCase(key.getCharacter())) != -1)
            return key.getCharacter();
        return checkForInput();
    }

    public KeyStroke readInput(){
        try {
            return screen.readInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
