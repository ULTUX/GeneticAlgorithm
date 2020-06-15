package simulation;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents genetic information of species
 */
public class Dna {
    static int lifeLength;
    private Vector[] acceleration;
    private int pointerPos = 0;
    static private double mutationChance;
    static private double fullMutationChance;
    private double forceMultiplier;

    static {
    lifeLength = Main.dnaLifeLength;
    mutationChance = Main.dnaMutationChance;
    fullMutationChance = Main.dnaFullMutationChance;
    }

    /**
     * Create a new Dna object from crossover of other already existing Dna objects.
     * @param parent1 First object to generate Dna from.
     * @param parent2 Second object to generate Dna from.
     */
    public Dna(Dna parent1, Dna parent2){
        if (ThreadLocalRandom.current().nextDouble() <= fullMutationChance){
            this.acceleration = new Vector[lifeLength];
            this.forceMultiplier = forceMultiplier;
            generateNewDna(this.forceMultiplier);
        } else {
            if (parent1.getAcceleration().length == parent2.getAcceleration().length){
                int length = parent1.getAcceleration().length;
                acceleration = new Vector[length];
//                final int split = ThreadLocalRandom.current().nextInt(Math.round((float)length/4), Math.round((float)3*length/4));
                final int split = Math.round(length/2f);
                for (int i = 0; i < length; i++){
                    if (ThreadLocalRandom.current().nextDouble() <= mutationChance){
                        acceleration[i] = new Vector(ThreadLocalRandom.current().nextDouble()*2-1, ThreadLocalRandom.current().nextDouble()*2-1);
                    } else {
                        if (i < split){
                            acceleration[i] = parent1.getAcceleration()[i];
                        }
                        else {
                            acceleration[i] = parent2.getAcceleration()[i];
                        }
                    }
                }

            }
        }
    }

    /**
     * Get the acceleration vector of this dna object.
     * @return Vector filled with acceleration values.
     */
    public Vector[] getAcceleration() {
        return acceleration;
    }

    /**
     * Generate completely new Dna object filled with random values.
     * @param forceMultiplier Maximum acceleration force.
     */
    public Dna(double forceMultiplier) {
        this.acceleration = new Vector[lifeLength];
        this.forceMultiplier = forceMultiplier;
        generateNewDna(this.forceMultiplier);
    }

    /**
     * Generating new random dna vector of this object.
     * @param forceMultiplier Maximum acceleration force.
     */
    private void generateNewDna(double forceMultiplier){
        for (int i = 0; i < lifeLength; i++){
            acceleration[i] = new Vector(ThreadLocalRandom.current().nextDouble()*2-1, ThreadLocalRandom.current().nextDouble()*2-1);
            acceleration[i].multiply(this.forceMultiplier);
        }
    }

    /**
     * Read next acceleration value in the array and move pointer by 1.
     * @return Acceleration value. If pointer reached end of an array, return null
     */
    public Vector readNextDna(){
        if (pointerPos+1 < acceleration.length) return acceleration[++pointerPos];
        else return null;
    }

    /**
     * Perform mutation of random acceleration  values.
     */
    public void mutate(){
        for (int i = 0; i < lifeLength; i++){
            if (Math.random() <= mutationChance){
                acceleration[i] = new Vector(Math.random()*2-1, Math.random()*2-1);
            }
        }
    }

    /**
     * If random value (1,0) <= full mutation chance, perform full mutation of this object.
     */
    public void fullMutate(){
        if (Math.random() <= fullMutationChance){
            generateNewDna(this.forceMultiplier);
        }
    }

    /**
     * Builds string based of acceleration Vector values.
     * @return String containing all Vector values as one line of csv format.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (Vector vector : acceleration) sb.append(vector.getX()).append(",").append(vector.getY());
        return sb.toString();
    }
}
