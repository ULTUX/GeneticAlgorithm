package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SlowPopulationMember extends PopulationMember {

    public SlowPopulationMember(Vector diameters) {
        super();
        setWidth((int)diameters.getX());
        setHeight((int)diameters.getY());
        setDna(new Dna(0.9));
    }

    public SlowPopulationMember(PopulationMember pm1, PopulationMember pm2){
        Dna dna = new Dna(pm1.getDna(), pm2.getDna());
        position.setVector(getStartCoordinates());
        velocity = new Vector(getInitialVelocity().getX(), getInitialVelocity().getY());
        setWidth(pm1.getWidth());
        setHeight(pm1.getHeight());
        setDna(dna);
    }


    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(Math.round(position.getX()), Math.round(position.getY()), getWidth(), getHeight());
    }
}
