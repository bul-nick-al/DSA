package assigment3.task1;

/**
 * Class representing a process
 */
public class Process {
    private String name;
    private int durationTime;
    private int arrivalTime;
    private int priority;

    public Process(String name, int arrivalTime, int durationTime, int priority) {
        this.name = name;
        this.durationTime = durationTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }

    public int getDurationTime() {
        return durationTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getPriority() {
        return priority;
    }
}
