import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;public class Game {

    private Screen screen;
    private Arena arena;

    public Game() throws IOException {

        screen = generateTerminal();

        arena = new Arena(40 ,20);

        run();
    }

    public void draw() throws IOException {

        screen.clear();

        arena.draw(screen.newTextGraphics());

        screen.refresh();
    }

    private Screen generateTerminal() throws IOException {
        TerminalSize terminalSize = new TerminalSize(40, 20);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
        return screen;
    }



    public void run() throws IOException {
        while(true) {
            draw();

            KeyStroke key = screen.readInput();

            if (key.getKeyType() == KeyType.EOF) break;

            processKey(key);

            if (arena.verifyMonsterCollisions()){
                if(arena.isHeroDead()) {
                    System.out.println("GAME OVER");
                    screen.close();
                    break;
                }
            }
        }
    }



    private void processKey(KeyStroke key) throws IOException {

        if (key.getKeyType() == KeyType.Character)
            if (key.getCharacter() == 'q')
                screen.close();


        arena.processKey(key);

    }

}