import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
APS book basics
Input:
7
27/01/2016 14:00 NewYork 6000
28/01/2016 08:00 Paris 3000
28/01/2016 14:00 Munich 5000
27/01/2016 09:00 Beijing 8000
27/01/2016 08:00 Seattle 4000
28/01/2016 09:00 SaltLakeCity 10000
28/01/2016 09:00 Lagos 12000
27/01/2016
Output:
09:00 Beijing 8000
-------------------------------------
Input:
7
27/01/2016 14:00 NewYork 6000
28/01/2016 08:00 Paris 3000
28/01/2016 14:00 Munich 5000
28/01/2016 09:00 Lagos 12000
27/01/2016 09:00 Beijing 8000
27/01/2016 08:00 Seattle 4000
28/01/2016 09:00 SaltLakeCity 10000
28/01/2016
Output:
09:00 Lagos 12000
*/

// прво решение
class Lecture {
    String date;
    String time;
    String city;
    Integer fee;

    Lecture(String date, String time, String city, Integer fee) {
        this.date = date;
        this.time = time;
        this.city = city;
        this.fee = fee;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public String getCity() {
        return city;
    }
    public Integer getFee() {
        return fee;
    }
}

// key -> date, value -> the other info
public class TheBestOffer1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        CBHT<String, Lecture> hashtableOffers = new CBHT<>(N * 2 + 1);

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            Lecture lectureInfo = new Lecture(input[0], input[1], input[2], Integer.parseInt(input[3]));

            SLLNode<MapEntry<String, Lecture>> hashElement = hashtableOffers.search(lectureInfo.date);    // it returns a linked list node (SLLNode) that contains a key-value pair
            if (hashElement == null)
                // datumot ne postoi vo hashtable
                hashtableOffers.insert(lectureInfo.getDate(), lectureInfo);
            else {
                // datumot postoi vo hashtable
                // (new fee from input > stored fee that already exsist)
                if (lectureInfo.getFee() > hashElement.element.value.getFee())
                    hashtableOffers.insert(lectureInfo.getDate(), lectureInfo);
            }
        }
        String searchDate = br.readLine();
        SLLNode<MapEntry<String, Lecture>> result = hashtableOffers.search(searchDate);
        if (result != null) {
            Lecture best = result.element.value;
            System.out.println(best.getTime() + " " + best.getCity() + " " + best.getFee());
        } else
            System.out.println("No offers found!");
    }
}

// второ решение
public class BestOffer1 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());

        CBHT<String, String> hashTable = new CBHT<>(n * 2 - 1);
        for (int i = 0; i < n; i++) {
            String[] line = input.readLine().split(" ");
            String date = line[0];
            String otherData = line[1] + " " + line[2] + " " + line[3];

            SLLNode<MapEntry<String, String>> checkDate = hashTable.search(date);
            if (checkDate != null) {
                String inHashData = checkDate.element.value;
                if (Integer.parseInt(line[3]) > Integer.parseInt(inHashData.split(" ")[2]))
                    hashTable.insert(date, otherData);
            } else
                hashTable.insert(date, otherData);
        }
        String searchDate = input.readLine();
        SLLNode<MapEntry<String, String>> searchNode = hashTable.search(searchDate);
        if (searchNode != null)
            System.out.println(searchNode.element.value);
        else
            System.out.println("No offers");
    }
}
