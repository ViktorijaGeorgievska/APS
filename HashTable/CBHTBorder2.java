import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
APS book
Input:
4
A112233 IvanaIvanovska
B345680 AleksandarPetreski
A878999 ElenaTrajkovska
B783789 IvanIvanov
2
PetrankaJanevska PetrankaPetrovska
AleksandarPetreski AleksandarKocevski
B345680                                   B783789 
  
Output:                                   Output:
Not Allowed                               Allowed

passportHash: мапа од број на пасош → име+презиме.
nameChangedHash: мапа од старо име+презиме → ново име+презиме.
Прво бараме дали пасошот постои. Ако не постои, лицето не може да помине граница.
Ако постои, го проверуваме дали тоа име е меѓу променетите → ако да → Not Allowed, инаку Allowed.
*/
public class CBHTBorder2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());
        CBHT<String, String> passportHash = new CBHT<>(n * 2 - 1);
        for (int i = 0; i < n; i++) {
            String[] parts = input.readLine().split(" ");
            String numPassport = parts[0];
            String nameSurname = parts[1];
            passportHash.insert(numPassport, nameSurname);
        }

        int m = Integer.parseInt(input.readLine());
        CBHT<String, String> nameChangedHash = new CBHT<>(m * 2 - 1);
        for (int i = 0; i < m; i++) {
            String[] parts = input.readLine().split(" ");
            String oldName = parts[0];
            String newName = parts[1];
            nameChangedHash.insert(oldName, newName);
        }

        String checkPassport = input.readLine();
        SLLNode<MapEntry<String, String>> result = passportHash.search(checkPassport);
        if (result != null) {
            String name = result.element.value;
            SLLNode<MapEntry<String, String>> searchForChange = nameChangedHash.search(name);
            if (searchForChange != null)
                System.out.println("Not Allowed");
            else
                System.out.println("Allowed");
        } else
            System.out.println("Not Allowed");
    }
}
