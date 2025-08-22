import java.util.Scanner;

/*
APS book basics
Input:
5
Alek A1+
Dejan B-
Sandra A+
Trajce 0+
Rebeka A1-

Output:
A+ = 2
B- = 1
0+ = 1
A- = 1
*/

public class OBHTRedCross1 {
    public static void main(String[] args) {
        OBHT<String, Integer> hashTable = new OBHT<>(11);

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 1; i < n; i++) {
            String name = input.next();
            String key = input.next().replaceAll("[1-2]", "");

            int bucket = hashTable.search(key);
            if (bucket == -1)
                hashTable.insert(key, 1);
            else
                hashTable.insert(key, hashTable.getMap(bucket).value + 1);
        }
        System.out.println(hashTable);
    }
}
