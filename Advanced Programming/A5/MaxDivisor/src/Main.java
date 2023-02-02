import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[10];
        Divisor[] parts = new Divisor[10];
        for (int i = 0; i < 10; i++) {
            parts[i] = new Divisor(i);
            threads[i] = new Thread(parts[i]);
            threads[i].start();
        }

        // Wait for threads to end before finding maximum
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        // Print number(s) with the highest number of divisors
        ArrayList<Pair> list = Pair.max(Divisor.maximums);
        System.out.println("Highest number of divisors: "+ list.get(0).divisor_count);
        System.out.println("Number(s): ");
        for (Pair pair : list){
            System.out.println(pair.number);
        }
    }
}