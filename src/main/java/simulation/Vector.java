package simulation;

public class Vector {
    private double x, y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "x="+x+", y="+y;
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public void setVector(Vector vector){
        this.x = vector.getX();
        this.y = vector.getY();
    }
    public void addVector(Vector vector){
        this.x += vector.x;
        this.y += vector.y;
    }
    public void multiply(Vector vector){
        this.x *= vector.x;
        this.y *= vector.y;
    }
    public void multiply(double num){
        this.x *= num;
        this.y *= num;
    }
    public double length(Vector vector){
        return Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2));
    }
}
