package simulation;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {
        static double dnaMutationChance = 0.02;
        static double dnaFullMutationChance = 0.01;
        static int dnaLifeLength = 150;
        static Vector initialVelocity = new Vector(0, 0);
        static ArrayList<Obstacle> obstacles = new ArrayList<>();
        static Vector startCoordinates = new Vector(50, 50);
        static int canvasWidth = 960;
        static int canvasHeight = 540;
        static int slowPopulationSize = 100;
        static int fastPopulationSize = 200;

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Hello World!");
        TextField textArea = new TextField();
        Label label = new Label();
        label.setText(
                "Arguments (-1 for default):\n"+
                "    1 - slow population size,\n" +
                "    2 - fast population size,\n" +
                "    3 - mutation chance,\n" +
                "    4 - full mutation chance,\n" +
                "    5 - canvas width,\n" +
                "    6 - canvas height.");
        Button btn = new Button();
        btn.setText("Run simulation");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String[] args = textArea.getText().split(" ");
                if (args.length >= 1 && !args[0].equals("-1") && !args[0].equals("")) slowPopulationSize = Integer.parseInt(args[0]);
                if (args.length >= 2 && !args[1].equals("-1")) fastPopulationSize = Integer.parseInt(args[1]);
                if (args.length >= 3 && !args[2].equals("-1")) dnaMutationChance = Double.parseDouble(args[2]);
                if (args.length >= 4 && !args[3].equals("-1")) dnaFullMutationChance = Double.parseDouble(args[3]);
                if (args.length >= 5 && !args[4].equals("-1")) canvasWidth = Integer.parseInt(args[4]);
                if (args.length >= 6 && !args[5].equals("-1")) canvasHeight = Integer.parseInt(args[5]);
                primaryStage.close();
                Canvas canvas = new Canvas(canvasWidth, canvasHeight);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                Pane root = new Pane();
                root.getChildren().add(canvas);
                primaryStage.setTitle("Drawing Test");
                Scene scene = new Scene(root, 960, 540);
                primaryStage.setScene(scene);
                primaryStage.show();
                start(scene, gc);

            }
        });

        VBox root = new VBox();
        root.getChildren().add(textArea);
        root.getChildren().add(btn);
        root.getChildren().add(label);
        textArea.setPromptText("Add paramaters here");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

    }
    private void draw(GraphicsContext gc){
        Population population = new Population(fastPopulationSize, slowPopulationSize, new Vector(20, 20), new Vector(15, 15));
        DrawTask drawTask = new DrawTask(gc, population, obstacles);
        drawTask.start();
    }
    private void start(Scene scene, GraphicsContext gc){
        gc.strokeText("Click 2 points to draw rectangles, press enter to start simulation.", canvasWidth/2-80, canvasHeight-20);
        AtomicBoolean isDrawingObstacle = new AtomicBoolean(false);
        final Vector[] pos1 = {new Vector(-1, -1)};
        scene.setOnMouseClicked(e -> {
            if (pos1[0].getX() == -1){
                pos1[0] = new Vector(e.getSceneX(), e.getSceneY());
                isDrawingObstacle.set(true);
            }
            else {
                obstacles.add(new Obstacle((int)pos1[0].getX(), (int) pos1[0].getY(), (int) e.getSceneX(), (int) e.getSceneY()));
                gc.setFill(Color.BLACK);
                gc.fillRect((int)pos1[0].getX(), (int) pos1[0].getY(), (int) e.getSceneX() - pos1[0].getX(), (int) e.getSceneY() - pos1[0].getY());
                pos1[0] = new Vector(-1, -1);
                isDrawingObstacle.set(false);

            }
        });



        scene.setOnKeyPressed((e)->{
            if (e.getCode().equals(KeyCode.ENTER)){
                System.out.println("Proces rysowania przeszkód zakończony, uruchamianie symulacji.");
                draw(gc);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
