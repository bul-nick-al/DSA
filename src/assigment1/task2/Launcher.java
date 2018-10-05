package assigment1.task2;

import java.io.*;
import java.util.Scanner;

/**
 * Created by nicholas on 01/02/2017.
 */
public class Launcher {
    public static void main (String args[]){
        ArrayList<Double> coordinates = readDoubles();
        Polygon polygon = new Polygon(turnToPoints(coordinates));
        writeString(String.format("%.2f", approximateArea(polygon)));
    }

    /**
     * calculates an approximate area of the polygon using Monte Carlo method of integration
     * @param polygon - polygon which area is needed to be calculated
     * @return approximate value of area
     */
    public static double approximateArea(Polygon polygon){
        //maximal and minimal x and y in polygon
        double xMax = polygon.getXMax();
        double xMin = polygon.getXMin();
        double yMax = polygon.getYMax();
        double yMin = polygon.getYMin();

        int pointsInside = 0;
        int allPoints = 0;
        double area = 0;
        double oldArea = 0;
        int i = 0;
        //calculating the area of a rectangle which circumscribes the given polygon
        double rectangleArea = (xMax - xMin)*(yMax - yMin);
        //dropping random points and estimating the area of polygon until it changes by less then 1e-4 at least 5 times
        //or number of iterations becomes more than 2000000 (this number was found experimentally by me and it gives
        // quite a good approximation)
        while ((allPoints!= 2000000)&&(i <= 5))  {
            if(isInside(polygon, Point.getRandomPoint(xMax, xMin, yMax, yMin))){
                pointsInside += 1;
            }
            allPoints += 1;
            oldArea = area;
            area = ((float)pointsInside/allPoints)*rectangleArea;
            i = (Math.abs(area - oldArea) < 0.000001 && Math.abs(area - oldArea) != 0) ? i +1 : i;
        }
        return Math.rint(((float)pointsInside/allPoints)*rectangleArea*100)/100;
    }
    /**
     * read a sequence of doubles from input file and put then in Array List
     * @return Array List with doubles
     */
    private static ArrayList<Double> readDoubles() {
        ArrayList<Double> result = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            while (sc.hasNext()){
                result.add(sc.nextDouble());
            }
        } catch (FileNotFoundException ex) {
            return null;
        }
        return result;
    }

    /**
     * write string to output file
     * @param s string to write
     */

    private static void writeString(String s) {
        try {
            try (Writer writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("output.txt"), "ascii"))) {
                writer.write(s);
            }
        } catch (IOException ex) {
        }
    }

    /**
     * takes doubles from Array List as coordinates and turns them to points
     * @param coordinates Array List with coordinates
     * @return Array List of points
     */
    public static ArrayList<Point> turnToPoints(ArrayList<Double> coordinates){
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < coordinates.size(); i+=2){
            points.add(new Point(coordinates.get(i), coordinates.get(i+1)));
        }
        return points;
    }


    /**
     * checks whether the section ab intersects with cd
     * ©
     * @param a a point of first section
     * @param b a point of first section
     * @param c a point of second section
     * @param d a point of second section
     * @return true if intersects, false if does not
     */
    public static boolean intersects(Point a, Point b, Point c, Point d) {
        // We describe the section AB as A+(B-A)*u and CD as C+(D-C)*v
        // then we solve A + (B-A)*u = C + (D-C)*v
        // let's use Kramer's rule to solve the task (Ax = B) were x = (u, v)^T
        // build a matrix for the equation
        double[][] matrix = new double[2][2];
        matrix[0][0] = b.getX() - a.getX();
        matrix[1][0] = b.getY() - a.getY();
        matrix[0][1] = c.getX() - d.getX();
        matrix[1][1] = c.getY() - d.getY();
        // calculate determinant
        double det0 = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
        // substitute columns and calculate determinants
        double detU = (c.getX() - a.getX()) * matrix[1][1] - (c.getY() - a.getY()) * matrix[0][1];
        double detV = matrix[0][0] * (c.getY() - a.getY()) - matrix[1][0] * (c.getX() - a.getX());
        // calculate the solution
        // even if det0 == 0 (they are parallel) this will return NaN and comparison will fail -> false double u = detU / det0;
        double u = detU / det0;
        double v = detV / det0;
        return u > 0 && u < 1 && v > 0 && v < 1;
    }

    /**
     * checks whether the point is inside the polygon
     * @param polygon
     * @param point
     * @return true if inside, false if not
     */
    public static boolean isInside(Polygon polygon, Point point){
        ArrayList<Point> points = polygon.getPoints();
        int numberOfIntersections;
        //maximal and minimal x and y in polygon
        double xMax = polygon.getXMax();
        double xMin = polygon.getXMin();
        double yMax = polygon.getYMax();
        double yMin = polygon.getYMin();
        //tests count how many times a ray intersects the polygon
        int test1 = 0;
        int test2 = 0;
        int test3 = 0;
        //iteration through the list does not check the section between the first and the last points, so it is checked here:
        test1 = intersects(points.get(points.size()-1), points.get(0), point, new Point(xMax+1, yMax+1)) ? test1 + 1 : test1;
        test2 = intersects(points.get(points.size()-1), points.get(0), point, new Point(xMax+1, yMin-1)) ? test2 + 1 : test2;
        test3 = intersects(points.get(points.size()-1), points.get(0), point, new Point(xMin-1, yMax+1)) ? test3 + 1 : test3;
        //iterating through all sides of the polygon to count the number of intersection
        for (int i = 0; i < points.size() - 1; i++){
            test1 = intersects(points.get(i), points.get(i+1), point, new Point(xMax+1, yMax+1)) ? test1 + 1 : test1;
            test2 = intersects(points.get(i), points.get(i+1), point, new Point(xMax+1, yMin-1)) ? test2 + 1 : test2;
            test3 = intersects(points.get(i), points.get(i+1), point, new Point(xMin-1, yMax+1)) ? test3 + 1 : test3;
        }
        //calculating if the majority of tests is odd or even
        numberOfIntersections = (int) Math.rint((test1%2 + test2%2 + test3%2)/3);
        return numberOfIntersections == 1;
    }
}
