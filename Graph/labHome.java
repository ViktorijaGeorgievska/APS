import java.util.Scanner;

public class labHome {
    public static void chechSum(AdjacencyListGraph<Integer> graph, int start, int sum, int currentSum, int []count) {
        currentSum += start;

        if (currentSum > sum)
            return;
        if (currentSum == sum)
            count[0]++;

        for (int neighbor : graph.getNeighbors(start)) {
            chechSum(graph, neighbor, sum, currentSum, count);
        }
    }

    public static void main(String[] args) {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        for (int i = 0; i < n; i++) {
            String []vertex = in.nextLine().split(" ");
            int v1 = Integer.parseInt(vertex[0]);
            int v2 = Integer.parseInt(vertex[1]);

            graph.addEdge(v1, v2);
        }
        int startVertex = in.nextInt();
        int sum = in.nextInt();
        in.close();

        int []count = {0};
        chechSum(graph, startVertex, sum, 0, count);
        System.out.println(count[0]);
    }
}
