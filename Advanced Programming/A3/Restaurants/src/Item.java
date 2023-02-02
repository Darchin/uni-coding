public class Item {
    private final Food food;
    private final int count;
    private final String description;


    // Constructors
    public Item(Food food, int count, String description){
        this.food = food; //new Food(food.getName(), food.getPrice());
        this.count = count;
        this.description = description;
    }

    public Item(Food food, int count){
        this.food = food;
        this.count = count;
        this.description = "";
    }

    // Getter Methods
    public Food getFood(){
        return this.food;
    }

    public int getCount(){
        return this.count;
    }

    public String getDescription(){
        return this.description;
    }
}
