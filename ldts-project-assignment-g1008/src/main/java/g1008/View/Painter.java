package g1008.View;

import g1008.Model.Grid;
import g1008.Model.Pieces.Piece;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public class Painter {
    private TextGraphics board;

    public Painter(TextGraphics board) {
        this.board = board;
    }

    public void paintGrid(Grid grid){
        int x;
        int y = grid.getY();
        for(List<String> line : grid.getMatrix()){
            x = grid.getX();
            for(String color : line){
                board.setBackgroundColor(TextColor.Factory.fromString(color));
                board.fillRectangle(new TerminalPosition(x,y),
                        new TerminalSize(grid.getBlockSize() * 2, grid.getBlockSize()), ' ');
                x += grid.getBlockSize() * 2;
            }
            y += grid.getBlockSize();
        }
    }

    public void paintGridList(List<Grid> grids){
        for (Grid grid : grids){
            paintGrid(grid);
        }
    }

    public void paintPiece(Piece piece, Grid grid){
        int x;
        int y = grid.getY() - 4 + piece.getY() * grid.getBlockSize();
        board.setBackgroundColor(TextColor.Factory.fromString(piece.getColor()));
        for(String line : piece.getForm()){
            x = grid.getX() + piece.getX() * grid.getBlockSize() * 2 - 8;
            for(int i = 0; i < line.length(); i++){
                if(line.charAt(i) == '0' && y >= grid.getY())
                    board.fillRectangle(new TerminalPosition(x - 4 +i*2,y),
                            new TerminalSize(grid.getBlockSize()*2, grid.getBlockSize()), ' ');
                x += grid.getBlockSize();
            }
            y += grid.getBlockSize();
        }
    }

    public void paintText(String text, int x, int y){
        board.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        board.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        board.putString(new TerminalPosition(x, y), text);
    }
}

