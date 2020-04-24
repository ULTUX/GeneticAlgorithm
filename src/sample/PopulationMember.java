package sample;


import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class PopulationMember extends Movable implements Drawable{
    private boolean dead = false;
    private Dna dna;
    private static int height, width;
    private static ArrayList<Obstacle> obstacles;
    private static Vector initialVelocity;
    private static Vector startCoordinates;

    static {
        height = Main.PopulationMemberHeight;
        width = Main.PopulationMemberWidth;
        initialVelocity = Main.initialVelocity;
        obstacles = Main.obstacles;
        startCoordinates = Main.startCoordinates;
    }

    public PopulationMember() {
        posX = startCoordinates.getX();
        posY = startCoordinates.getY();
        velocity = new Vector(initialVelocity.getX(), initialVelocity.getY());
        dna = new Dna();
    }

    public Dna getDna() {
        return dna;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }


    @Override
    void move() {
        double x = dna.readNextDna().getX();
        double y = dna.readNextDna().getY();
        velocity.addVector(new Vector(x, y));
        super.move();
        for (Obstacle obstacle : obstacles) {
            if (obstacle.isInObstacle(this.posX, this.posY, width, height)) dead = true;
        }
        if (posX < 0 || posX+width > Main.canvasWidth || posY < 0 || posY+20 > Main.canvasHeight) dead = true;

    }
    @Override
    public void draw(GraphicsContext gc){
        gc.fillRect(Math.round(this.posX), Math.round(this.posY), width, height);
    }

}
