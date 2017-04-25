package angularMotion.view;

import angularMotion.controller.Core;
import angularMotion.controller.Validate;
import angularMotion.model.MoveParams;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Window extends Application {

    private static final double DEFAULT_FIELD_WIDTH = 70;

    private final TextField x0 = new TextField();
    private final TextField y0 = new TextField();
    private final TextField Vx0 = new TextField();
    private final TextField Vy0 = new TextField();
    private final TextField time = new TextField();
    private final TextField frequency = new TextField();
    private final Text error = new Text("Wrong input data");
    private final Text maxX = new Text();
    private final Text maxY = new Text();
    private final Text movementTime = new Text();
    private final Text distance = new Text();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Movement at the angle to the horizon");

        //используем BorderPane как скилет нашего окна
        BorderPane border = new BorderPane();
        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(700);
        primaryStage.setScene(new Scene(border));
        border.setStyle("-fx-background-color:#fff;");

        //создаем поле для ввода начальных данных
        StackPane stack = new StackPane();
        Button btn = new Button("Start");
        btn.setMinWidth(100);
        stack.getChildren().addAll(addGridPane(), btn);
        stack.setAlignment(Pos.CENTER_RIGHT);
        StackPane.setMargin(btn, new Insets(0, 10, 0, 0));
        border.setBottom(stack);

        //создаем поле для вывода праметров движения
        border.setRight(addMovingParams());

        //создаем поля для вывода траектории
        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();
        LineChart<Number, Number> numberNumberLineChart = new LineChart<Number, Number>(x, y);
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Trajectory");
        border.setCenter(numberNumberLineChart);

        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e) {
                if(Validate.allValid(x0, y0, Vx0, Vy0, time, frequency, error)) {
                    error.setVisible(false);
                    Core traj = new Core(new MoveParams(Double.parseDouble(x0.getText()), Double.parseDouble(y0.getText()),
                            Double.parseDouble(Vx0.getText()), Double.parseDouble(Vy0.getText())));
                    traj.printTrajectory(numberNumberLineChart, series, time, frequency);
                    traj.printParams(maxX, maxY, movementTime, distance, time, frequency);
                }
            }
        });
        primaryStage.show();
    }

    private GridPane addGridPane(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(15));

        Label initialCoordinates = new Label("Initial Coordinates:");
        initialCoordinates.setMinWidth(125);
        initialCoordinates.setPrefWidth(125);
        grid.add(initialCoordinates, 0, 0);

        Label x = new Label("X:");
        x.setMinWidth(15);
        x.setPrefWidth(15);
        grid.add(x, 1, 0);
        Label y = new Label("Y:");
        grid.add(y, 1, 1);

        x0.setMinWidth(DEFAULT_FIELD_WIDTH);
        x0.setPrefWidth(DEFAULT_FIELD_WIDTH);
        y0.setPrefWidth(DEFAULT_FIELD_WIDTH);
        grid.add(x0, 2, 0);
        grid.add(y0, 2, 1);

        Label initialSpeeds = new Label("Initial Speeds:");
        initialSpeeds.setMinWidth(95);
        initialSpeeds.setPrefWidth(95);
        grid.add(initialSpeeds, 3, 0);

        Label Vx = new Label("Vx:");
        Vx.setMinWidth(20);
        Vx.setPrefWidth(20);
        grid.add(Vx, 4, 0);
        Label Vy = new Label("Vy:");
        grid.add(Vy, 4, 1);

        Vx0.setMinWidth(DEFAULT_FIELD_WIDTH);
        Vx0.setPrefWidth(DEFAULT_FIELD_WIDTH);
        Vy0.setPrefWidth(DEFAULT_FIELD_WIDTH);
        grid.add(Vx0, 5, 0);
        grid.add(Vy0, 5, 1);

        Label simulationTime = new Label("Simulation Time:");
        simulationTime.setMinWidth(110);
        simulationTime.setPrefWidth(110);
        grid.add(simulationTime, 6, 0);

        time.setMinWidth(DEFAULT_FIELD_WIDTH);
        time.setPrefWidth(DEFAULT_FIELD_WIDTH);
        grid.add(time, 7, 0);

        Label freq = new Label("Frequency (units in second):");
        freq.setMinWidth(185);
        freq.setPrefWidth(185);
        grid.add(freq, 8, 0);

        frequency.setMinWidth(DEFAULT_FIELD_WIDTH);
        frequency.setPrefWidth(DEFAULT_FIELD_WIDTH);
        grid.add(frequency, 9, 0);

        error.setFill(Color.FIREBRICK);
        error.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
        error.setVisible(false);
        grid.add(error, 7, 1, 3,1);

        grid.setStyle("-fx-background-color:#34b3f7;");
        return grid;
    }

    private GridPane addMovingParams(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(15));

        Text title = new Text("Moving parameters:");
        title.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(title, 0, 0, 2, 1);

        Text highestPoint = new Text("Highest point:");
        grid.add(highestPoint, 0, 2);
        Text x = new Text("X:");
        grid.add(x, 0, 3);
        Text y = new Text("Y:");
        grid.add(y, 0, 4);
        grid.add(maxX, 1, 3);
        grid.add(maxY, 1, 4);

        Text movTime = new Text("Movement time:");
        grid.add(movTime, 0, 6);
        grid.add(movementTime, 1, 6);

        Text dist = new Text("Distance:");
        grid.add(dist, 0, 8);
        grid.add(distance, 1, 8);

        grid.setStyle("-fx-background-color:#45f7ad;");

        return grid;
    }

}
