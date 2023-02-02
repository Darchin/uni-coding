import java.util.ArrayList;
import java.util.Scanner;


class contact {
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    String birthdate;
    int id;

    public contact(String fn, String ln, String pn, String em, String bd, int ID) {
        firstName = fn;
        lastName = ln;
        phoneNumber = pn;
        email = em;
        birthdate = bd;
        id = ID;
    }

    public static void list(ArrayList<contact> contactList){
        for (contact contact : contactList) {
            print(contact);
        }
    }

    public static void add(ArrayList<contact> contactList){
        {
            String firstName;
            String lastName;
            String phoneNumber;
            String email;
            String birthdate;
            int id = contactList.size() + 1;
            System.out.print("    first name: ");
            firstName = Main.input.nextLine();
            System.out.print("    last name: ");
            lastName = Main.input.nextLine();
            System.out.print("    phone number: ");
            phoneNumber = Main.input.nextLine();
            System.out.print("    email: ");
            email = Main.input.nextLine();
            System.out.print("    birthdate: ");
            birthdate = Main.input.nextLine();
            System.out.println("####");
            System.out.println("id: "+id);
            contactList.add(new contact(firstName, lastName, phoneNumber, email, birthdate, id));
        }
    }

    public static void print(contact contact){
        System.out.println("####");
        System.out.println("id: "+contact.id);
        System.out.println("first name: "+contact.firstName);
        System.out.println("last name: "+contact.lastName);
        System.out.println("phone number: "+contact.phoneNumber);
        System.out.println("email: "+contact.email);
        System.out.println("birthdate: "+contact.birthdate);
    }


    public static void searchE(ArrayList<contact> contactList){
        boolean found = false;
        String email;
        System.out.print("email: ");
        email = Main.input.nextLine();
        for (contact contact : contactList) {
            if (email.equalsIgnoreCase(contact.email)) {
                print(contact);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("####");
            System.out.println("no contacts found");
        }
    }

    public static void searchPN(ArrayList<contact> contactList){
        String phoneNumber;
        boolean found = false;
        System.out.print("phone number: ");
        phoneNumber = Main.input.nextLine();
        for (contact contact : contactList) {
            if (phoneNumber.equalsIgnoreCase(contact.phoneNumber)) {
                print(contact);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("####");
            System.out.println("no contacts found");
        }
    }
}
public class Main {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        ArrayList<contact> contactList = new ArrayList<>();
        String command = new String();
        while (!command.equals("exit")){
            System.out.print("enter your command: ");
            command = input.nextLine();

            if (command.equals("add")){
                contact.add(contactList);
            }

            if (command.equals("list")){
//                System.out.println("####");
                contact.list(contactList);
            }

            if (command.equals("search-by-phone")){
                contact.searchPN(contactList);
            }

            if (command.equals("search-by-email")){
                contact.searchE(contactList);
            }

            if (command.equals("exit")){
                System.exit(0);
            }
        }
    }
}