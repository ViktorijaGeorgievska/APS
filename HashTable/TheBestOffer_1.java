import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// APS book basics
public class TheBestOffer_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        CBHT<String, String> bestOffer = new CBHT<>(n * 2 + 1);

        for (int i = 0; i < n; i++) {
            String []lines = input.readLine().split(" ");
            String date = lines[0];
            String info = lines[1] + " " + lines[2] + " " + Integer.parseInt(lines[3]);

            SLLNode<MapEntry<String, String>> current = bestOffer.search(date);
            if (current == null) {
                bestOffer.insert(date, info);
            }
            else {
                int offeredPrice = Integer.parseInt(current.element.value.split(" ")[2]);
                if (Integer.parseInt(lines[3]) > offeredPrice) {
                    bestOffer.insert(date, info);
                }
            }
        }
        String searchDate = input.readLine();
        SLLNode<MapEntry<String, String>> search = bestOffer.search(searchDate);
        if (search == null) {
            System.out.println("No offers");
        }
        else {
            System.out.println(search.element.value);
        }
    }
}
