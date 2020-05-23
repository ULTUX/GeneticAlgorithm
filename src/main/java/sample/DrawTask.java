package sample;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.TimerTask;

public class DrawTask extends TimerTask {
    private final GraphicsContext gc;
    private final Population population;
    private final ArrayList<Obstacle> obstacles;
    public DrawTask(GraphicsContext gc, Population population, ArrayList<Obstacle> obstacles) {
        this.population = population;
        this.gc = gc;
        this.obstacles = obstacles;
    }

    @Override
    public void run() {
        gc.clearRect(0,0, Main.canvasWidth, Main.canvasHeight);
        population.drawPopulation(gc);
        gc.fillOval(Main.canvasWidth-50, Main.canvasHeight-50, 50, 50);
        population.movePopulation();
        population.checkForNewEpoch();
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(gc);
        }

    }
}
