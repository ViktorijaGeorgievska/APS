import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
APS book
Input:
2
0610992333666 5.0
0901993222233 4.78
4
2205990121212 2.45
0901993222233 4.68
0610992333666 5.0
1511989984256 3.45
0610992333666    

Output: 
OK
*/

public class OBHTFacultyEnrollment2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());
        OBHT<String, Double> facultyHash = new OBHT<>(n * 2);
        for (int i = 0; i < n; i++) {
            String []lines = input.readLine().split(" ");
            facultyHash.insert(lines[0], Double.parseDouble(lines[1]));
        }
        int m = Integer.parseInt(input.readLine());
        OBHT<String, Double> eDictionaryHash = new OBHT<>(m * 2);
        for (int i = 0; i < m; i++) {
            String []lines = input.readLine().split(" ");
            eDictionaryHash.insert(lines[0], Double.parseDouble(lines[1]));
        }

        String checkStudent = input.readLine();
        if (facultyHash.search(checkStudent) != -1) {
            if (eDictionaryHash.search(checkStudent) != -1) {
                Double avg1 = facultyHash.getBucket(facultyHash.search(checkStudent)).value;
                Double avg2 = eDictionaryHash.getBucket(eDictionaryHash.search(checkStudent)).value;
                if (avg1.equals(avg2)) {
                    System.out.println("OK");
                }
                else {
                    System.out.println("Error");
                }
            }
            else {
                System.out.println("Empty");
            }
        }
        else {
            System.out.println("Empty");
        }
    }
}
