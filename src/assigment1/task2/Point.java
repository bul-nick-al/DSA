package assigment1.task2;

import java.util.Random;

/**
 * Created by nicholas on 01/02/2017.
 */
public class Point {
    //coordinates
    double x;
    double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * @param xMax
     * @param xMin
     * @param yMax
     * @param yMin
     * @return a random point within the given bounds
     */
    public static Point getRandomPoint(double xMax, double xMin, double yMax, double yMin) {
        double random = new Random().nextDouble();
        double x = xMin + (random * (xMax - xMin));
        random = new Random().nextDouble();
        double y = yMin + (random * (yMax - yMin));
        return new Point(x, y);
    }
}
