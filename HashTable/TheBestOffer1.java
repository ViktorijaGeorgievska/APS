import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// APS book basics

/*
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
    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public Integer getFee() {
        return fee;
    }
    public void setFee(Integer fee) {
        this.fee = fee;
    }
}

// key -> date, value -> the other info
public class TheBestOffer1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        CBHT<String, Lecture> hashtableOffers = new CBHT<>(N * 2 + 1);

        for (int i = 0; i < N; i++) {
            String []input = br.readLine().split(" ");
            Lecture lectureInfo = new Lecture(input[0], input[1], input[2], Integer.parseInt(input[3]));

            SLLNode<MapEntry<String, Lecture>> hashElement = hashtableOffers.search(lectureInfo.date);    // it returns a linked list node (SLLNode) that contains a key-value pair
            if (hashElement == null) {
                // datumot ne postoi vo hashtable
                hashtableOffers.insert(lectureInfo.getDate(), lectureInfo);
            }
            else {
                // datumot postoi vo hashtable
                // (new fee from input > stored fee that already exsist)
                if (lectureInfo.getFee() > hashElement.element.value.getFee()) {
                    hashtableOffers.insert(lectureInfo.getDate(), lectureInfo);
                }
            }
        }

        String searchDate = br.readLine();
        SLLNode<MapEntry<String, Lecture>> result = hashtableOffers.search(searchDate);
        if (result != null) {
            Lecture best = result.element.value;
            System.out.println(best.getTime() + " " + best.getCity() + " " + best.getFee());
        }
        else {
            System.out.println("No offers found!");
        }
    }
}