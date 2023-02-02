import java.util.ArrayList;

public class SortedIntList extends IntList {

    // Fields

    // Constructors

    public SortedIntList(int size)
    {
        super(size);
    }

    // Methods
    //[3,2,4] -> temp = 2
    public void sort(int[] list){
        int temp;
        int j;
        for (int i = 1; i < numElements; i++){
            temp = list[i];
            j = i - 1;
            while (j >= 0 && list[j] > temp){
                list[j+1] = list[j];
                j--;
            }
            list[j+1] = temp;
        }
    }
    @Override
    public void add(int value) {
        super.add(value);
        if (numElements >= 2){
            sort(list);
        }
    }
}
