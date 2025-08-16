import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

/*
APS book basic
Input:         Output:
5              B A E D C B A E D C B A E D C B A E D D
A 40 2
B 35 0
C 28 10
D 45 4
E 32 2
10
*/

class Process implements Comparable<Process> {
    private String name;
    private int arrivalTime, executionTime;

    public Process(String name, int arrivalTime, int executionTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.executionTime = executionTime;
    }

    public void updateTime(int quantum) {
        if (quantum > this.executionTime) {
            this.executionTime = 0;
        } else {
            this.executionTime -= quantum;
        }
    }

    public int getArrivalTime() {
        return this.arrivalTime;
    }

    public int getExecutionTime() {
        return this.executionTime;
    }

    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Process o) {
        if (this.arrivalTime > o.getArrivalTime())
            return 1;
        else if (this.arrivalTime == o.getArrivalTime())
            if (this.executionTime > o.getExecutionTime())
                return -1;
            else
                return 1;
        else
            return -1;
    }
}

public class RoundRobin1 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Process> processes = new LinkedList<>();
        Process p;
        LinkedQueue<Process> queue = new LinkedQueue<>();

        int n = Integer.parseInt(input.readLine());
        String []lines;
        for (int i = 0; i < n; i++) {
            lines = input.readLine().split(" ");
            String name = lines[0];
            int executionTime = Integer.parseInt(lines[1]);
            int arrivalTime = Integer.parseInt(lines[2]);

            p = new Process(name, arrivalTime, executionTime);
            processes.add(p);
        }
        int quantum = Integer.parseInt(input.readLine());

        Collections.sort(processes);
        for (int i = 0; i < n; i++) {
            queue.enqueue(processes.get(i));
        }

        while (!queue.isEmpty()) {
            p = queue.dequeue();
            p.updateTime(quantum);
            if (p.getExecutionTime() != 0)
                queue.enqueue(p);
            System.out.print(p + " ");
        }
    }
}
