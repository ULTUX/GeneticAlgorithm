package simulation;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Specifies actions required for a simulation to be drawn and ran properly.
 */
public class DrawTask extends AnimationTimer {
    private final GraphicsContext gc;
    private final Population population;
    private final ArrayList<Obstacle> obstacles;
    public DrawTask(GraphicsContext gc, Population population, ArrayList<Obstacle> obstacles) {
        this.population = population;
        this.gc = gc;
        this.obstacles = obstacles;
    }

    /**
     * Function required by javafx.animation.AnimationTimer containing actions ran every frame.
     * @param l The timestamp of the current frame given in nanoseconds.
     */
    public void handle(long l) {
        if (Main.simulationRunning){
            gc.clearRect(0,0, Main.canvasWidth, Main.canvasHeight);
            population.drawPopulation(gc);
            gc.strokeText("Epoch number: "+Population.epoch, Main.canvasWidth-100, 15);
            for (Obstacle obstacle : obstacles) {
                obstacle.draw(gc);
            }
            gc.fillOval(Main.canvasWidth-50, Main.canvasHeight-50, 50, 50);
            population.movePopulation();
            population.checkForNewEpoch();
        }
        else {
            gc.clearRect(0,0, Main.canvasWidth, Main.canvasHeight);
            gc.strokeText("Simulation ended, you can close this window.", Main.canvasWidth/2-170, Main.canvasHeight/2);
            stop();
        }

    }
}
