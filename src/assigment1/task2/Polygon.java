package assigment1.task2;

/**
 * Created by nicholas on 01/02/2017.
 */
public class Polygon {
    //all points of a polygon
    private ArrayList<Point> points = new ArrayList<>();
    //maximal and minimal values of x and y
    private double xMax;
    private double xMin;
    private double yMax;
    private double yMin;

    public double getXMax() {
        return xMax;
    }

    public void setXMax(double xMax) {
        this.xMax = xMax;
    }

    public double getXMin() {
        return xMin;
    }

    public void setXMin(double xMin) {
        this.xMin = xMin;
    }

    public double getYMax() {
        return yMax;
    }

    public void setYMax(double yMax) {
        this.yMax = yMax;
    }

    public double getYMin() {
        return yMin;
    }

    public void setYMin(double yMin) {
        this.yMin = yMin;
    }

    public Polygon(ArrayList<Point> points){
        this.points = points;
        setMinsMaxs();
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    /**
     * finds and sets maximal and minimal values of x and y
     */
    private void setMinsMaxs(){
        xMax = points.get(0).getX();
        xMin = points.get(0).getX();
        yMax = points.get(0).getY();
        yMin = points.get(0).getY();
        for (int i = 1; i < points.size(); i++){
            xMax = Math.max(xMax, points.get(i).getX());
            xMin = Math.min(xMin, points.get(i).getX());
            yMax = Math.max(yMax, points.get(i).getY());
            yMin = Math.min(yMin, points.get(i).getY());
        }
    }

}
