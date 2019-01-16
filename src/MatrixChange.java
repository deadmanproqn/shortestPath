import java.util.Scanner;

public class MatrixChange {
    public static int n;
    public static int[][] graph;

    public void Swapnodes(int a, int b) {
        int temp = 0;
        int temp2 = 0;
        for (int i = 0; i < n; i++) {
            if ((i != a) && (i != b)) {
                temp = graph[a][i];
                graph[a][i] = graph[b][i];
                graph[b][i] = temp;
                temp2 = graph[i][a];
                graph[i][a] = graph[i][b];
                graph[i][b] = temp2;
            }
        }
        temp = graph[a][b];
        graph[a][b] = graph[b][a];
        graph[b][a] = temp;
    }

    public static int[][] main(int[][] oGraph, int stNode, int V) {

//        Scanner scan = new Scanner(System.in);
//        System.out.println("Change Nodes leads to change in adjacency matrix\n");
//        System.out.println("Enter number of vertices\n");
//        int V = scan.nextInt();
        n = V;
        MatrixChange matrix = new MatrixChange();
//        System.out.println("\nEnter road matrix\n");
        int[][] graph1 = new int[V][V];
        graph1 = oGraph;
//        for (int i = 0; i < V; i++)
//            for (int j = 0; j < V; j++)
//                graph1[i][j] = scan.nextInt();
        //System.out.println("\nEnter node to set first\n");

        //int s1 = scan.nextInt();
        int s1 = stNode;
        graph = graph1;
        if ((s1 < n) && (s1 >= 0)) {
//            while (s1 > 0) {
//                matrix.Swapnodes(s1, s1 -1);
//                s1 = s1-1;
//            }
            matrix.Swapnodes(s1, 0);
        } else {
            System.out.println("Wrong nodes you got there");
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
        //scan.close();
        return graph;
    }

    //    public int[][] main(int[][] oGraph, int stNode,int V) {
//        this.graph = new int[V][V];
//        for (int i = 0; i < V; i++)
//            for (int j = 0; j < V; j++)
//                graph[i][j] = oGraph[i][j];
//
//        int node=stNode;
//        while (node > 0) {
//            this.Swapnodes(node, node-1);
//            node = node-1;
//        }
//        for (int i=0 ; i<V;i++) {
//            for (int j=0;j<V;j++) {
//                System.out.print(graph[i][j]+ " ");
//            }
//            System.out.println();
//        }
//        return graph;
//    }
    public static void reset() {
        graph = null;
    }

}