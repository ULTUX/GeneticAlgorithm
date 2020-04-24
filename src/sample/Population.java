package sample;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Population {
    private static ArrayList<PopulationMember> populationMembers;
    private static int populationSize;
    static {
        populationMembers = new ArrayList<>();
        populationSize = Main.populationSize;
    }

    public static ArrayList<PopulationMember> getPopulationMembers() {
        return populationMembers;
    }

    public Population() {
        for (int i = 0; i < populationSize; i++){
            populationMembers.add(new PopulationMember());
        }
    }
    public void movePopulation(){
        for (PopulationMember populationMember : populationMembers) {
                if (!populationMember.isDead()){
                    populationMember.move();
                }
        }
    }
    public void drawPopulation(GraphicsContext gc){
        for (PopulationMember populationMember : populationMembers) {
            populationMember.draw(gc);
        }
    }
}
