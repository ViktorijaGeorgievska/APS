import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
APS book
Input:
3
Ivana Vodnjanska 4
Marko Leninova 18/2
Elena StivNaumov 10
4
Vodnjanska MajkaTereza
Leninova AmintaTreti
StivNaumov SlavkoJanevski
AdolfCiborovski GoranStefanovski
Elena

Output:
SlavkoJanevski 10
*/

public class OBHTHelpSantaClaus2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader((System.in)));

        int n = Integer.parseInt(input.readLine());
        OBHT<String, String> children = new OBHT<>(2 * n - 1);
        for (int i = 0; i < n; i++) {
            String[] line = input.readLine().split(" ");
            String childName = line[0];
            String street = line[1] + " " + line[2];

            children.insert(childName, street);
        }
        int m = Integer.parseInt(input.readLine());
        OBHT<String, String> streets = new OBHT<>(2 * m - 1);
        for (int i = 0; i < m; i++) {
            String[] line = input.readLine().split(" ");
            String oldName = line[0];
            String newName = line[1];

            streets.insert(oldName, newName);
        }
        String searchName = input.readLine();

        int indexChildren = children.search(searchName);
        if (indexChildren != -1) {
            String streetInfo = children.getBucket(indexChildren).value;
            String streetName = streetInfo.split(" ")[0];
            String streetNumber = streetInfo.split(" ")[1];

            int indexStreets = streets.search(streetName);
            if (indexStreets != -1) {
                String newStreetInfo = streets.getBucket(indexStreets).value;
                System.out.println(newStreetInfo + " " + streetNumber);
            } else
                System.out.println(streetInfo);
        } else
            System.out.println("No gift");
    }
}