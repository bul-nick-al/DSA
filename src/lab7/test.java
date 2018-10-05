package lab7;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by nicholas on 02/03/2017.
 */
public class test {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.andrew.cmu.edu/course/15-121/dictionary.txt");
        Scanner sc = new Scanner( url.openStream() ); while(sc.hasNext()) {
            System.out.println(sc.next());
        }
    }
}
