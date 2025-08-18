import java.util.Scanner;

/*
APS book
Input:
3
IlinkaIvanoska A
MagdalenaKostoska A
HristinaMihajloska B
1
IgorKulev

Output:
IlinkaIvanoska
IgorKulev
HristinaMihajloska
MagdalenaKostoska
*/

public class Consultations2 {
    public static ArrayQueue<String> scheduleConsultations(ArrayQueue<String> aps, ArrayQueue<String> mms, ArrayQueue<String> type) {
        ArrayQueue<String> schedule = new ArrayQueue<>(50);

        String typeQuestion = "";
        while (!aps.isEmpty()) {
            if (schedule.isEmpty()) {
                schedule.enqueue(aps.dequeue());
                typeQuestion = type.dequeue();
            } else {
                if (type.peek().equals(typeQuestion) && !mms.isEmpty()) {           // ако прашањето е исто
                    schedule.enqueue(mms.dequeue());
                    aps.enqueue(aps.dequeue());
                    type.enqueue(type.dequeue());
                    typeQuestion = "";
                } else {
                    schedule.enqueue(aps.dequeue());
                    typeQuestion = type.dequeue();
                }
            }
        }
        while (!mms.isEmpty())
            schedule.enqueue(mms.dequeue());

        return schedule;
    }

    public static void main(String[] args) {
        ArrayQueue<String> apsQueue = new ArrayQueue<>(50);
        ArrayQueue<String> mmsQueue = new ArrayQueue<>(50);
        ArrayQueue<String> typeQuestion = new ArrayQueue<>(50);

        Scanner input = new Scanner(System.in);
        int numAps = input.nextInt();
        for (int i = 0; i < numAps; i++) {
            apsQueue.enqueue(input.next());
            typeQuestion.enqueue(input.next());
        }
        int numMMS = input.nextInt();
        for (int i = 0; i < numMMS; i++)
            mmsQueue.enqueue(input.next());
        input.close();

        ArrayQueue<String> result = scheduleConsultations(apsQueue, mmsQueue, typeQuestion);
        while (!result.isEmpty()) System.out.println(result.dequeue());
    }
}

