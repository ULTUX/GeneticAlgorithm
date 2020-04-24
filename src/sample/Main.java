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

    @Override
    public void start(Stage primaryStage) throws Exception{
        Canvas canvas = new Canvas(960, 540);
        GraphicsContext gp = canvas.getGraphicsContext2D();
        draw(gp);
        Pane root = new Pane();
        root.getChildren().add(canvas);
        primaryStage.setTitle("Drawing Test");
        primaryStage.setScene(new Scene(root, 960, 540));
        primaryStage.show();
    }
    private void draw(GraphicsContext gp){
        Timer timer = new Timer();
        ArrayList<PopulationMember> populationMembers = new ArrayList<>();
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(200, 250, 660, 270));
        PopulationMember pm = new PopulationMember(0.1, 0.01, 2000, new Vector(0, 0), 1.2, obstacles, 20, 20);
        populationMembers.add(pm);
        timer.schedule(new DrawTask(gp, populationMembers, obstacles), 100, 50);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
