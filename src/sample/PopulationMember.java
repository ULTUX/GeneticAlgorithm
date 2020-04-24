package sample;


import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class PopulationMember extends Movable implements Drawable{
    boolean isDead = false;
    Dna dna;
    int height, width;
    ArrayList<Obstacle> obstacles;

    public PopulationMember(double dnaMutationChance, double dnaFullMutationChance, int dnaLifeLength, Vector initialVelocity, double forceMultiplier, ArrayList<Obstacle> obstacles, int height, int width) {
        this.obstacles = obstacles;
        dna = new Dna(dnaLifeLength, dnaMutationChance, dnaFullMutationChance, forceMultiplier);
        velocity = initialVelocity;
        this.height = height;
        this.width = width;
    }

    public Dna getDna() {
        return dna;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }


    @Override
    void move() {
        super.move();
        double x = dna.readNextDna().getX();
        double y = dna.readNextDna().getY();
        velocity.addVector(new Vector(x, y));
        for (Obstacle obstacle : obstacles) {
            if (obstacle.isInObstacle(this.posX, this.posY, width, height)) isDead = true;
        }

    }
    @Override
    public void draw(GraphicsContext gc){
        gc.fillRect(Math.round(this.posX), Math.round(this.posY), width, height);
    }

}
