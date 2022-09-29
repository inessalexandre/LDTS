import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;


public class Hero extends Element {

    private int hp;

    public Hero() {

        super();
        this.hp = 3;
    }

    public void takeDamage(){
        setHp(this.hp - 1);
    }

    public Position moveUp(){
        return new Position(getPosition().getX(), getPosition().getY() - 1);
    }

    public Position moveDown(){
        return new Position(getPosition().getX(), getPosition().getY() + 1);
    }

    public Position moveLeft(){
        return new Position(getPosition().getX() - 1, getPosition().getY());
    }

    public Position moveRight(){
        return new Position(getPosition().getX() + 1, getPosition().getY());
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(),getPosition().getY()), "X");
    }
}