import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class labLab {
    public static void main(String[] args) {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        ArrayList<String> list = new ArrayList<>();
        String objects;

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            objects = input.next();
            list.add(objects);
            graph.addVertex(objects);
        }
        int m = input.nextInt();
        input.nextLine();
        for (int i = 0; i < m; i++) {
            String []joined = input.nextLine().split(" ");
            String obj1 = joined[0];
            String obj2 = joined[1];
            graph.addEdge(obj1, obj2);
        }
        input.close();

        Set<String> visited = new HashSet<>();
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (!visited.contains(list.get(i))) {
                count++;
                graph.DFSUtil(list.get(i), visited );
            }
        }
        System.out.println(count);
    }
}
