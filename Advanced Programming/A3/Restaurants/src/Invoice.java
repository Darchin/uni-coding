import java.time.LocalDateTime;
import java.util.ArrayList;

public class Invoice {
    // Fields
    private int state;
    Customer customer;
    private final LocalDateTime local_date_time;
    private final float tax_rate = 9.4f;
    ArrayList<Item> order = new ArrayList<>();

    // Constructors
    public Invoice(Customer customer){
        this.customer = customer;
        this.state = -1;
        local_date_time = LocalDateTime.now();
    }

    // Getter Methods
    public LocalDateTime getSubmitTime(){
        return this.local_date_time;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    // Getter Methods
    public int getTotalPrice(){
        double total = 0;
        for (Item item : order){
            total += item.getCount() * item.getFood().getPrice();
        }
        total *= 1+tax_rate/100;
        return (int)Math.ceil(total);
    }
    // Methods
    public boolean addItem(Item item){
        if (this.state == -1) {
            this.order.add(item);
            return true;
        }
        else {return false;}
    }

    public boolean removeItem(Item item){
        if (this.state == -1){
            for (Item i : order){
                if (i.getFood() == item.getFood()) {
                    order.remove(item);
                    return true;
                }
            }
        }
        return false;
    }

    public void nextStage(){
        if (this.state == -1) {this.state = 1;}
        else if (this.state == 1) {this.state = 2;}
    }
}
