package simulation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
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
    public void start(Stage primaryStage) throws Exception{
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
    private void draw(GraphicsContext gc){
        Timer timer = new Timer();
        Population population = new Population(fastPopulationSize, slowPopulationSize, new Vector(20, 20), new Vector(15, 15));
        DrawTask drawTask = new DrawTask(gc, population, obstacles);
        drawTask.start();
    }
    private void start(Scene scene, GraphicsContext gc){
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

    /*
    Launch args;
    0 - slow population size,
    1 - fast population size,
    2 - mutation chance
    3 - full mutation chance,
    4 - canvas width
    5 - canvas height
     */
    public static void main(String[] args) {
        System.out.println(args.length);
        if (args[0] != null) slowPopulationSize = Integer.parseInt(args[0]);
        if (args[1] != null) fastPopulationSize = Integer.parseInt(args[1]);
        if (args[2] != null) dnaMutationChance = Double.parseDouble(args[2]);
        if (args[3] != null) dnaFullMutationChance = Double.parseDouble(args[3]);
        if (args[4] != null) canvasWidth = Integer.parseInt(args[4]);
        if (args[5] != null) canvasHeight = Integer.parseInt(args[5]);
        launch(args);
    }
}
