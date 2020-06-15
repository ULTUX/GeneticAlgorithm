package simulation;

import javafx.scene.canvas.GraphicsContext;

/**
 * Interface specifying ability to draw.
 */
public interface Drawable {

    /**
     * Function required to draw spmething on the screen.
     * @param gc JavaFx object of class class used to issue draw calls to a Canvas using a buffer.
     */
    void draw(GraphicsContext gc);
}
