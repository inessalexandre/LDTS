package g1008.Model.Pieces;

import g1008.Model.Grid;

import java.util.List;


public abstract class Piece {
    protected List<List<String>> forms;
    protected int rotation=0;
    protected int x=6;
    protected int y=-1;
    protected String color;
    protected int height;

    abstract List<List<String>> createForms();

    public int getX(){return x;}
    public int getY(){return y;}
    public int getHeight() {return height;}
    public String getColor(){return color;}
    public int getRotation(){return rotation;}
    public List<String> getForm() {
        return forms.get(rotation);
    }

    public void setRotation(int rotation){this.rotation=rotation;}
    public void setForms(List<List<String>> forms){this.forms=forms;}
    public void setColor(String color){this.color=color;}
    public void setHeight(int height){this.height=height;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}


    public void rotate(Grid playGrid){
        rotation++;
        if(rotation >= forms.size())
            rotation = 0;
        if(playGrid.isValid(this))
            return;
        checkIfFits(playGrid);
    }
    public void deRotate(){
        rotation--;
        if(rotation < 0)
            rotation = forms.size()-1;
    };

    public void checkIfFits(Grid playGrid) {
        int tests = 0;
        while (tests<this.height){
            if(checkRight(playGrid))
                return;
            if(checkLeft(playGrid))
                return;
            if (tests==0)
                if(checkUnder(playGrid))
                    return;
            this.y-=1;
            if (playGrid.isValid(this))
                return;
            tests+=1;
        }
        this.y+=tests;
        this.deRotate();
    }

    public boolean checkRight(Grid playGrid){
        this.x+=1;
        if (playGrid.isValid(this)){
            return true;
        }
        else
            this.x-=1;
        return false;
    }

    public boolean checkLeft(Grid playGrid){
        this.x-=1;
        if (playGrid.isValid(this)){
            return true;
        }
        else
            this.x+=1;
        return false;
    }

    public boolean checkUnder(Grid playGrid){
        this.y += 1;
        if (playGrid.isValid(this))
            return true;
        if (checkRight(playGrid))
            return true;
        if (checkLeft(playGrid))
            return true;
        this.y -= 1;
        return false;
    }

    public void moveDown() {
        y++;
    }
    public void moveUp() {
        y--;
    }
    public void moveRight(Grid playGrid) {
        x++;
        if(!playGrid.isValid(this))
            x--;
    }
    public void moveLeft(Grid playGrid) {
        x--;
        if(!playGrid.isValid(this))
            x++;
    }
}