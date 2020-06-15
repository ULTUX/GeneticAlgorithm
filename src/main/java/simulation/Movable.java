package simulation;

/**
 * Gives ability to move.
 */
public abstract class Movable {
    Vector position = new Vector(0, 0 );
    Vector velocity;

    /**
     * Get the velocity of an object.
     * @return velocity
     */
    public Vector getVelocity() {
        return velocity;
    }

    /**
     * Set velocity of the object.
     * @param velocity Vector containing velocity values.
     */
    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    /**
     * Move the object by changing position by velocity.
     */
    void move(){
        position.addVector(velocity);
    }

    /**
     * Add a new velocity.
     * @param velocity velocity to be added.
     */
    public void addVelocity(Vector velocity){
        this.velocity.addVector(velocity);
    }

    /**
     * Multiply velocity by scalar.
     * @param num a scalar to be multiplied by.
     */
    public void multiply(int num){
        velocity.multiply(num);
    }

    /**
     * Multiply velocity by a vector.
     * @param vector a vector to be multiplied by.
     */
    public void multiply(Vector vector){
        velocity.multiply(vector);
    }
}
