import java.util.Scanner;

/*
APS book basics for obht
Input:
3
Magdalena Kostoska 15/05/1982
Hristina Mihajloska 30/05/1984
Ilinka Ivanovska 15/05/1986
15/05/2016

Output:
Ilinka Ivanovska 30
Magdalena Kostoska 34
*/

class Employee {
    String name;
    String surname;
    int birthYear;

    public Employee(String name, String surname, int birthYear) {
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
    }
}

public class CBHTBirthdaysMoreDifficult1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        CBHT<String, Employee> hashTable = new CBHT<>(n * 2 + 1);
        for (int i = 0; i < n; i++) {
            String name = in.next();
            String surname = in.next();
            String date = in.next();
  
            String birthDate = date.split("/")[0] + date.split("/")[1];                // 15/05/2016 ->  1505

            Employee employee = new Employee(name, surname, Integer.parseInt(date.split("/")[2]));
            hashTable.insertBirthdays(birthDate, employee);
        }

        String date = in.next();
        String key = date.split("/")[0] + date.split("/")[1];                          // 15/05/2016 ->  1505
        int year = Integer.parseInt(date.split("/")[2]);                               // 15/05/2016 ->  2016

        SLLNode<MapEntry<String, Employee>> hashElement = hashTable.search(key);

        if (hashElement != null) {
            while (hashElement != null) {
                int years = year - hashElement.element.value.birthYear;                 // сегашната - год на раѓање на вработениот (2016 - 1982)

                System.out.println(hashElement.element.value.name + " " + hashElement.element.value.surname + " " + years);
                hashElement = hashElement.succ;
            }
        }
        else 
            System.out.println("There is no birthdays at the date!");
    }
}
