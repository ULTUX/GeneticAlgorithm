package sample;



import java.util.ArrayList;

public abstract class PopulationMember extends Movable implements Drawable{
    private boolean dead = false;
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

    public PopulationMember() {
        position.setVector(startCoordinates);
        velocity = new Vector(initialVelocity.getX(), initialVelocity.getY());

    }

    public abstract double calcFitness();

    @Override
    void move() {
        if (!isDead()){
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
            else setDead(true);
        }
        else {
            System.out.println(isDead());
        }
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public static Vector getInitialVelocity() {
        return initialVelocity;
    }

    public static Vector getStartCoordinates() {
        return startCoordinates;
    }

    public Dna getDna() {
        return dna;
    }


    public void setDna(Dna dna) {
        this.dna = dna;
    }

    public static ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }


}
