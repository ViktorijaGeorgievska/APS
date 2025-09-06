import java.util.*;

public class AdjacencyMatrixGraph<T> {
    private int numVertices;
    private int[][] matrix;
    private T[] vertices;

    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(int numVertices) {
        this.numVertices = numVertices;
        matrix = new int[numVertices][numVertices];
        vertices = (T[]) new Object[numVertices];
    }

    public void addVertex(int index, T data) {
        vertices[index] = data;
    }

    public T getVertex(int index) {
        return vertices[index];
    }

    public void addEdge(int source, int destination) {
        matrix[source][destination] = 1;
        matrix[destination][source] = 1; // For undirected graph
    }

    public boolean isEdge(int source, int destination) {
        return matrix[source][destination] == 1;
    }


    public void removeEdge(int source, int destination) {
        matrix[source][destination] = 0;
        matrix[destination][source] = 0; // For undirected graph
    }

    @SuppressWarnings("unchecked")
    public void removeVertex(int vertexIndex) {
        if (vertexIndex < 0 || vertexIndex >= numVertices) {
            throw new IndexOutOfBoundsException("Vertex index out of bounds!");
        }
        int[][] newMatrix = new int[numVertices-1][numVertices-1];
        T[] newVertices = (T[]) new Object[numVertices-1];
        // Copy the vertices and matrix excluding the given vertex
        int ni = 0;
        for (int i = 0; i < numVertices; i++) {
            if (i == vertexIndex) continue;
            int nj = 0;
            for (int j = 0; j < numVertices; j++) {
                if (j == vertexIndex) continue;

                newMatrix[ni][nj] = matrix[i][j];     // во newMatrix се копира целата матрица, освен редот и колоната што се бришат
                nj++;
            }
            newVertices[ni] = vertices[i];            // во newVertices се копираат сите рабови, без оние што треба да се избришат
            ni++;
        }
        // Replace the old matrix and vertices with the new ones
        matrix = newMatrix;
        vertices = newVertices;
        numVertices--;
    }

    public List<T> getNeighbors(int vertexIndex) {
        List<T> neighbors = new ArrayList<>();
        for (int i = 0; i < matrix[vertexIndex].length; i++) {
            if (matrix[vertexIndex][i] == 1) {
                neighbors.add(vertices[i]);
            }
        }
        return neighbors;
    }

    /* Имаме граф: A - B - C и код:
    graph.addVertex(0, "A);
    graph.addVertex(1, "B);
    graph.addVertex(2, "C);

    graph.addEgde(0, 1);  ->   A - B
    graph.addEgde(1, 2);  ->   B - C

    getNeighbors(1) (за B) -> ["A", "C"] (ги враќа сите соседи на B)
    */

    public AdjacencyListGraph<T> toAdjacencyList() {
        AdjacencyListGraph<T> result = new AdjacencyListGraph<>();

        for(int i=0;i<numVertices;i++) {
            result.addVertex(vertices[i]);
        }

        for(int i=0;i<numVertices;i++) {
            for(int j=0;j<numVertices;j++) {
                if(matrix[i][j] > 0) {
                    result.addEdge(vertices[i], vertices[j]);
                }
            }
        }
        return result;
    }

    private void union(int u, int v, int[] trees) {
        int findWhat, replaceWith;
        if(u<v) {
            findWhat = trees[v];
            replaceWith = trees[u];
        } else {
            findWhat = trees[u];
            replaceWith = trees[v];
        }

        for(int i=0;i<trees.length;i++) {
            if(trees[i] == findWhat) {
                trees[i] = replaceWith;
            }
        }
    }

    public List<Edge> kruskal() {
        List<Edge> mstEdges = new ArrayList<>();
        List<Edge> allEdges = getAllEdges();

        allEdges.sort(Comparator.comparingInt(Edge::getWeight));

        int trees[] = new int[numVertices];

        for(int i=0;i<numVertices;i++)
            trees[i] = i;

        for(Edge e: allEdges) {
            if(trees[e.getFrom()] != trees[e.getTo()]) {
                mstEdges.add(e);

                union(e.getFrom(), e.getTo(), trees);
            }
        }

        return mstEdges;
    }

    public List<Edge> adaptedKruskal(int tress[]) {
        List<Edge> mstEdges = new ArrayList<>();
        List<Edge> allEdges = getAllEdges();

        allEdges.sort((a,b) -> a.getWeight() - b.getWeight());

        for (Edge e: allEdges) {
            if (tress[e.getFrom()] != tress[e.getTo()]) {
                mstEdges.add(e);

                union(e.getFrom(), e.getTo(), tress);
            }
        }
        return mstEdges;
    }

    public List<Edge> prim(int startVertexIndex) {
        List<Edge> mstEdges = new ArrayList<>();
        Queue<Edge> q = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        boolean included[] = new boolean[numVertices];

        for(int i=0;i<numVertices;i++) {
            included[i] = false;
        }

        included[startVertexIndex] = true;

        for(int i=0;i<numVertices;i++) {
            if(isEdge(startVertexIndex,i)) {
                q.add(new Edge(startVertexIndex, i, matrix[startVertexIndex][i]));
            }
        }

        while(!q.isEmpty()) {
            Edge e = q.poll();

            if(!included[e.getTo()]) {
                included[e.getTo()] = true;
                mstEdges.add(e);
                for(int i=0;i<numVertices;i++) {
                    if(!included[i] && isEdge(e.getTo(),i)) {
                        q.add(new Edge(e.getTo(), i, matrix[e.getTo()][i]));
                    }
                }
            }
        }

        return mstEdges;
    }


    private List<Edge> getAllEdges() {
        List<Edge> edges = new ArrayList<>();

        for(int i=0;i<numVertices;i++) {
            for(int j=0;j<numVertices;j++) {
                if(isEdge(i,j)) {
                    edges.add(new Edge(i, j, matrix[i][j]));
                }
            }
        }

        return edges;
    }

    @Override
    public String toString() {
        String ret = "  ";
        for(int i = 0; i < numVertices; i++)
            ret += vertices[i] + " ";
        ret += "\n";
        for(int i = 0; i < numVertices; i++){
            ret += vertices[i] + " ";
            for(int j = 0; j < numVertices; j++)
                ret += matrix[i][j] + " ";
            ret += "\n";
        }
        return ret;
    }

    public void printMatrix() {
        for(int i=0;i<numVertices;i++) {
            for(int j=0;j<numVertices;j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}