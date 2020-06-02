package sample;

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
        timer.schedule(new DrawTask(gc, population, obstacles), 10, 20);
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

    public static void main(String[] args) {
        launch(args);
    }
}
