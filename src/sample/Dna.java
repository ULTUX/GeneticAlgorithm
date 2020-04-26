package sample;

import java.util.Random;

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
     * @param parent1 First object to generate Dna from
     * @param parent2 Second object to generate Dna from
     * @throws Exception An exception is thrown when parents have not equal lifeLength values.
     */
    public Dna(Dna parent1, Dna parent2){
        if (parent1.getAcceleration().length == parent2.getAcceleration().length){
            int length = parent1.getAcceleration().length;
            acceleration = new Vector[length];
            final int split = sample.Random.randBetween(length/4, 3*length/4);
            for (int i = 0; i < length; i++){
                if (i < split){
                    acceleration[i] = parent1.getAcceleration()[i];
                }
                else {
                    acceleration[i] = parent2.getAcceleration()[i];
                }
            }

        }
    }

    public Vector[] getAcceleration() {
        return acceleration;
    }

    public Dna(double forceMultiplier) {
        this.acceleration = new Vector[lifeLength];
        this.forceMultiplier = forceMultiplier;
        generateNewDna(this.forceMultiplier);
    }

    private void generateNewDna(double forceMultiplier){
        Random random = new Random();
        for (int i = 0; i < lifeLength; i++){
            acceleration[i] = new Vector(random.nextDouble()*2-1, random.nextDouble()*2-1);
            acceleration[i].multiply(this.forceMultiplier);
        }
    }

    public Vector readNextDna(){
        if (pointerPos+1 < acceleration.length) return acceleration[++pointerPos];
        else return null;
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
            generateNewDna(this.forceMultiplier);
        }
    }


}
