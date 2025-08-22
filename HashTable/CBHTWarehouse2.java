import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
APS book
Input:                     
5                          
Analgin@Headache@80
Daleron@Headache@90
Spazmeks@Stomachache@120
Lineks@Stomachache@150
Loratidin@Allergy@150
Headache

Output:
Analgin

// прво решение
Хеш табела каде клуч е намената (болест), а вредност е објект DrugInfo (име и цена).
За секоја нова линија, проверувам дали има веќе лек за истата болест и го ажурирам
    само ако новиот лек е поевтин.
На крај го пребарувам најевтиниот лек според дадената намена.
*/
class DrugInfo {
    String name;
    int price;

    public DrugInfo(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
public class CBHTWarehouse2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());
        CBHT<String, DrugInfo> pharmacyHash = new CBHT<>(n * 2 - 1);
        for (int i = 0; i < n; i++) {
            String[] parts = input.readLine().split("@");
            String drugName = parts[0];
            String pain = parts[1];
            int drugPrice = Integer.parseInt(parts[2]);
            DrugInfo newDrugObj = new DrugInfo(drugName, drugPrice);

            SLLNode<MapEntry<String, DrugInfo>> node = pharmacyHash.search(pain);
            if (node == null)
                pharmacyHash.insert(pain, newDrugObj);
            else {
                if (drugPrice < node.element.value.price)
                    pharmacyHash.insert(pain, newDrugObj);
            }
        }
        String searchPain = input.readLine();
        SLLNode<MapEntry<String, DrugInfo>> result = pharmacyHash.search(searchPain);
        if (result != null)
            System.out.println(result.element.value.name);
        else
            System.out.println("Not found!");
    }
}

// второ решение
public class CBHTWarehouse2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());
        CBHT<String, String> pharmacyHelper = new CBHT<>(n * 2 - 1);
        for (int i = 0; i < n; i++) {
            String[] parts = input.readLine().split("@");
            String drug = parts[0];
            String illness = parts[1];
            int price = Integer.parseInt(parts[2]);

            String drugData = drug + " " + price;
            SLLNode<MapEntry<String, String>> searchNode = pharmacyHelper.search(illness);
            if (searchNode == null)
                pharmacyHelper.insert(illness, drugData);
            else {
                String[] dataInHash = searchNode.element.value.split(" ");
                int priceInHash = Integer.parseInt(dataInHash[1]);
                if (price < priceInHash) {
                    pharmacyHelper.insert(illness, drugData);
                }
            }
        }

        String checkIllness = input.readLine();
        SLLNode<MapEntry<String, String>> result = pharmacyHelper.search(checkIllness);
        if (result != null) {
            System.out.println(result.element.value.split(" ")[0]);
        } else
            System.out.println("Not found!");
    }
}
