package sample;


public abstract class Movable {
    Vector position = new Vector(0, 0 );
    Vector velocity;

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    void move(){
        position.addVector(velocity);
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
