package sample;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.Formattable;
import java.util.HashMap;

public class Population {
    private ArrayList<PopulationMember> fastPopulationMembers = new ArrayList<>();
    private ArrayList<PopulationMember> slowPopulationMembers = new ArrayList<>();

    public Population(int fastPopulationSize, int slowPopulationSize, Vector slowPopulationMemberDiameters, Vector fastPopulationMemberDiameters) {
        for (int i = 0; i < fastPopulationSize; i++){
            fastPopulationMembers.add(new FastPopulationMember(fastPopulationMemberDiameters));
        }
        for (int i = 0; i < slowPopulationSize; i++){
            slowPopulationMembers.add(new SlowPopulationMember(slowPopulationMemberDiameters));

        }
    }

    public void checkForNewEpoch(){
        boolean isNewEpoch = true;
        for (PopulationMember populationMember : fastPopulationMembers) {
            if (!populationMember.isDead()) isNewEpoch = false;
        }
        for (PopulationMember populationMember : slowPopulationMembers){
            if (!populationMember.isDead()) isNewEpoch = false;
        }
        if (isNewEpoch) {
            replicate(slowPopulationMembers);
            replicate(fastPopulationMembers);
        }
    }

    private PopulationMember drawPopulationMember(ArrayList<PopulationMember> populationMembers){
        boolean found = false;
        int index = 0;
        while (!found){
            index = (int)Random.randBetween(0, populationMembers.size()-1);
            if (Math.random() <= populationMembers.get(index).getFitness()){
                found = true;
            }
        }
        return populationMembers.get(index);
    }

    private void replicate(ArrayList<PopulationMember> populationMembers){
        double sumFitness = 0;

        for (int i = 0; i < populationMembers.size(); i++){
            sumFitness += populationMembers.get(i).calcFitness();
        }
        for (int i = 0; i < populationMembers.size(); i++){
            PopulationMember pm = populationMembers.get(i);
            pm.setFitness(pm.getFitness()/sumFitness);
        }

        ArrayList<PopulationMember> newPopulation = new ArrayList<>();

        if (populationMembers.get(0) instanceof SlowPopulationMember){
            for (int i = 0; i < populationMembers.size(); i++){
                PopulationMember parent1 = drawPopulationMember(populationMembers);
                PopulationMember parent2 = drawPopulationMember(populationMembers);
                newPopulation.add(new SlowPopulationMember(parent1, parent2));
            }
            slowPopulationMembers = newPopulation;
        }
        else {
            for (int i = 0; i < populationMembers.size(); i++){
                PopulationMember parent1 = drawPopulationMember(populationMembers);
                PopulationMember parent2 = drawPopulationMember(populationMembers);
                newPopulation.add(new FastPopulationMember(parent1, parent2));
            }
            fastPopulationMembers = newPopulation;
        }
    }

    public void movePopulation(){
        for (PopulationMember populationMember : slowPopulationMembers) {
                if (!populationMember.isDead()){
                    populationMember.move();
                }
        }
        for (PopulationMember populationMember : fastPopulationMembers) {
            if (!populationMember.isDead()){
                populationMember.move();
            }
        }
    }
    public void drawPopulation(GraphicsContext gc){
        for (PopulationMember populationMember : fastPopulationMembers) {
            populationMember.draw(gc);
        }
        for (PopulationMember populationMember : slowPopulationMembers) {
            populationMember.draw(gc);
        }
    }
}
