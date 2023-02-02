public class Main {
    public static void main(String[] args) {
        double input = 494;
        int x = (int)Math.round(input/4);
        if (input % 4 == 0) {
            System.out.println("(" + (-x) + "," + x + ")");
            System.exit(0);
        }

        if (input % 4 == 1) {
            System.out.println("(" + (-x) + "," + (-x) + ")");
            System.exit(0);
        }

        if (input % 4 == 2) {
            System.out.println("(" + x + "," + (-x+1) + ")");
            System.exit(0);
        }

        if (input % 4 == 3) {
            System.out.println("(" + x + "," + x + ")");
            System.exit(0);
        }

    }
}