package assigment4.task2;

/**
 * class representing a road
 * Created by nicholas on 18/04/2017.
 */
public class Road {
    //properties of going by this road: distance, time, price
    double distance;
    double time;
    double cost;

    public Road(String[] data){
        distance = Double.parseDouble(data[0]);
        time = Double.parseDouble(data[1]);
        cost = Double.parseDouble(data[2]);
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
