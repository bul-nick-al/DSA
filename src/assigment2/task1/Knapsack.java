package assigment2.task1;
/**
 * Created by nicholas on 21/02/2017.
 */
public class Knapsack {
    /**
     * knapsack problem algorithm DP implementation
     * @param items array with items with fields value and weight
     * @param maxWeight the greatest weight allowed
     * @return the greatest value possible for the given weight
     */
    static int knapsack(Item[] items, int maxWeight)
    {
        //table for saving intermediate values
        int knapsack[][] = new int[items.length+1][maxWeight+1];
        for (int i = 0; i <= items.length; i++)
        {
            for (int weight = 0; weight <= maxWeight; weight++)
            {
                if (weight == 0 || i == 0)
                    knapsack[i][weight] = 0;
                //saving the maximum of total value with or without ith element
                else if ( items[i-1].getWeight() <= weight)
                    knapsack[i][weight] = Math.max(knapsack[i-1][weight], items[i-1].getValue() + knapsack[i-1][weight- items[i-1].getWeight()]);
                //for the case if weight of the current element is greater than the current max weight
                else
                    knapsack[i][weight] = knapsack[i-1][weight];
            }
        }
        return knapsack[items.length][maxWeight];
    }
}


