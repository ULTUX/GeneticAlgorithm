package sample;


public abstract class Movable {
    double posX , posY;
    Vector velocity;

    public double getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    void move(){
        posX += velocity.getX();
        posY += velocity.getY();
    }

    public void addVelocity(Vector velocity){
        this.velocity.addVector(velocity);
    }
    public void multiply(int num){
        velocity.multiply(num);
    }
    public void multiply(Vector vector){
        velocity.multiply(vector);
    }
}
