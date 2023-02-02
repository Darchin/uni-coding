import java.util.ArrayList;

public class Pair {
    int divisor_count;
    int number;

    // Constructor
    public Pair(int number, int divisor_count){
        this.number = number;
        this.divisor_count = divisor_count;
    }

    // Methods
    public static ArrayList<Pair> max(ArrayList<Pair> pair_list){
        int highest_divisor = 0;
        ArrayList<Pair> list = new ArrayList<>();
        for (Pair pair : pair_list){
            if (pair.divisor_count > highest_divisor){
                highest_divisor = pair.divisor_count;
                list = new ArrayList<>();
                list.add(pair);
            } else if (pair.divisor_count == highest_divisor) {
                list.add(pair);
            }
        }
        return list;
    }
}
