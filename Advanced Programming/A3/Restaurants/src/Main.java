public class Main {
    public static void main(String[] args) {
        Address address = new Address(0, 0, "Greenwich");
        Customer firstCustomer = new Customer("some guy", address);
        System.out.printf("customer number: %s\n",firstCustomer.getCustomerNumber());
//add Foods
        Food f1 = new Food("pizza", 80000);
        Food f2 = new Food("coca cola", 6000);
        Food f3 = new Food("Cinnamon Roll", 18000);
        Food f4 = new Food("tea", 10000);
        Food f5 = new Food("pasta", 55000);
        Invoice firstInvoice = new Invoice(firstCustomer);
        firstInvoice.addItem(new Item(f1, 1, "with extra pepper"));
//we can also define a function with 2 args for this situations
        firstInvoice.addItem(new Item(f5, 1, "More cheese"));
        firstInvoice.addItem(new Item(f2, 3));
        System.out.printf("invoice submit time: %s\n",firstInvoice.getSubmitTime().toString());
        System.out.printf("invoice total price: %s\n",firstInvoice.getTotalPrice());
    }
}