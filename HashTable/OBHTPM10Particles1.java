import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
APS book basics
Input:
8
Centar 319.61
Karposh 296.74
Centar 531.98
Karposh 316.44
GaziBaba 384.05
GaziBaba 319.3
Karposh 393.37
GaziBaba 326.42
Karposh

Output:
355.52
*/

public class OBHTPM10Particles1 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());
        OBHT<String, ArrayList<Double>> hashtable = new OBHT<>(2 * n);

        for (int i = 0; i < n; i++) {
            String[] lines = input.readLine().split(" ");
            String neighbourhood = lines[0];
            String pm10 = lines[1];
            ArrayList<Double> list = new ArrayList<>();

            if (hashtable.search(neighbourhood) == -1) {
                list.add(Double.parseDouble(pm10));
                hashtable.insert(neighbourhood, list);
            } else {
                list = hashtable.getBucket(hashtable.search(neighbourhood)).value;
                list.add(Double.parseDouble(pm10));
                hashtable.insert(neighbourhood, list);
            }
        }
        String checkNeighbourhood = input.readLine();
        int index = hashtable.search(checkNeighbourhood);

        if (index != -1) {
            ArrayList<Double> result = hashtable.getBucket(index).value;
            double sum = 0;
            for (int i = 0; i < result.size(); i++)
                sum += result.get(i);
            System.out.printf("%.2f", sum / result.size());
        } else
            System.out.println("No info");
    }
}
