package sample;

public class Dna {
    int lifeLength;
    private Vector[] acceleration;
    private int pointerPos = 0;
    private double mutationChance;
    private double fullMutationChance;
    private double forceMultiplier;

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

    /**
     * Generate completely new Dna object.
     * @param lifeLength Length of an array containing Dna sequence.
     */
    public Dna(int lifeLength, double mutationChance, double fullMutationChance, double forceMultiplier) {
        this.mutationChance = mutationChance;
        this.fullMutationChance = fullMutationChance;
        this.acceleration = new Vector[lifeLength];
        this.lifeLength = lifeLength;
        this.forceMultiplier = forceMultiplier;
        generateNewDna();
    }

    private void generateNewDna(){
        for (int i = 0; i < lifeLength; i++){
            acceleration[i] = new Vector(Math.random()*2-1, Math.random()*2-1);
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
