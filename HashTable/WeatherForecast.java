import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
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
*/
// APS book
public class WeatherForecast {
    public static class Interval {
        String startTime;
        String endTime;
        float temperature;

        public Interval(String startTime, String endTime, float temperature) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.temperature = temperature;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(startTime + " - " + endTime + " " + temperature);
            return builder.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());

        CBHT<String, ArrayList<Interval>> weatherMap = new CBHT<>(n * 2 + 1);
        for (int i = 0; i < n; i++) {
            String []parts = input.readLine().split(" ");

            String city = parts[0];
            String newStart = parts[1];
            String newEnd = parts[2];
            float temperature = Float.parseFloat(parts[3]);
            Interval newInterval = new Interval(newStart, newEnd, temperature);

            SLLNode<MapEntry<String, ArrayList<Interval>>> node = weatherMap.search(city);
            if (node == null) {
                ArrayList<Interval> intervals = new ArrayList<>();
                intervals.add(newInterval);
                weatherMap.insert(city, intervals);
            }
            else {
                ArrayList<Interval> intervals = node.element.value;
                boolean found = false;

                for (int j = 0; j < intervals.size(); j++) {
                    Interval current = intervals.get(j);

                    if (current.startTime.equals(newStart) && current.endTime.equals(newEnd)) {
                        float averageTemp = (current.temperature + temperature) / 2;

                        intervals.set(j, new Interval(newStart, newEnd, averageTemp));
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    intervals.add(newInterval);
                }
            }
        }
        String searchCity = input.readLine();
        SLLNode<MapEntry<String, ArrayList<Interval>>> result = weatherMap.search(searchCity);
        if (result == null) {
            System.out.println(searchCity + ": does not exist");
        }
        else {
            System.out.println(searchCity + ": ");
            for (Interval intervals : result.element.value) {
                System.out.println(intervals + "0");
            }
        }
    }
}
