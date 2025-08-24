import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
APS book
Input:
12
1 SoedinetiAmerikanskiDrzavi
20 Egipet
21 Maroko
26 Zambija
351 Portugalija
355 Albanija
359 Bugarija
372 Estonija
381 Srbija
385 Hrvatska
387 BosnaiHercegovina
389 Makedonija
+2611332345678

Output: Zambija
*/

public class OBHTCheckCallingNumber2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader((System.in)));

        int n = Integer.parseInt(input.readLine());
        OBHT<Integer, String> hashTable = new OBHT<>(2 * n);
        for (int i = 0; i < n; i++) {
            String[] lines = input.readLine().split(" ");
            int number = Integer.parseInt(lines[0]);
            String country = lines[1];
            hashTable.insert(number, country);
        }
        String phoneNumber = input.readLine().substring(1);          // отстанува +
        int checkNumber = 0;
        if (phoneNumber.startsWith("1"))
            checkNumber = Integer.parseInt(phoneNumber.substring(0, 1));
        else if (phoneNumber.startsWith("2"))
            checkNumber = Integer.parseInt(phoneNumber.substring(0, 2));
        else if (phoneNumber.startsWith("3"))
            checkNumber = Integer.parseInt(phoneNumber.substring(0, 3));

        if (hashTable.search(checkNumber) != -1) {
            MapEntry<Integer, String> result = hashTable.getBucket(hashTable.search(checkNumber));
            System.out.println(result.value);
        }
    }
}

