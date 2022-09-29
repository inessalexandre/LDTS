package g1008.ModelTest;

import g1008.Model.Grid;
import g1008.Model.Pieces.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PieceTest {
    int valor = 1;
    Grid grid = new Grid(10, 10, 10, 10, 10);
    IPiece Ipiece = new IPiece();
    JPiece Jpiece = new JPiece();
    LPiece Lpiece = new LPiece();
    OPiece Opiece = new OPiece();
    SPiece Spiece = new SPiece();
    TPiece Tpiece = new TPiece();
    ZPiece Zpiece = new ZPiece();

    @Test
    public void rotateTest() {
        this.Tpiece.rotate(this.grid);
        Tpiece.checkIfFits(grid);
        this.Spiece.rotate(this.grid);
        Spiece.checkIfFits(grid);
        this.Zpiece.rotate(this.grid);
        Zpiece.checkIfFits(grid);
        this.Jpiece.rotate(this.grid);
        Jpiece.checkIfFits(grid);
        Assertions.assertEquals(this.valor, this.Tpiece.getRotation());
        this.Opiece.rotate(this.grid);
        Assertions.assertEquals(0, this.Opiece.getRotation());
    }

   @Test
    public void moveRightTest() {
        this.Lpiece.moveRight(this.grid);
        Assertions.assertEquals(6 + this.valor, this.Lpiece.getX());
        Assertions.assertNotEquals(6 , this.Lpiece.getX());
        this.Lpiece.setX(12);
        this.Lpiece.setY(18);
        this.Lpiece.moveRight(this.grid);
        Assertions.assertEquals(12, this.Lpiece.getX());
        Assertions.assertNotEquals(14,this.Lpiece.getX());
    }

    @Test
    public void moveLeftTest() {
        this.Ipiece.moveLeft(this.grid);
        Assertions.assertEquals(6 - this.valor, this.Ipiece.getX());
        this.Ipiece.setX(12);
        this.Ipiece.setY(18);
        this.Ipiece.moveLeft(this.grid);
        Assertions.assertEquals(12, this.Ipiece.getX());
    }

    @Test
    public void moveDownTest() {
        this.Opiece.moveDown();
        Assertions.assertEquals(-1+this.valor, this.Opiece.getY());
    }

    @Test
    public void moveUpTest() {
        this.Spiece.moveUp();
        Assertions.assertEquals(-1-this.valor, this.Spiece.getY());
    }

    @Test
    public void getFormTest() {
        Assertions.assertNotNull(this.Ipiece.getForm());
    }

    @Test
    public void getHeightTest() {
        Assertions.assertEquals(4, this.Ipiece.getHeight());
        Assertions.assertEquals(3, this.Jpiece.getHeight());
        Assertions.assertEquals(3, this.Lpiece.getHeight());
        Assertions.assertEquals(2, this.Opiece.getHeight());
        Assertions.assertEquals(3, this.Spiece.getHeight());
        Assertions.assertEquals(3, this.Tpiece.getHeight());
        Assertions.assertEquals(3, this.Zpiece.getHeight());
    }

    @Test
    public void deRotateTest() {
        this.Jpiece.deRotate();
        Jpiece.checkIfFits(grid);
        Assertions.assertEquals(this.Jpiece.getForm().size() - 2, this.Jpiece.getRotation());
        Assertions.assertNotEquals(this.Jpiece.getForm().size()-1, this.Jpiece.getRotation());
        this.Spiece.setRotation(1);
        Spiece.checkIfFits(grid);
        //Assertions.assertEquals(this.Spiece.getForm().size()-1, this.Spiece.getRotation());
        Assertions.assertNotEquals(this.Spiece.getForm().size()-2, this.Spiece.getRotation());

    }

   @Test
    public void setXTest() {
        this.Ipiece.setX(2);
        Assertions.assertEquals(2, this.Ipiece.getX());
        Assertions.assertNotEquals(4, this.Ipiece.getX());
    }

    @Test
    public void setYTest() {
        this.Ipiece.setY(3);
        Assertions.assertEquals(3, this.Ipiece.getY());
        Assertions.assertNotEquals(5, this.Ipiece.getY());
    }

    @Test
    public void setRotationTest() {
        this.Ipiece.setRotation(3);
        Assertions.assertEquals(3, this.Ipiece.getRotation());
        Assertions.assertNotEquals(5, this.Ipiece.getRotation());
    }

    @Test
    public void checkIfFitsTest() {
        this.Lpiece.setX(1);
        this.Lpiece.setY(18);
        Lpiece.checkIfFits(grid);
    }

    @Test
    public void checkTest() {
        Assertions.assertTrue(Lpiece.checkRight(grid));
        Assertions.assertTrue(Lpiece.checkLeft(grid));
        Assertions.assertTrue(Lpiece.checkUnder(grid));
        Ipiece.setX(-2);
        Ipiece.setY(0);
        Assertions.assertFalse(Ipiece.checkRight(grid));
        Assertions.assertFalse(Ipiece.checkLeft(grid));
        Assertions.assertFalse(Ipiece.checkUnder(grid));
    }

    @Test
    public void SetColorTest() {
        Zpiece.setColor("#1111111");
        Assertions.assertNotEquals(Zpiece.getColor(), "#OOOOFF");
        Jpiece.setColor("#1111111");
        Assertions.assertNotEquals(Jpiece.getColor(), "#910091");
        Opiece.setColor("#1111111");
        Assertions.assertNotEquals(Opiece.getColor(), "#FF7F00");
        Tpiece.setColor("#1111111");
        Assertions.assertNotEquals(Tpiece.getColor(), "#FF0000");
    }

    @Test
    public void IPieceCheckRightTest() {
        Assertions.assertTrue(Ipiece.checkRight2(grid));
        this.Ipiece.setX(-3);
        this.Ipiece.setY(0);
        Ipiece.checkIfFits(grid);
        Assertions.assertFalse(Ipiece.checkRight2(grid));
    }
}