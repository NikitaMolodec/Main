package FX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SimpleWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("My first window");
        primaryStage.show();

        GridPane grid = FXUtils.createGrid();

        Scene scene = FXUtils.createScene(primaryStage, grid);

        Text sceneTitle = FXUtils.createText("Welcome");
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label userName = FXUtils.createLabel("User name:", "Tahoma", FontWeight.NORMAL, 15);
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Button btn = new Button("Sign in");
        HBox hbBtn = FXUtils.createHBox(btn, 10, Pos.BOTTOM_RIGHT);
        grid.add(hbBtn, 1, 4);

        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 3);

        final TextArea area = new TextArea();
        grid.add(area, 0, 5, 3, 1);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    if (!FXUtils.hasNumbers(userTextField.getText())) {
                        actionTarget.setFill(Color.GREEN);
                        actionTarget.setText(userTextField.getText());
                    } else {
                        throw new RuntimeException("Found numbers:" + FXUtils.getNumbers(userTextField.getText()));
                    }
                }catch (RuntimeException ex){
                    FXUtils.printExceptions(ex, area);
                }
            }
        });
    }

}
