import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.Random;

class Monster extends Element {

    public Monster() {
        super();
    }

    public Monster(int x, int y) {
        super(new Position(x, y));
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "M");
    }

    public Position move() {
        int side = new Random().nextInt(4);
        switch (side) {
            case 0:
                return new Position(this.getPosition().getX(), this.getPosition().getY() + 1);
            case 1:
                return new Position(this.getPosition().getX(), this.getPosition().getY() - 1);
            case 2:
                return new Position(this.getPosition().getX() + 1, this.getPosition().getY());
            case 3:
                return new Position(this.getPosition().getX() - 1, this.getPosition().getY());
            default:
                break;
        }

        return null;
    }
}