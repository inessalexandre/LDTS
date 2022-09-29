import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster2 extends Monster{

    public Monster2(int x, int y){
        super(x,y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(),getPosition().getY()), "2");
    }

    public Position move() {
        int side = new Random().nextInt(4);
        switch(side){
            case 0:
                return new Position(this.getPosition().getX(), this.getPosition().getY() + 2);
            case 1:
                return new Position(this.getPosition().getX(), this.getPosition().getY() - 2);
            case 2:
                return new Position(this.getPosition().getX() + 2, this.getPosition().getY());
            case 3:
                return new Position(this.getPosition().getX() - 2, this.getPosition().getY());
            default: break;
        }

        return null;
    }
}