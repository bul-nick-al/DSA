package lab7;

/**
 * Created by nicholas on 05/03/2017.
 */
public class complexity {

    public static void main(String[] args) {
        int a[] = {2, 3, 5, 8,6,7,4,6,3,5,23};
        System.out.println(g3(a.length, a));
    }
    static int g3(int n, int a[]) {
        int s=0;
        int s2 = a[0];
        for (int i = 1; i < a.length ; i++) {
            s = s + a[i]*s2;
            s2= s2 + a[i];
        }
        return s;
    }

}
