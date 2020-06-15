package simulation;



import java.util.ArrayList;

/**
 * Common features of all population members.
 */
public abstract class PopulationMember extends Movable implements Drawable{
    private boolean dead = false;
    private boolean naturalDead = false;
    private static ArrayList<Obstacle> obstacles;
    private int width, height;
    private static Vector initialVelocity;
    private static Vector startCoordinates;
    private Dna dna;
    private double fitness = -1;

    static {
        initialVelocity = Main.initialVelocity;
        obstacles = Main.obstacles;
        startCoordinates = Main.startCoordinates;
    }

    /**
     * Create new PopulationMember.
     */
    public PopulationMember() {
        position.setVector(startCoordinates);
        velocity = new Vector(initialVelocity.getX(), initialVelocity.getY());
    }

    /**
     * Check if this population member died naturally (didn't bump into obstacle).
     * @return whether it died naturally or not.
     */
    public boolean isNaturalDead() {
        return naturalDead;
    }

    /**
     * Set natural death of this population member.
     * @param naturalDead whether a natural death occured or not.
     */
    public void setNaturalDead(boolean naturalDead) {
        this.naturalDead = naturalDead;
    }

    /**
     * Calculate fitness of this object.
     * Fitness is a value telling how good is genetic information of this object.
     * The bigger the value the higher the chance of this species genetic information to survive.
     * @return Fitness of this object.
     */
    public abstract double calcFitness();

    @Override
    void move() {
        if (!isDead() && !isNaturalDead()){
            Vector nextDna = dna.readNextDna();
            if (nextDna != null) {
                double x = nextDna.getX();
                double y = nextDna.getY();
                velocity.addVector(new Vector(x, y));
                super.move();
                for (Obstacle obstacle : getObstacles()) {
                    if (obstacle.isInObstacle(position.getX(), position.getY(), width, height)) setDead(true);
                }
                if (position.getX() < 0 || position.getX() + width > Main.canvasWidth || position.getY() < 0 || position.getY() + height > Main.canvasHeight)
                    setDead(true);
            }
            else setNaturalDead(true);
        }
    }

    /**
     * Get the fitness of this object.
     * @return fitness
     */
    public double getFitness() {
        return fitness;
    }

    /**
     * Set fitness value to be different.
     * @param fitness a desired fitness value.
     */
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    /**
     * Get this objects initial velocity.
     * @return
     */
    public static Vector getInitialVelocity() {
        return initialVelocity;
    }

    /**
     * Get this species start coordinates.
     * @return a vector containing this start coordinates.
     */
    public static Vector getStartCoordinates() {
        return startCoordinates;
    }

    /**
     * Get this species genetic information in form of an Dna object.
     * @return this objects DNA.
     */
    public Dna getDna() {
        return dna;
    }

    /**
     * Set this objects Dna to be different.
     * @param dna a dna to be set to.
     */
    public void setDna(Dna dna) {
        this.dna = dna;
    }

    /**
     * Get an ArrayList of all obstacles that are seen by this population member.
     * @return list of all obstacles.
     */
    public static ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * Get this population member render width.
     * @return render width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set this population member render width.
     * @param width render width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Get this population member render height.
     * @return render height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set render height of this object.
     * @param height render height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Check if this object is dead.
     * @return Whether it is dead or not.
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Set whether this object is dead or not.
     * @param dead specified value.
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }


}
