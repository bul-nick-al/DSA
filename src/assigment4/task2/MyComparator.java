package assigment4.task2;

import java.util.Comparator;

/**
 * represents comparator for roads
 * Created by nicholas on 24/02/2017.
 */
public class MyComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Edge<Road> edge1 = (Edge<Road>)(o1);
        Edge<Road> edge2 = (Edge<Road>)(o2);
        if (edge1.getWeight().getTime() > edge2.getWeight().getTime())
            return 1;
        if (edge1.getWeight().getTime() < edge2.getWeight().getTime())
            return -1;
        return 0;
    }
}
