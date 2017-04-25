package angularMotion.controller;


import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Validate {

    public Validate(){}

    private static boolean isNumber(String text){return text.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+");}

    public static boolean allValid(TextField x0, TextField y0, TextField Vx0, TextField Vy0, TextField time, TextField frequency, Text error){
        try {
            if (Validate.isNumber(x0.getText()) && Validate.isNumber(y0.getText()) && Validate.isNumber(Vx0.getText())
                    && Validate.isNumber(Vy0.getText()) && Validate.isNumber(time.getText()) && Validate.isNumber(frequency.getText())
                    && (Double.parseDouble(frequency.getText()) <= 1000)) {
                return true;
            }else{
                throw new RuntimeException();
            }
        } catch(RuntimeException ex){
            error.setVisible(true);
            return false;
        }
    }
}
