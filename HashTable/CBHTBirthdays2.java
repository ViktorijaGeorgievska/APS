import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
APS book
Input:
4
Ivan 20.7.1976
Ivan 16.7.1988
Ana 18.7.1966
Ivan 5.6.1988
7

Output:
Ivan Ana
*/

public class CBHTBirthdays2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        CBHT<Integer, ArrayList<String>> birthdays = new CBHT<>(n * 2 + 1);

        for (int i = 0; i < n; i++) {
            String []lines = input.readLine().split(" ");
            String name = lines[0];
            String[] date = lines[1].split("\\.");
            int month = Integer.parseInt(date[1]);

            SLLNode<MapEntry<Integer, ArrayList<String>>> searchNode = birthdays.search(month);
            ArrayList<String> namesList;
            if (searchNode == null) {
                namesList = new ArrayList<>();
                namesList.add(name);
                birthdays.insert(month, namesList);
            } else {
                namesList = searchNode.element.value;
                if (!namesList.contains(name))
                    namesList.add(name);
                birthdays.insert(month, namesList);
            }
        }

        int searchMonth = Integer.parseInt(input.readLine());
        SLLNode<MapEntry<Integer, ArrayList<String>>> result = birthdays.search(searchMonth);
        if (result == null)
            System.out.println("Empty");
        else {
            ArrayList<String> namesList = result.element.value;
            for (String name : namesList)
                System.out.println(name);
        }
    }
}
