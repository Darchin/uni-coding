import java.util.ArrayList;

public class Food {
    // Fields
    private final String name;
    private final int price;
    ArrayList<Food> menu = new ArrayList<>();

    // Constructors
    public Food(String name, int price){
        this.name = name;
        this.price = price;
        menu.add(this);
    }

    // Getter Methods
    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }
}
