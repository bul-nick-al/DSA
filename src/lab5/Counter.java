package lab5;

/**
 * Created by nicholas on 19/02/2017.
 */
public class Counter {
    private int N;   // number of bits
    private int[] a; // bits (0 or 1)

    public Counter(int N) {
        this.N = N;
        a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = 0;
        enumerate(0);
    }

    private void enumerate(int k)
    {
        if (k == N)
        {  process(); return;  }
        enumerate(k+1);
        a[k] = 1;
        enumerate(k+1);
        a[k] = 0;
    }

    private void process() {
        for (int i = 0; i < N; i++)
            System.out.print(a[i]);
        System.out.println();
        }
}
