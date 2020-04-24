package sample;

import java.util.Random;

public class Dna {
    static int lifeLength;
    private Vector[] acceleration;
    private int pointerPos = 0;
    static private double mutationChance;
    static private double fullMutationChance;
    static private double forceMultiplier;

    static {
    lifeLength = Main.dnaLifeLength;
    mutationChance = Main.dnaMutationChance;
    fullMutationChance = Main.dnaFullMutationChance;
    forceMultiplier = Main.forceMultiplier;
    }

    /**
     * Create a new Dna object from crossover of other already existing Dna objects.
     * @param parent1 First object to generate Dna from
     * @param parent2 Second object to generate Dna from
     * @throws Exception An exception is thrown when parents have not equal lifeLength values.
     */
    public Dna(Vector[] parent1, Vector[] parent2) throws Exception {
        if (parent1.length == parent2.length){

        } else {
            throw new Exception("Not matching parents dna size.");
        }
    }

    public Dna() {
        this.acceleration = new Vector[lifeLength];
        generateNewDna();
    }

    private void generateNewDna(){
        Random random = new Random();
        for (int i = 0; i < lifeLength; i++){
            acceleration[i] = new Vector(random.nextDouble()*2-1, random.nextDouble()*2-1);
            acceleration[i].multiply(forceMultiplier);
        }
    }

    public Vector readNextDna(){
        return acceleration[++pointerPos];
    }
    public void mutate(){
        for (int i = 0; i < lifeLength; i++){
            if (Math.random() <= mutationChance){
                acceleration[i] = new Vector(Math.random()*2-1, Math.random()*2-1);
            }
        }
    }
    public void fullMutate(){
        if (Math.random() <= fullMutationChance){
            generateNewDna();
        }
    }


}
