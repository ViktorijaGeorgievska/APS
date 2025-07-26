import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
Input:
4
Skopje,Macedonia 09:00 10:30 24
Ohrid,Macedonia 12:00 13:00 25
Skopje,Macedonia 10:00 11:00 26.2
Ohrid,Macedonia 10:00 12:00 23.1
Ohrid,Macedonia
Output:
Ohrid,Macedonia: 12:00 - 13:00 25.0
*/
// APS book
public class Temperatures {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        CBHT<String, String> tempMap = new CBHT<>(2 * n - 1);

        for (int i = 0; i < n; i++) {
            String []lines = input.readLine().split(" ");
            String place = lines[0];
            String beginInterval = lines[1];
            String endInterval = lines[2];
            float temp = Float.parseFloat(lines[3]);

            String putInTable = beginInterval + " " + endInterval + " " + temp;
            SLLNode<MapEntry<String, String>> node = tempMap.search(place);

            if (node == null) {
                tempMap.insert(place, putInTable);
            }
            else {
                Float fromTable = Float.valueOf(node.element.value.split(" ")[2]);
                if (temp > fromTable) {
                    tempMap.insert(place, putInTable);
                }
            }
        }
        String searchPlace = input.readLine();
        SLLNode<MapEntry<String, String>> searchNode = tempMap.search(searchPlace);
        if (searchNode != null) {
            String []values = searchNode.element.value.split(" ");
            System.out.println(searchNode.element.key + ": " + values[0] + " - " + values[1] + " " + values[2]);
        }
        else {
            System.out.println("No such place!");
        }
    }
}
