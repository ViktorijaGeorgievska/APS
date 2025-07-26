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

public class CheckCallingNumber2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        CBHT<Integer, String> callingNumbersTable = new CBHT<>(n * 2 - 1);
        for (int i = 0; i < n; i++) {
            String []line = in.readLine().split(" ");
            int number = Integer.parseInt(line[0]);
            String country = line[1];

            callingNumbersTable.insert(number, country);
        }
        String checkNumber = in.readLine().substring(1);
        int num = 0;
        if (checkNumber.startsWith("1")) {
            num = Integer.parseInt(checkNumber.substring(0, 1));
        } else if (checkNumber.startsWith("2")) {
            num = Integer.parseInt(checkNumber.substring(0, 2));
        } else if (checkNumber.startsWith("3")) {
            num = Integer.parseInt(checkNumber.substring(0, 3));
        }
        SLLNode<MapEntry<Integer, String>> check = callingNumbersTable.search(num);
        if (check != null) {
            System.out.println(check.element.value);
        }
    }
}
