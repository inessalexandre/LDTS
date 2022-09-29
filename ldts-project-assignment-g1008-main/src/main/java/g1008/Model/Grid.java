package g1008.Model;

import g1008.Model.Pieces.Piece;

import java.util.List;
import java.util.Vector;

public class Grid {
    private List<List<String>> matrix;
    private int width;
    private int height;
    private int x;
    private int y;
    private int blockSize;
    private List<String> blankLine;
    public final static String defaultGridColor = "#505050";

    public Grid(int width, int height, int x, int y, int blockSize){
        this.height = height;
        this.width = width;
        this.blockSize = blockSize;
        this.x = x;
        this.y = y;
        this.matrix=createMatrix(width,height);
    }

    public List<List<String>> createMatrix(int width, int height){
        matrix = new Vector<>();
        for (int i = 0; i < height; i++){
            List <String> line = new Vector<>();
            for(int j = 0; j < width; j++){
                line.add(defaultGridColor);
            }
            matrix.add(line);
        }
        return matrix;
    }



    public Grid(Piece piece, int x, int y, int blockSize){
        this.height = 5;
        this.width = 5;
        this.blockSize = blockSize;
        this.x = x;
        this.y = y;
        this.matrix=createMatrixPiece(piece,width,width);
    }

    public List<List<String>> createMatrixPiece(Piece piece,int width, int height){
        matrix = new Vector<>();
        for (int i = 0; i < height; i++){
            List <String> line = new Vector<>();
            for(int j = 0; j < width; j++){
                if (piece.getForm().get(i).charAt(j) == '.')
                    line.add(defaultGridColor);
                else
                    line.add(piece.getColor());
            }
            matrix.add(line);
        }
        return matrix;
    }

    public int getHeight() {return height;}
    public int getWidth() {return width;}
    public int getX() {return x;}
    public int getY() {return y;}
    public int getBlockSize() {return blockSize;}
    public List<List<String>> getMatrix() {return matrix;}

    public boolean setMatrix(List<List<String>> newMatrix){
        if(newMatrix.size() == height && newMatrix.get(0).size() == width){
            matrix = newMatrix;
            return true;
        }
        return false;
    }

    public boolean isValid(Piece piece) {
        for (int j = 0; j<5; j++){
            for (int i = 0; i<5; i++) {
                if (piece.getForm().get(i).charAt(j) == '0' &&  !(piece.getY() + i - 2 < 0)) {
                    if ((piece.getX() + j - 3)<0 || (piece.getX() + j - 3) >= width)
                        return false;
                    if ((piece.getY() + i - 2) >= height)
                        return false;
                    if (!matrix.get(piece.getY() + i - 2).get(piece.getX() + j - 3).equals(defaultGridColor))
                        return false;
                }
                    if (piece.getForm().get(i).charAt(j) == '0' &&  piece.getY() + i - 2 < 0)
                        if ((piece.getX() + j - 3)<0 || (piece.getX() + j - 3) >= 10)
                            return false;
            }
        }
        return true;
    }

    public void addPiece(Piece piece){
        for (int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if (piece.getForm().get(i).charAt(j)=='0')
                    this.matrix.get(piece.getY()+i-2).set(piece.getX()+j-3, piece.getColor());
            }
        }
    }

    public int removeLines(){
        int removedLines = 0;
        boolean hasHole;
        int i = 0;
        while(i < height){
            hasHole = verifyLine(i);
            if(!hasHole){
                removeLine(i);
                removedLines ++;
            }
            i++;
        }
        return removedLines;
    }

    public boolean verifyLine(int i){
        boolean hasHole= false;
        for(String color : matrix.get(i))
            if(color.equals(defaultGridColor)){
                hasHole = true;
                break;
            }
        return hasHole;
    }

    public void removeLine(int i){
        matrix.remove(i);
        List <String> line = new Vector<>();
        for(int j = 0; j < width; j++){
            line.add(defaultGridColor);
        }
        matrix.add(0, line);
    }
}

