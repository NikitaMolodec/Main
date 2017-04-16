package FX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class FXUtils {

    public static GridPane getGrid(Pos value, double hgap, double vgap, double top, double right, double bottom, double left){
        GridPane grid = new GridPane();
        grid.setAlignment(value);
        grid.setHgap(hgap);
        grid.setVgap(vgap);
        grid.setPadding(new Insets(top, right, bottom, left));
        return grid;
    }
}
