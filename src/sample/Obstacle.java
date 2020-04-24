package sample;

import javafx.scene.canvas.GraphicsContext;

public class Obstacle implements Drawable{
    int xStart, yStart;
    int xEnd, yEnd;

    public Obstacle(int xStart, int yStart, int xEnd, int yEnd) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }

    public boolean isInObstacle(double x, double y, int width, int height){
        int posX = (int) Math.round(x);
        int posY = (int) Math.round(y);
        if (posX+width > xStart && posX < xEnd){
            if (posY+height > yStart && posY < yEnd){
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.fillRect(xStart, yStart, xEnd-xStart, yEnd-yStart);
    }
}
