package FX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FXUtils {

    //getGrid создает сетку
    public static GridPane createGrid(Pos value, double hgap, double vgap, double top, double right, double bottom, double left){
        GridPane grid = new GridPane();
        grid.setAlignment(value);
        grid.setHgap(hgap);
        grid.setVgap(vgap);
        grid.setPadding(new Insets(top, right, bottom, left));
        return grid;
    }

    public static GridPane createGrid(){
        return createGrid(Constants.DEFAULT_POS, Constants.DEFAULT_HGAP, Constants.DEFAULT_VGAP, Constants.DEFAULT_TOP, Constants.DEFAULT_RIGHT, Constants.DEFAULT_BOTTOM, Constants.DEFAULT_LEFT);
    }

    //createScene создает сцену
    public static Scene createScene(Stage stage, Parent root, double width, double height){
        Scene scene = new Scene(root, 300, 275);
        stage.setScene(scene);
        return scene;
    }

    public static Scene createScene(Stage stage, Parent root){
        return createScene(stage, root, Constants.DEFAULT_WIDTH, Constants.DEFAULT_HEIGHT);
    }

    //createText создает нередактируемый текст заданного шрифта и размера
    public static Text createText(String text, String family, FontWeight weight, double size){
        Text result = new Text(text);
        result.setFont(Font.font(family, weight, size));
        return result;
    }

    public static Text createText(String text){
        return createText(text, Constants.DEFAULT_FAMILY, Constants.DEFAULT_FONTWEIGHT, Constants.DEFAULT_SIZE);
    }

    //createLabel создает лэйбл заданного шрифта и размера
    public static Label createLabel(String text, String family, FontWeight weight, double size){
        Label result = new Label(text);
        result.setFont(Font.font(family, weight, size));
        return result;
    }

    public static Label createLabel(String text){
        return createLabel(text, Constants.DEFAULT_FAMILY, Constants.DEFAULT_FONTWEIGHT, Constants.DEFAULT_SIZE);
    }

    //createHBox создает создает макет для кнопки с указанным интервалом между дочерними элементами и в заданной позиции
    public static HBox createHBox(Button btn, double spacing, Pos value){
        HBox hbBtn = new HBox(spacing);
        hbBtn.setAlignment(value);
        hbBtn.getChildren().add(btn);
        return hbBtn;
    }

    public static HBox createHBox(Button btn){
        HBox hbBtn = new HBox(Constants.DEFAULT_SPACING);
        hbBtn.setAlignment(Constants.DEFAULT_POS);
        hbBtn.getChildren().add(btn);
        return hbBtn;
    }

    //hasNumber принимает строку и проверяет на наличие цифр
    public static boolean hasNumbers(String text) {
        StringWriter result = new StringWriter();
        Pattern pat=Pattern.compile("[0-9]");
        Matcher matcher=pat.matcher(text);
        return matcher.find();
    }

    //getNumbers принимает строку и возвращает строку содержащую все цифры входной строки
    public static String getNumbers(String text){
        StringWriter result = new StringWriter();
        Pattern pat=Pattern.compile("[0-9]");
        Matcher matcher=pat.matcher(text);
        while (matcher.find()) {
            result.write(matcher.group());
            result.write(Constants.DEFAULT_DELIMITER);
        }
        return result.toString();
    }

    //printExceptions принимает на вход исключение e и печатет его в area
    public static void printExceptions(RuntimeException e, TextArea area){
        StackTraceElement[] strings = e.getStackTrace();
        StringWriter result = new StringWriter();
        result.write(e.getMessage() + "\n");
        for(StackTraceElement element : strings){
            result.write(element.toString() + "\n");
        }
        area.setText(result.toString());
    }

}
