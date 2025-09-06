import java.util.Scanner;
/*
APS book
6,6
######
# # ##
# # S#
# # ##
# E ##
######
*/
public class Maze2 {
    public static void main(String[] args) {
        AdjacencyListGraph<String> mazeGraph = new AdjacencyListGraph<>();

        Scanner input = new Scanner(System.in);

        String []parts = input.next().split(",");
        int m = Integer.parseInt(parts[0]);
        int n = Integer.parseInt(parts[1]);
        String []lines = new String[m];

        String startVertex = null, endVertex = null;
        for (int i = 0; i < m; i++) {
            lines[i] = input.next();

            for (int j = 0; j < n; j++) {
                if (lines[i].charAt(j) != '#') {
                   mazeGraph.addVertex(i + ",");
                }
                if (lines[i].charAt(j) == 'S') {
                    startVertex = i + "," + j;
                } else if (lines[i].charAt(j) == 'E') {
                    endVertex = i + "," + j;
                }

                if (i > 0 && lines[i-1].charAt(j) != '#') {
                    mazeGraph.addEdge((i-1) + "," + j, i + "," + j);
                }
                if (j > 0 && lines[i].charAt(j-1) != '#') {
                    mazeGraph.addEdge(i + "," + (j-1), i + "," + j);
                }
            }
        }
        input.close();
        mazeGraph.findPath(startVertex, endVertex);
    }
}
