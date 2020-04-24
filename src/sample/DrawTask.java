package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.TimerTask;

public class DrawTask extends TimerTask {
    private GraphicsContext gp;
    private ArrayList<PopulationMember> population;
    private ArrayList<Obstacle> obstacles;
    public DrawTask(GraphicsContext gp, ArrayList<PopulationMember> pop, ArrayList<Obstacle> obstacles) {
        population = pop;
        this.gp = gp;
        this.obstacles = obstacles;
    }

    @Override
    public void run() {
        gp.clearRect(0,0, 1000, 1000);
        population.forEach((populationMember)->{
            populationMember.draw(gp);
            if (!populationMember.isDead()){
                populationMember.move();
            }
        });
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(gp);
        }

    }
}
