package simulation;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FastPopulationMember extends PopulationMember {

    public FastPopulationMember(Vector diameters) {
        super();
        setWidth((int)diameters.getX());
        setHeight((int)diameters.getY());
        setDna(new Dna(1.2));
    }
    public FastPopulationMember(PopulationMember pm1, PopulationMember pm2){
            Dna dna = new Dna(pm1.getDna(), pm2.getDna());
            position.setVector(getStartCoordinates());
            velocity = new Vector(getInitialVelocity().getX(), getInitialVelocity().getY());
            setWidth(pm1.getWidth());
            setHeight(pm1.getHeight());
            setDna(dna);
    }

    //Do poprawy
    @Override
    public double calcFitness() {
        double fitness = position.length(new Vector(Main.canvasWidth - 50, Main.canvasHeight - 50));
        if (isDead()) fitness += 0.9*fitness;
        if ((position.getX() < Main.canvasWidth && position.getX() > Main.canvasWidth - 100) &&
                (position.getY() < Main.canvasHeight && position.getY() > Main.canvasHeight - 100)) fitness -= 0.9*fitness;
        fitness = 1/ fitness;
        setFitness(fitness);
        return fitness;
    }


    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        gc.fillRect(Math.round(position.getX()), Math.round(position.getY()), getWidth(), getHeight());
    }
}
