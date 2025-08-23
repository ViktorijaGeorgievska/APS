import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
APS book
Input:                                 
5
SK1234AA Anita Angelovska
OH1212BE Aleksandar Antov
ST0989OO Ognen Spirovski
ST0000AB Sara Spasovska
SK8888KD Dino Ackov
50
SK8888KD 48 14:00:00 ST0000AB 55 12:00:02 ST0989OO 60 08:10:00 SK1234AA 65 20:00:10 OH1212BE 50 22:00:21

Output:
Ognen Spirovski
Sara Spasovska
Anita Angelovska
*/

class Driver implements Comparable<Driver> {
    String name;
    String surname;
    Date time;

    public Driver(String name, String surname, Date time) {
        this.name = name;
        this.surname = surname;
        this.time = time;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    @Override
    public int compareTo(Driver o) {
        return this.time.compareTo(o.time);
    }
}

public class OBHTTrafficLights2 {
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader input = new BufferedReader(new InputStreamReader((System.in)));

        int n = Integer.parseInt(input.readLine());
        OBHT<String, String> hashTable = new OBHT<>(2 * n);
        for (int i = 0; i < n; i++) {
            String[] lines = input.readLine().split(" ");
            hashTable.insert(lines[0], lines[1] + " " + lines[2]);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        LinkedList<Driver> drivers = new LinkedList<>();

        int speed = Integer.parseInt(input.readLine());
        String[] data = input.readLine().split(" ");

        for (int i = 0; i < data.length - 2; i += 3) {
            String carPlate = data[i];
            int speedDriver = Integer.parseInt(data[i + 1]);
            String time = data[i + 2];

            if (speedDriver > speed) {
                int searchIndex = hashTable.search(carPlate);
                String[] pom = hashTable.getBucket(searchIndex).value.split(" ");

                Driver newDriver = new Driver(pom[0], pom[1], formatter.parse(time));
                drivers.add(newDriver);
            }
        }
        Collections.sort(drivers);
        for (Driver driver : drivers)
            System.out.println(driver);
    }
}
