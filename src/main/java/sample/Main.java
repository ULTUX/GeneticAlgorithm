package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Timer;

public class Main extends Application {
        static double dnaMutationChance = 0.02;
        static double dnaFullMutationChance = 0.01;
        static int dnaLifeLength = 150;
        static Vector initialVelocity = new Vector(0, 0);
        static ArrayList<Obstacle> obstacles = new ArrayList<>();
        static Vector startCoordinates = new Vector(50, 50);
        static int canvasWidth = 960;
        static int canvasHeight = 540;
        static int slowPopulationSize = 200;
        static int fastPopulationSize = 100;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        draw(gc);
        Pane root = new Pane();
        root.getChildren().add(canvas);
        primaryStage.setTitle("Drawing Test");
        primaryStage.setScene(new Scene(root, 960, 540));
        primaryStage.show();
    }
    private void draw(GraphicsContext gc){
        Timer timer = new Timer();
        obstacles.add(new Obstacle(200, 250, 660, 270));
        Population population = new Population(fastPopulationSize, slowPopulationSize, new Vector(20, 20), new Vector(15, 15));
        timer.schedule(new DrawTask(gc, population, obstacles), 100, 20);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
