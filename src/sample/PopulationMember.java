package sample;


import javafx.scene.canvas.GraphicsContext;

public class PopulationMember extends Moveable {
    public PopulationMember(int x, int y, Vector velocity) {
        this.posX = x;
        this.posY = y;
        this.velocity = velocity;
    }
    public void draw(GraphicsContext gc){
        gc.fillRect(this.posX, this.posY, 20, 20);
        move();
    }
}
