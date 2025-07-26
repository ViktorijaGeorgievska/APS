import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// APS book basics
/*
Input:
4
20.7.1976
16.7.1988
18.7.1966
5.6.1988
7        -> key
Output: 3
*/

// key -> month, value -> count (broi kolku ima od sekoj mesec)
public class Birthdays1 {
    public static void main(String[] args) throws IOException {
        CBHT<String, Integer> birthdays = new CBHT<>(23);

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        for (int i = 0; i < n; i++) {
            String []dates = input.readLine().split("\\.");

            if (birthdays.search(dates[1]) == null) {
                birthdays.insert(dates[1], 1);
            }
            else {
                SLLNode<MapEntry<String, Integer>> searchMonth = birthdays.search(dates[1]);
                birthdays.insert(dates[1], searchMonth.element.value + 1);
            }
        }
        String search = input.readLine();
        SLLNode<MapEntry<String, Integer>> searchMonth = birthdays.search(search);
        if (searchMonth == null) {
            System.out.println("Empty!");
        }
        else {
            System.out.println(searchMonth.element.value);
        }
    }
}
