import java.util.Scanner;

/*
Courses + Lab home
Input:
7
9 7 3 7 9 3 1

Output:
9977331
*/

public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = input.nextInt();
        input.close();

        bubbleSort(array);
        for (int i = 0; i < n; i++)
            System.out.print(array[i]);
    }
}
