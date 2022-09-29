package g1008.ModelTest;

import g1008.Model.Grid;
import g1008.Model.Pieces.IPiece;
import g1008.Model.Pieces.Piece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Vector;

public class GridTest {

    Grid grid1 =  new Grid(5, 5, 4, 3, 1);
    List<List<String>> matrix1 = new Vector<>();
    public void creatematrix1(int height,int width){
        for (int i = 0; i < height; i++){
            List<String> line = new Vector<>();
            for(int j = 0; j < width; j++){
                line.add("#505050");
            }
            matrix1.add(line);
        }
    }

    Piece iPiece = new IPiece();
    Grid grid2 =  new Grid(iPiece, 7, 6, 1);
    List<List<String>> matrix2 = new Vector<>();
    public void creatematrix2(int height,int width){
        for (int i = 0; i < height; i++){
            List <String> line = new Vector<>();
            for(int j = 0; j < width; j++){
                if (iPiece.getForm().get(i).charAt(j) == '.')
                    line.add("#505050");
                else
                    line.add(iPiece.getColor());
            }
            matrix2.add(line);
        }
    }



    @Test
    public void gets() {
        Assertions.assertEquals(5, grid2.getHeight());
        Assertions.assertEquals(5, grid2.getWidth());
        Assertions.assertEquals(7, grid2.getX());
        Assertions.assertEquals(6, grid2.getY());
        Assertions.assertEquals(1, grid2.getBlockSize());
        creatematrix2(5,5);
        Assertions.assertEquals(matrix2, grid2.getMatrix());

        Assertions.assertEquals(5, grid1.getHeight());
        Assertions.assertEquals(5, grid1.getWidth());
        Assertions.assertEquals(4, grid1.getX());
        Assertions.assertEquals(3, grid1.getY());
        Assertions.assertEquals(1, grid1.getBlockSize());
        creatematrix1(5,5);
        Assertions.assertEquals(matrix1, grid1.getMatrix());
    }

    List<List<String>> matrix3 = new Vector<>();
    public void creatematrix3(int height,int width){
        matrix3.clear();
        for (int i = 0; i < height; i++){
            List<String> line = new Vector<>();
            for(int j = 0; j < width; j++){
                line.add("#505050");
            }
            matrix1.add(line);
        }
    }
    @Test
    public void setmatrix() {
        creatematrix3(6,6);
        Assertions.assertFalse(grid1.setMatrix(matrix3));
        creatematrix2(5,5);
        Assertions.assertTrue(grid1.setMatrix(matrix2));
    }

    @Test
    public void isValid() {
        int playWidth = 10;
        int playHeight = 20;
        int screenWidth = playWidth*4 + 32;
        int screenHeight = playHeight*2 + 6;
        Grid playGrid1 = new Grid(playWidth, playHeight, (screenWidth - playWidth*4)/2,
                (screenHeight - playHeight*2)/2, 2);
        Assertions.assertTrue(playGrid1.isValid(iPiece));
        iPiece.setX(80);
        Assertions.assertFalse(playGrid1.isValid(iPiece));
        iPiece.setX(6);
        iPiece.setY(20);
        Assertions.assertFalse(playGrid1.isValid(iPiece));
        iPiece.setY(6);
        Assertions.assertTrue(playGrid1.isValid(iPiece));

        List<List<String>> matrix = new Vector<>();
        for (int i = 0; i < playHeight; i++){
            List <String> line = new Vector<>();
            for(int j = 0; j < playWidth; j++){
                line.add("#00FFFF");
            }
            matrix.add(line);
        }
        playGrid1.setMatrix(matrix);
        Assertions.assertFalse(playGrid1.isValid(iPiece));

    }

    @Test
    public void addPiece() {
        int playWidth = 10;
        int playHeight = 20;
        int screenWidth = playWidth*4 + 32;
        int screenHeight = playHeight*2 + 6;
        Grid playGrid1 = new Grid(playWidth, playHeight, (screenWidth - playWidth*4)/2,
                (screenHeight - playHeight*2)/2, 2);
        iPiece.setY(7);
        iPiece.setX(8);
        playGrid1.addPiece(iPiece);
        Assertions.assertEquals("#00FFFF",playGrid1.getMatrix().get(iPiece.getY()+0-2).get(iPiece.getX()+2-3));
        Assertions.assertEquals("#00FFFF",playGrid1.getMatrix().get(iPiece.getY()+1-2).get(iPiece.getX()+2-3));
        Assertions.assertEquals("#00FFFF",playGrid1.getMatrix().get(iPiece.getY()+2-2).get(iPiece.getX()+2-3));
        Assertions.assertEquals("#00FFFF",playGrid1.getMatrix().get(iPiece.getY()+3-2).get(iPiece.getX()+2-3));

        Grid playGrid2 = new Grid(playWidth, playHeight, (screenWidth - playWidth*4)/2,
                (screenHeight - playHeight*2)/2, 2);
        iPiece.setY(7);
        iPiece.setX(8);
        iPiece.rotate(playGrid2);
        playGrid2.addPiece(iPiece);
        Assertions.assertEquals("#00FFFF",playGrid2.getMatrix().get(iPiece.getY()+1-2).get(iPiece.getX()+0-3));
        Assertions.assertEquals("#00FFFF",playGrid2.getMatrix().get(iPiece.getY()+1-2).get(iPiece.getX()+1-3));
        Assertions.assertEquals("#00FFFF",playGrid2.getMatrix().get(iPiece.getY()+1-2).get(iPiece.getX()+2-3));
        Assertions.assertEquals("#00FFFF",playGrid2.getMatrix().get(iPiece.getY()+1-2).get(iPiece.getX()+3-3));

    }

    @Test
    public void removeLines() {
        List<List<String>> matrix = new Vector<>();
        for (int i = 0; i < 5; i++){
            List <String> line = new Vector<>();
            for(int j = 0; j < 5; j++){
                if (i<3)
                    line.add("#505050");
                else
                    line.add(iPiece.getColor());
            }
            matrix.add(line);
        }
        Grid grid =  new Grid(5, 5, 4, 3, 1);
        Assertions.assertEquals(0, grid.removeLines());
        grid.setMatrix(matrix);
        Assertions.assertEquals(2, grid.removeLines());
    }



}
