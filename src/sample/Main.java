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
        Canvas canvas = new Canvas(2000, 3000);
        GraphicsContext gp = canvas.getGraphicsContext2D();
        draw(gp);
        Pane root = new Pane();
        root.getChildren().add(canvas);
        primaryStage.setTitle("Drawing Test");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
    private void draw(GraphicsContext gp){
        PopulationMember pm = new PopulationMember(50, 50, new Vector(1, 1));
        Timer timer = new Timer();
        ArrayList<PopulationMember> populationMembers = new ArrayList();
        populationMembers.add(pm);
        timer.schedule(new DrawTask(gp, populationMembers), 100, 100);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
