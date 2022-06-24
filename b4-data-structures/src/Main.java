import java.util.*;
import java.util.stream.Stream;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // write your code here
        System.out.println("Enter first array (Ex: 1,2,3,4): ");
        int[] arr1 = getArrayInput();
        System.out.println("Enter second array (Ex: 1,2,3,4): ");
        int[] arr2 = getArrayInput();


        Set<Integer> ts = new TreeSet<>();
        insertArrayInto(ts, arr1);
        insertArrayInto(ts, arr2);

        System.out.println("TreeSet: " + ts);

    }

    private static void insertArrayInto(Set<Integer> ts, int[] arr) {
        for(int x: arr){
            ts.add(x);
        }
    }

    private static int[] getArrayInput() {
        String[] strArr = sc.nextLine().split(",");
        return Stream.of(strArr).mapToInt(Integer::parseInt).toArray();
    }
}
