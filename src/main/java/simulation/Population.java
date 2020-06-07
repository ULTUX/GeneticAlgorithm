package simulation;

import javafx.scene.canvas.GraphicsContext;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Population {
    static int epoch = 1 ;
    private ArrayList<PopulationMember> populationMembers = new ArrayList<>();

    public Population(int fastPopulationSize, int slowPopulationSize, Vector slowPopulationMemberDiameters, Vector fastPopulationMemberDiameters) {
        for (int i = 0; i < fastPopulationSize; i++){
            populationMembers.add(new FastPopulationMember(fastPopulationMemberDiameters));
        }
        for (int i = 0; i < slowPopulationSize; i++){
            populationMembers.add(new SlowPopulationMember(slowPopulationMemberDiameters));

        }
    }

    public void checkForNewEpoch(){
        if (epoch < Main.targetEpochs){
            boolean isNewEpoch = true;
            for (PopulationMember populationMember : populationMembers) {
                if (!populationMember.isDead() && !populationMember.isNaturalDead()) isNewEpoch = false;
            }
            if (isNewEpoch) {
                replicate(populationMembers);
            }
        }
        else {
            Main.simulationRunning = false;
            System.out.println("Simulation ended, collecting results.");
            try {
                final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./simulationResults.csv"));

            populationMembers.forEach((populationMember -> {
                if (populationMember instanceof SlowPopulationMember){
                    try {
                        bufferedWriter.write("slow");
                        bufferedWriter.write(","+populationMember.getDna().toString());
                        bufferedWriter.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (populationMember instanceof FastPopulationMember){
                    try {
                        bufferedWriter.write("fast");
                        bufferedWriter.write(","+populationMember.getDna().toString());
                        bufferedWriter.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }));
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private PopulationMember drawPopulationMember(ArrayList<PopulationMember> populationMembers){
        boolean found = false;
        int index = 0;
        while (!found){
            index = ThreadLocalRandom.current().nextInt(0, populationMembers.size());;
            if (Math.random() <= populationMembers.get(index).getFitness()){
                found = true;
            }
        }
        return populationMembers.get(index);
    }

    private void replicate(ArrayList<PopulationMember> populationMembers){
        double sumFitness = 0;
        epoch++;
        for (int i = 0; i < populationMembers.size(); i++){
            sumFitness += populationMembers.get(i).calcFitness();
        }
        for (int i = 0; i < populationMembers.size(); i++){
            PopulationMember pm = populationMembers.get(i);
            pm.setFitness(pm.getFitness()/sumFitness);
        }

        ArrayList<PopulationMember> newPopulation = new ArrayList<>();

            for (int i = 0; i < populationMembers.size(); i++) {
                if (populationMembers.get(i) instanceof SlowPopulationMember) {
                    PopulationMember parent1 = drawPopulationMember(populationMembers);
                    PopulationMember parent2 = drawPopulationMember(populationMembers);
                    while (!(parent1 instanceof SlowPopulationMember))
                        parent1 = drawPopulationMember(populationMembers);
                    while (!(parent2 instanceof SlowPopulationMember))
                        parent2 = drawPopulationMember(populationMembers);
                    newPopulation.add(new SlowPopulationMember(parent1, parent2));
                } else {
                    PopulationMember parent1 = drawPopulationMember(populationMembers);
                    PopulationMember parent2 = drawPopulationMember(populationMembers);
                    while (!(parent1 instanceof FastPopulationMember))
                        parent1 = drawPopulationMember(populationMembers);
                    while (!(parent2 instanceof FastPopulationMember))
                        parent2 = drawPopulationMember(populationMembers);
                    newPopulation.add(new FastPopulationMember(parent1, parent2));
                }
            }
            System.out.println("Nowa populacja:"+ newPopulation);
            this.populationMembers = newPopulation;
    }

    public void movePopulation(){
        for (PopulationMember populationMember : populationMembers) {
                if (!populationMember.isDead() && !populationMember.isNaturalDead()){
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
