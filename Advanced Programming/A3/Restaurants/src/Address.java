public class Address {

    // Fields
    double latitude;
    double longitude;
    String written_address;


    // Constructors
    public Address() {}
    public Address(double latitude, double longitude, String written_address){
        this.written_address = written_address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Methods
    public double distance_from(Address address){
        return Math.sqrt(
               Math.pow((this.latitude-address.latitude),2)
              +Math.pow((this.longitude-address.longitude),2));
    }

    // Copy Method
    public static void copy_address(Address a1, Address a2){
        a1.latitude = a2.latitude;
        a1.longitude = a2.longitude;
        a1.written_address = a2.written_address;
    }
}
