import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/*
APS book
Input:
4
Ohrid 10:00 12:00 23.1
Skopje 09:00 10:30 24
Ohrid 12:00 13:00 25
Skopje 10:00 11:00 26.2
Ohrid

Output:
Ohrid:
10:00 - 12:00 23.10
12:00 - 13:00 25.00
----------------------
Input:
4
Ohrid 10:00 12:00 23.1
Skopje 09:00 10:30 24
Ohrid 12:00 13:00 25
Skopje 10:00 11:00 26.2
Strumica

Output:
Strumica:
does not exist

Input:
9
KrivaPalanka 16:00 17:30 25.0
Skopje 14:00 14:30 28.5
KrivaPalanka 18:00 20:30 22.0
Skopje 13:00 15:30 33.05
Bitola 19:00 20:15 23.80
KrivaPalanka 16:00 17:30 20.0
Skopje 14:00 14:30 28.3
KrivaPalanka 16:00 17:30 27.0
Bitola 21:00 21:10 18.5
KrivaPalanka

Output:
KrivaPalanka:            Skopje:
16:00 - 17:30 24.750     14:00 - 14:30 28.40
18:00 - 20:30 22.00      13:00 - 15:30 33.050
*/

class TemperatureByInterval {
    String startInterval;
    String endInterval;
    float temperature;

    public TemperatureByInterval(String startInterval, String endInterval, float temperature) {
        this.startInterval = startInterval;
        this.endInterval = endInterval;
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return startInterval + " - " + endInterval + " " + temperature;
    }
}

public class CBHTWeatherForecast2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());

        CBHT<String, LinkedList<TemperatureByInterval>> weather = new CBHT<>(n * 2 + 1);
        for (int i = 0; i < n; i++) {
            String[] line = input.readLine().split(" ");
            String place = line[0];
            String start = line[1];
            String end = line[2];
            float temperature = Float.parseFloat(line[3]);
            TemperatureByInterval tempObj = new TemperatureByInterval(start, end, temperature);

            SLLNode<MapEntry<String, LinkedList<TemperatureByInterval>>> searchNode = weather.search(place);
            LinkedList<TemperatureByInterval> tempList;
            if (searchNode == null) {
                tempList = new LinkedList<>();
                tempList.add(tempObj);
                weather.insert(place, tempList);
            } else {
                tempList = searchNode.element.value;
                boolean found = false;

                for (int j = 0; j < tempList.size(); j++) {
                    TemperatureByInterval t = tempList.get(j);
                    if (t.startInterval.equals(start) && t.endInterval.equals(end)) {
                        float averageTemp = (t.temperature + temperature) / 2;

                        tempList.set(j, new TemperatureByInterval(start, end, averageTemp));
                        found = true;
                        break;
                    }
                }
                if (!found)
                    tempList.add(tempObj);
            }
        }
        String place = input.readLine();
        SLLNode<MapEntry<String, LinkedList<TemperatureByInterval>>> searchPlace = weather.search(place);
        if (searchPlace != null) {
            LinkedList<TemperatureByInterval> tempList = searchPlace.element.value;
            System.out.println(place + ":");
            for (TemperatureByInterval t : tempList)
                System.out.println(t + "0");
        } else
            System.out.println("does not exist");
    }
}
