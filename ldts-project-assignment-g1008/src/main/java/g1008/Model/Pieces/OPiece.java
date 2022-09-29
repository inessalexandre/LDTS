package g1008.Model.Pieces;

import java.util.List;
import java.util.Vector;

public class OPiece extends Piece{
    public OPiece(){
        this.setHeight(2);
        this.setColor("#FFFF00");
        this.setForms(this.createForms());
    }

    @Override
    List<List<String>> createForms() {
        forms = new Vector<>();
        List <String> form = new Vector<>();
        form.add(".....");
        form.add(".00..");
        form.add(".00..");
        form.add(".....");
        form.add(".....");
        forms.add(form);
        return forms;
    }
}
