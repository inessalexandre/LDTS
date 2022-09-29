package g1008.Model.Pieces;

import g1008.Model.Grid;

import java.util.List;
import java.util.Vector;

public class IPiece extends Piece{
    public IPiece(){
        this.setHeight(4);
        this.setColor("#00FFFF");
        this.setForms(this.createForms());
    }

    @Override
    List<List<String>> createForms() {
        forms = new Vector<>();
        List <String> form = new Vector<>();
        form.add("..0..");
        form.add("..0..");
        form.add("..0..");
        form.add("..0..");
        form.add(".....");
        forms.add(form);
        form = new Vector<>();
        form.add(".....");
        form.add("0000.");
        form.add(".....");
        form.add(".....");
        form.add(".....");
        forms.add(form);
        return forms;
    }

    @Override
    public void checkIfFits(Grid playGrid) {
        int tests = 0;
        while (tests<this.height){
            if(checkRight(playGrid))
                return;
            if(checkLeft(playGrid))
                return;
            if(checkRight2(playGrid))
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

    public boolean checkRight2(Grid playGrid){
        this.x+=2;
        if (playGrid.isValid(this)){
            return true;
        }
        else
            this.x-=2;
        return false;
    }
}
