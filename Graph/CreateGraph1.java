/*
Input                     Output
5                         0 0 0 1
CREATE 4                  0 0 0 0
ADDEDGE 0 3               0 0 0 0
PRINTMATRIX               1 0 0 0
PRINTMATRIX               C
PRINTNODE 2               0
ADJACENT 0 2
DELETEEDGE 3 0
*/
import java.util.Scanner;

public class CreateGraph1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCommands = input.nextInt();
        input.nextLine();

        String []lines = input.nextLine().split(" ");         // CREATE 5
        int numVertices = Integer.parseInt(lines[1]);

        AdjacencyMatrixGraph<Character> graph = new AdjacencyMatrixGraph<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            graph.addVertex(i, (char) ((int) 'A' + i));             // addVertex(int index, T data)
        }

        for (int i = 1; i < numCommands; i++) {
            lines = input.nextLine().split(" ");
            switch (lines[0]) {
                case "ADDEDGE":
                    graph.addEdge(Integer.parseInt(lines[1]), Integer.parseInt(lines[2]));
                    break;
                case "DELETEDGE":
                    graph.removeEdge(Integer.parseInt(lines[1]), Integer.parseInt(lines[2]));
                    break;
                case "ADJACENT":
                    if (graph.isEdge(Integer.parseInt(lines[1]), Integer.parseInt(lines[2]))) {
                        System.out.println("1");
                    }
                    else {
                        System.out.println("0");
                    }
                    break;
                case "PRINTMATRIX":
                    graph.printMatrix();
                    break;
                case "PRINTNODE":
                    System.out.println(graph.getVertex(Integer.parseInt(lines[1])));
                    break;
                default:
                    System.out.println("Invalid command!" + lines[0]);
                    break;
            }
        }
        input.close();
    }
}
