import java.util.Arrays;
import java.util.Scanner;

/*
Greedy Fractional Knapsack
Courses + Lab
Input:
3
10 60
20 100
30 120

Output:
200
*/

class Task implements Comparable<Task> {
    int hours;
    int money;

    public Task(int hours, int money) {
        this.hours = hours;
        this.money = money;
    }

    @Override
    public int compareTo(Task o) {
        double radio1 = (double) this.money / this.hours;
        double radio2 = (double) o.money / o.hours;

        return Double.compare(radio2, radio1);
    }
}

public class GreedyTasks {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        Task[] tasks = new Task[n];
        for (int i = 0; i < n; i++) {
            int hour = input.nextInt();
            int money = input.nextInt();
            tasks[i] = new Task(hour, money);
        }
        input.close();
        Arrays.sort(tasks);            // пробај без овој

        int remainingHours = 40;
        double maxEarnings = 0;

        for (Task task : tasks) {
            if (remainingHours == 0)
                break;
            if (remainingHours > task.hours) {
                maxEarnings += task.money;
                remainingHours -= task.hours;
            } else {
                maxEarnings = maxEarnings + ((double) remainingHours / task.hours) * task.money;
                remainingHours = 0;
            }
        }
        System.out.printf("%.0f\n", maxEarnings);
    }
}
