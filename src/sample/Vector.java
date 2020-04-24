package sample;

public class Vector {
    private double x, y;

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setY(int y) {
        this.y = y;
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
}
