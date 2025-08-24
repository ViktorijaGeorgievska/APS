import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
APS book from OBHT
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

class Prekrsok implements Comparable<Prekrsok> {
    String redNum;
    int hours, minutes, seconds;

    public Prekrsok(String redNum, int hours, int minutes, int seconds) {
        this.redNum = redNum;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    // 12:24:11     14:34:11 -> ke vrati pomalo od 0
    public int compareTo(Prekrsok p) {
        if (this.hours > p.hours) {
            return 1;
        }
        else if (this.hours < p.hours) {
            return -1;
        }
        else {
            if (this.minutes > p.minutes) {
                return 1;
            }
            else if (this.minutes < p.minutes) {
                return -1;
            }
            else {
                if (this.seconds > p.seconds) {
                    return 1;
                }
                else if (this.seconds < p.seconds) {
                    return -1;
                }
                else
                    return 0;
            }
        }
    }
}

public class CBHTTrafficLights2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        CBHT<String, String> hashtable = new CBHT<String, String>(2 * n - 1);

        for (int i = 0; i < n; i++) {
            String []lineParts = br.readLine().split(" ");
            String registerNumber = lineParts[0];
            String name = lineParts[1];
            String surname = lineParts[2];

            hashtable.insert(registerNumber, name + " " + surname);
        }

        int speed = Integer.parseInt(br.readLine());
        String line = br.readLine();
        String []data = line.split(" ");

        ArrayList<Prekrsok> listNaPrekrsoci = new ArrayList<Prekrsok>();
        for (int i = 0; i < data.length; i+=3) {
            String regData = data[i];
            int speedData = Integer.parseInt(data[i + 1]);
            String time = data[i + 2];

            if (speedData > speed) {
                String []vreme = time.split(":");
                int h = Integer.parseInt(vreme[0]);
                int m = Integer.parseInt(vreme[1]);
                int s = Integer.parseInt(vreme[2]);

                listNaPrekrsoci.add(new Prekrsok(regData, h, m, s));
            }
        }
        Collections.sort(listNaPrekrsoci);

        for (Prekrsok p : listNaPrekrsoci) {
            SLLNode<MapEntry<String, String>> current = hashtable.search(p.redNum);
            if (current != null) {
                System.out.println(current.element.value);
                current = current.succ;
            }
        }
    }
}
