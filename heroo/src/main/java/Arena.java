import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Arena {

    private int width;
    private int height;

    private Hero hero;

    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;

        this.hero = new Hero();

        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    public List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        try {
            File myObj = new File("src/main/maps/map1");
            Scanner myReader = new Scanner(myObj);
            int line = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                for (int i = 0; i < data.length(); i++) {
                    if (data.charAt(i) == '#') walls.add(new Wall(new Position(i, line)));
                }
                line++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return walls;
    }


    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1));
        return coins;
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1));
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster2(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1));

        return monsters;
    }

    public void draw(TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        hero.draw(graphics);

        for (Wall wall : walls) wall.draw(graphics);
        for (Coin coin : coins) coin.draw(graphics);
        for (Monster monster : monsters) monster.draw(graphics);
    }

    public void processKey(KeyStroke key) throws IOException {

        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero((Position) hero.moveUp());
                break;
            case ArrowDown:
                moveHero((Position) hero.moveDown());
                break;
            case ArrowLeft:
                moveHero((Position) hero.moveLeft());
                break;
            case ArrowRight:
                moveHero((Position) hero.moveRight());
                break;
            default:
                break;
        }

        moveMonsters();


    }

    public boolean isHeroDead() {
        return hero.getHp() == 0;
    }

    public boolean verifyMonsterCollisions() {
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(hero.getPosition())) {
                hero.takeDamage();
                return true;
            }
        }
        return false;
    }

    public void moveMonsters() {
        for (Monster monster : monsters) {
            monster.setPosition(monster.move());
        }
    }

    public void retrieveCoins() {
        for (Coin coin : coins) {
            if (coin.getPosition().equals(hero.getPosition())) {
                coins.remove(coin);
                break;
            }
        }
    }

    private boolean canHeroMove(Position position) {
        int x = position.getX();
        int y = position.getY();

        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) return false;
        }

        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public void moveHero(Position position) {
        if (canHeroMove(position)) hero.setPosition(position);
        retrieveCoins();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}