import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
APS book
Input:
1
5043 courses.finki.ukim.mk
4
courses.finki.ukim.mk
finki.ukim.mk
ukim.mk
mk

Output:
5043
5043
5043
5043
------------------------
Input:
4
900 google.mail.com
50 yahoo.com
1 intel.mail.com
5 wiki.org
5
mail.com
com
org
yahoo.com
test

Output:
901
951
5
50
Not found
*/

public class OBHTWebDomains2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader((System.in)));

        int n = Integer.parseInt(input.readLine());
        OBHT<String, Integer> hashTable = new OBHT<>(n * 4 + 1);
        for (int i = 0; i < n; i++) {
            String[] line = input.readLine().split(" ");
            int numVisits = Integer.parseInt(line[0]);
            String domain = line[1];
            String[] subDomains = domain.split("\\.");

            for (int j = 0; j < subDomains.length; j++) {
                StringBuilder sb = new StringBuilder();
                for (int k = j; k < subDomains.length; k++) {
                    if (k > j)
                        sb.append(".");
                    sb.append(subDomains[k]);
                }
                String subDomain = sb.toString();
                int index = hashTable.search(subDomain);
                if (index != -1) {
                    int old = hashTable.getBucket(index).value;
                    hashTable.insert(subDomain, old + numVisits);
                } else
                    hashTable.insert(subDomain, numVisits);
            }
        }
        int m = Integer.parseInt(input.readLine());
        for (int i = 0; i < m; i++) {
            String searchDomain = input.readLine();
            int index = hashTable.search(searchDomain);

            if (index != -1)
                System.out.println(hashTable.getBucket(index).value);
            else
                System.out.println("Not found");
        }
    }
}