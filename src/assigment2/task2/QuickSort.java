package assigment2.task2;

/**
 * Created by nicholas on 21/02/2017.
 */
public class QuickSort<E>  {
    private ArrayList<E> elements;
    private MyComparator<E> comparator;

    public ArrayList<E> sort(ArrayList<E>  elements, MyComparator comparator) {
        // checking if array is empty or null
        if (elements.equals(null) || elements.size() == 0){
            return elements;
        }
        this.elements = elements;
        this.comparator = comparator;
        quickSort(0, elements.size() - 1);
        return elements;
    }

    /**
     * Quick sort algorithm implementation
     * @param low bound
     * @param high bound
     */
    private void quickSort(int low, int high) {
        int newLow = low, newHigh = high;
        // get the middle pivot
        E pivot = elements.get(low + (high-low)/2);
        //
        while (newLow <= newHigh) {
            //Iterates i as long as the number at ith position is smaller than the pivot
            while (comparator.compare(elements.get(newLow), pivot) < 0) {
                newLow++;
            }
            //Iterates j as long as the number at ith position is smaller than the pivot
            while (comparator.compare(elements.get(newHigh), pivot) > 0) {
                newHigh--;
            }
            //achieving this line of code with i<=j implies that elements at ith and jth position should be swapped
            if (newLow <= newHigh) {
                swap(newLow, newHigh);
                newLow++;
                newHigh--;
            }
        }
        // Recursion for a list divided into two sublists
        if (newLow < high)
            quickSort(newLow, high);
        if (low < newHigh)
            quickSort(low, newHigh);
    }

    /**
     * swaps two integers in Array List
     * @param i position of the number from the left
     * @param j position of the number from the right
     */
    private void swap(int i, int j) {
        E temp = elements.get(i);
        elements.set(i, elements.get(j));
        elements.set(j, temp);
    }
}
