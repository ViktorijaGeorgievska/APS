import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
APS book
Input:
6
Aleksandra,Marko
Beti,Marko
Marko,Filip
Darko,Elena
Elena,Filip
Filip,Filip

Output:
Elena: 1
Filip: 3
Marko: 2
*/

public class OBHTCompany2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader((System.in)));

        int n = Integer.parseInt(input.readLine());
        OBHT<String, Integer> hashTable = new OBHT<>(2 * n - 1);
        for (int i = 0; i < n; i++) {
            String[] line = input.readLine().split(",");
            String manager = line[1];

            int index = hashTable.search(manager);
            if (index != -1) {
                MapEntry<String, Integer> result = hashTable.getBucket(index);
                hashTable.insert(manager, result.value + 1);
            } else
                hashTable.insert(manager, 1);
        }
        ArrayList<String> managerList = new ArrayList<>();
        for (int i = 0; i < n * 2 - 1; i++) {
            if (hashTable.getBucket(i) != null)
                managerList.add(hashTable.getBucket(i).key + ": " + hashTable.getBucket(i).value);
        }
        Collections.sort(managerList);
        for (String manager : managerList)
            System.out.println(manager);
    }
}