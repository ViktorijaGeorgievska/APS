import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
APS book basics
Input:
4
20.7.1976
16.7.1988
18.7.1966
5.6.1988
7        -> key
Output: 3
*/
// key -> month, value -> count (брои колку родендени има во секој месец)
public class CBHTBirthdays1 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());

        CBHT<Integer, Integer> hashTable = new CBHT<>(n * 2 - 1);
        for (int i = 0; i < n; i++) {
            String[] line = input.readLine().split("\\.");
            int month = Integer.parseInt(line[1]);

            SLLNode<MapEntry<Integer, Integer>> search = hashTable.search(month);
            if (search != null)
                hashTable.insert(month, search.element.value + 1);
            else
                hashTable.insert(month, 1);
        }
        int searchMonth = Integer.parseInt(input.readLine());
        SLLNode<MapEntry<Integer, Integer>> searchNode = hashTable.search(searchMonth);
        if (searchNode != null)
            System.out.println(searchNode.element.value);
        else
            System.out.println("Empty");
    }
}

