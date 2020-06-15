package simulation;

import javafx.scene.canvas.GraphicsContext;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Population of all species present in current epoch.
 */
public class Population {
    static int epoch = 1 ;
    private ArrayList<PopulationMember> populationMembers = new ArrayList<>();

    /**
     * Generate new population with given slow and fast population member size and their diameters.
     * @param fastPopulationSize number of fast species.
     * @param slowPopulationSize number of slow species.
     * @param slowPopulationMemberDiameters diameters of slow species.
     * @param fastPopulationMemberDiameters diameters of fast species.
     */
    public Population(int fastPopulationSize, int slowPopulationSize, Vector slowPopulationMemberDiameters, Vector fastPopulationMemberDiameters) {
        for (int i = 0; i < fastPopulationSize; i++){
            populationMembers.add(new FastPopulationMember(fastPopulationMemberDiameters));
        }
        for (int i = 0; i < slowPopulationSize; i++){
            populationMembers.add(new SlowPopulationMember(slowPopulationMemberDiameters));

        }
    }

    /**
     * Check if a new epoch should be started, if yes, start one.
     */
    public void checkForNewEpoch(){
        if (epoch < Main.targetEpochs){
            boolean isNewEpoch = true;
            for (PopulationMember populationMember : populationMembers) {
                if (!populationMember.isDead() && !populationMember.isNaturalDead()) {
                    isNewEpoch = false;
                    break;
                }
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

    /**
     * Draw all alive population members on the screen.
     * @return
     */
    private PopulationMember drawPopulationMember(){
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

    /**
     *
     * @param populationMembers
     */
    private void replicate(ArrayList<PopulationMember> populationMembers){
        System.out.println("Generating new population...");
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
                    PopulationMember parent1 = drawPopulationMember();
                    PopulationMember parent2 = drawPopulationMember();
                    while (!(parent1 instanceof SlowPopulationMember))
                        parent1 = drawPopulationMember();
                    while (!(parent2 instanceof SlowPopulationMember))
                        parent2 = drawPopulationMember();
                    newPopulation.add(new SlowPopulationMember(parent1, parent2));
                } else {
                    PopulationMember parent1 = drawPopulationMember();
                    PopulationMember parent2 = drawPopulationMember();
                    while (!(parent1 instanceof FastPopulationMember))
                        parent1 = drawPopulationMember();
                    while (!(parent2 instanceof FastPopulationMember))
                        parent2 = drawPopulationMember();
                    newPopulation.add(new FastPopulationMember(parent1, parent2));
                }
            }
            System.out.println("New population generated.");
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
