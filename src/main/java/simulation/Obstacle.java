package simulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Obstacles spread on the map making it harder for species to survive.
 */
public class Obstacle implements Drawable{
    int xStart, yStart;
    int xEnd, yEnd;

    /**
     * Create new obstacle given start position and end position (obstacles are represented by rectangles).
     * @param xStart x value of obstacle start position.
     * @param yStart y value of obstacle start position.
     * @param xEnd x value of obstacle end position.
     * @param yEnd y value of obstacle end position.
     */
    public Obstacle(int xStart, int yStart, int xEnd, int yEnd) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }

    /**
     * Check if given rectangle with given position is touching obstacle.
     * @param x x value of the position.
     * @param y y value of the position.
     * @param width width of the rectangle.
     * @param height height of the obstacle.
     * @return whether this area is touching the obstacle or not.
     */
    public boolean isInObstacle(double x, double y, int width, int height){
        int posX = (int) Math.round(x);
        int posY = (int) Math.round(y);
        if (posX+width > xStart && posX < xEnd){
            return posY + height > yStart && posY < yEnd;
        }
        return false;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(xStart, yStart, xEnd-xStart, yEnd-yStart);
    }
}
