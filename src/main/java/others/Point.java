package others;

public class Point {
    private double x;
    private double y;
    Point(){
        this.x = 0;
        this.y = 0;
    }
    Point(double x) {
        this.x = x;
        this.y = 0;
    }
    Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }
    void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    void setY(double y) {
        this.y = y;
    }
    void rotateAroundZ(double phi)
    {
        double sinPhi = Math.sin(phi);
        double cosPhi = Math.cos(phi);
        double x, y;
        x = cosPhi * this.x - sinPhi * this.y;
        y = sinPhi * this.x + cosPhi * this.y;
        this.x = x;
        this.y = y;
    }
}
