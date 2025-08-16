import java.util.Scanner;

/*
APS book basic
Input:
5
A 40 2
B 35 0
C 28 10
D 45 4
E 32 2
10
Output:
B A E D C B A E D C B A E D C B A E D D
*/

class Process implements Comparable<Process> {
    String name;
    int executionTime;
    int arrivalTime;

    public Process(String name, int executionTime, int arrivalTime) {
        this.name = name;
        this.executionTime = executionTime;
        this.arrivalTime = arrivalTime;
    }

    public void updateExecutionTime(int quantum) {
        if (quantum > this.executionTime)
            this.executionTime = 0;
        else
            this.executionTime -= quantum;
    }

    @Override
    public int compareTo(Process o) {
        if (this.arrivalTime != o.arrivalTime)
            return Integer.compare(this.arrivalTime, o.arrivalTime);
        return Integer.compare(this.executionTime, o.executionTime);
    }

    @Override
    public String toString() {
        return name;
    }
}

public class RoundRobin1 {
    public static void sortList(SLL<Process> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            SLLNode<Process> current = list.getFirst();
            SLLNode<Process> next = current.succ;

            for (int j = 0; j < list.size() - i - 1; j++) {
                if (current.element.arrivalTime > next.element.arrivalTime) {
                    Process temp = current.element;
                    current.element = next.element;
                    next.element = temp;
                }
                current = next;
                next = current.succ;
            }
        }
    }

    public static void implementRoundRobin(SLL<Process> list, int quantum) {
        ArrayQueue<Process> processesQueue = new ArrayQueue<>(50);
        SLLNode<Process> current = list.getFirst();

        while (current != null) {
            processesQueue.enqueue(current.element);
            current = current.succ;
        }

        while (!processesQueue.isEmpty()) {
            Process p = processesQueue.dequeue();
            p.updateExecutionTime(quantum);

            if (p.executionTime != 0)
                processesQueue.enqueue(p);

            System.out.print(p + " ");
        }
    }

    public static void main(String[] args) {
        SLL<Process> list = new SLL<>();

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        for (int i = 0; i < n; i++) {
            String[] line = input.nextLine().split(" ");
            Process p = new Process(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]));
            list.insertLast(p);
        }
        int quantum = input.nextInt();
        input.close();

        sortList(list);
        implementRoundRobin(list, quantum);
    }
}
