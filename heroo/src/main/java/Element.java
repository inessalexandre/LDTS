import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {

    private Position position;

    public Element() {
        this.position = new Position(10,10);
    }

    public Element(Position position) {
        this.position = position;
    }

    public abstract void draw(TextGraphics graphics);

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}