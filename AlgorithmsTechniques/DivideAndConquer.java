import java.util.Scanner;

/*
Courses + Lab home
Input:
7 7
1 2 3 4 5 6 7

Output:
6
*/

public class DivideAndConquer {
    public static int binarySearch (int[] array, int target) {
        int start = 0, end = array.length - 1;

        while (start <= end) {
            int middle = (start + end ) / 2;

            if (array[middle] == target)
                return middle;
            else if (target > array[middle])
                start = middle + 1;
            else if (target < array[middle])
                end = middle - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int searchNum = input.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = input.nextInt();
        input.close();

        int position = binarySearch(array, searchNum);
        if (position != -1)
            System.out.println(position);
        else
            System.out.println("Ne postoi");
    }
}
