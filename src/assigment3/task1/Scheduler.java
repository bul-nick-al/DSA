package assigment3.task1;
import java.util.ArrayList;

/**
 * Created by nicholas on 21/03/2017.
 */
public class Scheduler {
    private final int SHUT_DOWN_TIME;
    private int currentTime;
    private int endTime;
    private Process current;
    private BinaryHeap<Integer, Process> heap = new BinaryHeap<>();

    public Scheduler (int shutDownTime){
        currentTime = 0;
        endTime = 0;
        SHUT_DOWN_TIME = shutDownTime;
    }

    public Process getCurrent() {
        return current;
    }

    /**
     * simulation of the scheduler's work
     * @param processes
     */
    public void run(ArrayList<Process> processes){
        int i = 0;
        //scheduler stops when there is no new process coming both from heap and list of processes
        //or computer is shut down
        while ((endTime <= SHUT_DOWN_TIME)&&(processes.size() != i || !heap.isEmpty())){
            //arrival of a new process to the heap, current time sets to the time of arrival
            if (processes.size() != i && processes.get(i).getArrivalTime() <= endTime||heap.isEmpty()){
                heap.insert(processes.get(i).getPriority(), processes.get(i));
                currentTime = processes.get(i).getArrivalTime();
                i++;
            }
            //checks whether the coming process will be finished before the computer turns off
            //if it will not, exits cycle
            if (heap.max().getValue().getDurationTime()+currentTime > SHUT_DOWN_TIME)
                break;
            //if the previous process has stopped, the coming process from the heap starts
            if (currentTime >= endTime){
                current = heap.removeMax().getValue();
                endTime = currentTime + current.getDurationTime();
            }
            //achieving this field implies that the coming process is waiting for the current one to be finished
            //so, the current time changes to the end time
            else
                currentTime = endTime;
        }
    }
}

