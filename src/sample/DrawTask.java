package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.TimerTask;

public class DrawTask extends TimerTask {
    private GraphicsContext gp;
    private ArrayList<PopulationMember> population;
    public DrawTask(GraphicsContext gp, ArrayList pop) {
        population = pop;
        this.gp = gp;
    }

    @Override
    public void run() {
        gp.clearRect(0,0, 1000, 1000);
        population.forEach((populationMember)->{
            populationMember.draw(gp);
        });
    }
}
