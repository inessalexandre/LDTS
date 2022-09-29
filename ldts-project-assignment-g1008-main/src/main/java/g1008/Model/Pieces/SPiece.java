package g1008.Model.Pieces;

import java.util.List;
import java.util.Vector;

public class SPiece extends Piece{
    public SPiece(){
        this.setHeight(3);
        this.setColor("#00FF00");
        this.setForms(this.createForms());
    }

    @Override
    List<List<String>> createForms() {
        forms = new Vector<>();
        List <String> form = new Vector<>();
        form.add(".....");
        form.add("..00.");
        form.add(".00..");
        form.add(".....");
        form.add(".....");
        forms.add(form);
        form = new Vector<>();
        form.add(".....");
        form.add("..0..");
        form.add("..00.");
        form.add("...0.");
        form.add(".....");
        forms.add(form);
        return forms;
    }
}
