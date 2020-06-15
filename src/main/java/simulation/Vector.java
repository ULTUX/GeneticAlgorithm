package simulation;

/**
 * Class describing vector as an array of numbers with additional operations that can be performed on Vectors.
 */
public class Vector {
    private double x, y;

    /**
     * Get value x of this vector.
     * @return x value of the vector.
     */
    public double getX() {
        return x;
    }

    /**
     * Set value x of this vector.
     * @param x desired x value of the vector.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Get value y of this vector.
     * @return y value of the vector.
     */
    public double getY() {
        return y;
    }

    /**
     * Generate string representation of object.
     * @return string containing x and y value of the vector.
     */
    @Override
    public String toString() {
        return "x="+x+", y="+y;
    }

    /**
     * Create new object from given x and y values.
     * @param x x value of the vector.
     * @param y y value of the vector.
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Clone a vector.
     * @param vector vector to be cloned.
     */
    public Vector(Vector vector) {
        this.x = vector.getX();
        this.y = vector.getY();
    }

    /**
     * Set value y of this vector.
     * @param y desired y value of the vector.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Set vector values to be equal to other vector.
     * @param vector a vector to be used.
     */
    public void setVector(Vector vector){
        this.x = vector.getX();
        this.y = vector.getY();
    }

    /**
     * Add values of different vector to this vector.
     * @param vector a vector to be added.
     */
    public void addVector(Vector vector){
        this.x += vector.x;
        this.y += vector.y;
    }

    /**
     * Multiply this vectors values by different vector's values.
     * @param vector a vector to be multiplied by.
     */
    public void multiply(Vector vector){
        this.x *= vector.x;
        this.y *= vector.y;
    }

    /**
     * Multiply a vector by a scalar.
     * @param num a scalar to be used in multiplication.
     */
    public void multiply(double num){
        this.x *= num;
        this.y *= num;
    }

    /**
     * Get a length of the vector.
     * @param vector vector to be used in length calculations.
     * @return length of given vector.
     */
    public double length(Vector vector){
        return Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2));
    }
}
