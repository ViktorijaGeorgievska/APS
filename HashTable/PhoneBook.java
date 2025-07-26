import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Input:
3
070111222 IvanIvanoski
071222333 PetrePetrevski
022333444 TrajceTrajkovski
+38970111222
Output: IvanIvanoski

Input:
3
070111222 IvanIvanoski
071222333 PetrePetrevski
022333444 TrajceTrajkovski
+38977111222
Output: Unknown number
*/
// APS book
public class PhoneBook {
    public static String normalizePhoneNumber(String phoneNumber) {
        if (phoneNumber.startsWith("0")) {
            phoneNumber = phoneNumber.substring(1);
        }
        else if (phoneNumber.startsWith("+")) {
            phoneNumber = phoneNumber.substring(4);
        }
        return phoneNumber;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        CBHT<String, String> phones = new CBHT<>(n * 2 + 1);

        for (int i = 0; i < n; i++) {
            String []lines = input.readLine().split(" ");
            String number = lines[0];
            number = normalizePhoneNumber(number);
            String owner = lines[1];

            phones.insert(number, owner);
        }
        String searchNumber = input.readLine();
        searchNumber = normalizePhoneNumber(searchNumber);

        SLLNode<MapEntry<String, String>> searchNode = phones.search(searchNumber);
        if (searchNode == null) {
            System.out.println("Unknown number");
        }
        else {
            System.out.println(searchNode.element.value);
        }
    }
}
