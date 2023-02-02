import java.util.ArrayList;

public class Divisor implements Runnable {
    static ArrayList<Pair> maximums = new ArrayList<>();
    int partition = 0;

    public Divisor(int partition) {
        this.partition = partition;
    }

    void countDivisors() {
        ArrayList<Pair> list = new ArrayList<>();
        int max_num = 0;
        int max_div = 0;
        int start = 10000 * partition;
        // Main Loop
        for (int n = start + 1; n <= start + 10000; n++) {
            int num = n;
            int divisor_count = 1;
            int divisor = 1;

            while (divisor <= Math.sqrt(num)) {
                if (num / divisor == divisor) {
                    divisor_count -= 1;
                }
                if (num % divisor == 0) {
                    divisor_count += 2;
                }
                divisor++;
            }
            if (divisor_count > max_div) {
                list = new ArrayList<>();
                max_div = divisor_count;
                list.add(new Pair(num, max_div));
            } else if (divisor_count == max_div) {
                list.add(new Pair(num, max_div));
            }
        }
        synchronized (maximums) {
            maximums.addAll(list);
        }
    }

    public void run() {
        this.countDivisors();
    }
}