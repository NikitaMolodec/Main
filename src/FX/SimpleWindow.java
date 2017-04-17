package FX;


import Common.CommonUtils;
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

        GridPane grid = FXUtils.createGridPane();

        Scene scene = FXUtils.createScene(primaryStage, grid);

        Text sceneTitle = FXUtils.createText("Welcome");
        grid.add(sceneTitle, 32, 0, 2, 1);

        Label inputText = FXUtils.createLabel("Input text:", Constants.DEFAULT_FAMILY, FontWeight.NORMAL, 15);
        grid.add(inputText, 32, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 33, 1);

        Button btn = new Button("Click on me");
        HBox hbBtn = FXUtils.createHBox(10, Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 33, 4);

        final Text actionTarget = new Text();
        grid.add(actionTarget, 33, 3);

        final TextArea area = new TextArea();
        grid.add(area, 0, 5, Constants.TEXTAREACOLUMNCOUNT, Constants.TEXTAREARAWCOUNT);
        area.setEditable(false);
        area.setVisible(false);
        area.setPrefColumnCount(Constants.TEXTAREACOLUMNCOUNT);
        area.setPrefRowCount(Constants.TEXTAREARAWCOUNT);

        //todo какие исключения ловить при нажатии кнопки?
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    if (!CommonUtils.hasNumbers(userTextField.getText())) {
                        actionTarget.setFill(Color.GREEN);
                        actionTarget.setText(userTextField.getText());
                        actionTarget.setVisible(true);
                        area.setVisible(false);
                    } else {
                        throw new RuntimeException("Found numbers:" + CommonUtils.getNumbers(userTextField.getText()));
                    }
                }catch (RuntimeException ex){
                    actionTarget.setVisible(false);
                    area.setVisible(true);
                    area.setText(CommonUtils.stackTraceToString(ex));
                }
            }
        });
    }

}
