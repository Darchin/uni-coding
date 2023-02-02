public class Customer {
    // Fields
    Address address;
    String customer_name;
    private final int customer_number;
    static int CUSTOMER_COUNT = 1;

    // Constructors
    public Customer(String customer_name, Address address){
        this.customer_name = customer_name;
        this.address = new Address(address.latitude, address.longitude, address.written_address);
        this.customer_number = CUSTOMER_COUNT;
        CUSTOMER_COUNT++;
    }

    // Copy Method
//    public static void copy_customer(Customer c1, Customer c2){
//        c1.setAddress(c2.getAddress());
//        c1.setName(c2.getName());
//    }

    // Setter Methods
    public void setName(String new_name){
        this.customer_name = new_name;
    }

    public void setAddress(Address new_address){
        this.address.latitude = new_address.latitude;
        this.address.longitude = new_address.longitude;
        this.address.written_address = new_address.written_address;
    }

    // Getter Methods
    public int getCustomerNumber(){
        return this.customer_number;
    }

    public String getName(){
        return this.customer_name;
    }

    public Address getAddress(){
        return this.address;
    }
}
