public class ListTest
{
    public static void main(String[] args)
    {
        SortedIntList myList = new SortedIntList(10);
        myList.add(100);
        myList.add(50);
        myList.add(200);
        myList.add(25);
        myList.add(-2);
        myList.add(37);
        myList.add(999);
        System.out.println(myList.toString());
    }
}