import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
APS book
Input:
4
Ohrid,Macedonia 10:00 12:00 23.1
Skopje,Macedonia 09:00 10:30 24
Ohrid,Macedonia 12:00 13:00 25
Skopje,Macedonia 10:00 11:00 26.2
Ohrid,Macedonia

Output:
Ohrid,Macedonia: 12:00 - 13:00 25.0
*/

public class CBHTTemperatures2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());

        CBHT<String, String> temperatures = new CBHT<>(n * 2 + 1);
        for (int i = 0; i < n; i++) {
            String[] line = input.readLine().split(" ");
            String place = line[0];
            String otherData = line[1] + " " + line[2] + " " + line[3];

            SLLNode<MapEntry<String, String>> searchNode = temperatures.search(place);
            if (searchNode == null)
                temperatures.insert(place, otherData);
            else {
                float temp = Float.parseFloat(searchNode.element.value.split(" ")[2]);
                float newTemp = Float.parseFloat(line[3]);
                if (newTemp > temp)
                    temperatures.insert(place, otherData);
            }
        }
        String place = input.readLine();
        SLLNode<MapEntry<String, String>> searchPlace = temperatures.search(place);
        if (searchPlace != null) {
            String[] data = searchPlace.element.value.split(" ");
            System.out.println(place + ": " + data[0] + " - " + data[1] + " " + Float.parseFloat(data[2]));
        } else
            System.out.println("No such place!");
    }
}
