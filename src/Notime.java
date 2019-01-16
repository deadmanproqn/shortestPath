import java.util.ArrayList;
import java.util.Scanner;

public class Notime {
    public int mindistance = 1000;
    public int minroad[];
    public int totaldistance;
    public int subop[] = new int[1000];
    public int optimall[] = new int[1000];
    public int x[];
    public int index;
    public boolean free[];
    public int trace[] = new int[1000];
    public static int n;
    public static int[][] graph;
    ArrayList<int[]> output = new ArrayList<>();
    ArrayList<Integer> distance = new ArrayList<>();
    public int[] path;
    public int stNode;

    public void setup2() {
        minroad = new int[n + 1];
        free = new boolean[n];
        x = new int[n + 1];
        for (int i = 0; i < n; i++) {
            free[i] = true;
        }
        free[0] = false;
    }

    public void attempt3(int i) {
        int j;
        for (j = 0; j < n; j++) {
            if ((free[j]) && (graph[x[i - 1]][j] > 0)) {
                x[i] = j;
                if (i < n - 1) {
                    free[j] = false;
                    attempt3(i + 1);
                    free[j] = true;
                } else if (i == n - 1) {
                    if (graph[j][x[0]] > 0) {
                        System.out.print("\nPath : ");
                        int V = x.length;
                        int distance = 0;
                        for (int ii = 0; ii < V; ii++) {
                            System.out.print(x[ii] + " ");
                        }
                        for (int ii = 1; ii < V; ii++) {
                            distance = distance + graph[x[ii]][x[ii - 1]];
                        }
                        System.out.print("Distance = " + distance);
                        if (distance < mindistance) {
                            mindistance = distance;
                            for (int ii = 0; ii < V; ii++) {
                                minroad[ii] = x[ii];
                            }
                        }

                        System.out.println();
                        for (int ii = 0; ii < 1000; ii++) {
                            optimall[ii] = 0;
                            subop[ii] = 0;
                        }
                        index = 1;
                        subop[0] = 0;
                        for (int ii = 1; ii < V; ii++) {
                            optimization(x[ii - 1], x[ii]);
                        }
                        if (totaldistance < distance) {
                            System.out.print("Optimization : ");
                            for (int ii = 0; ii < index; ii++) {
                                System.out.print(" " + subop[ii]);
                            }
                            System.out.println(" Distance = " + totaldistance);
                        } else {
                            System.out.println("Already the shortest possible path of this path");
                        }

                    }
                }
            }
        }
    }

    public void optimization(int fp, int lp) {
        //Using Ford-Bellman algorithm
        int size = n;
        int length[] = new int[n];
        for (int i = 0; i < n; i++) {
            length[i] = 1000;
        }
        length[fp] = 0;
        boolean stop;
        int u, v, countloop;
        for (countloop = 0; countloop < size - 1; countloop++) {
            stop = true;
            for (u = 0; u < size; u++) {
                for (v = 0; v < size; v++) {
                    if ((length[v] > length[u] + graph[u][v]) && (graph[u][v] > 0)) {
                        length[v] = length[u] + graph[u][v];
                        trace[v] = u;
                        stop = false;
                    }
					/*if (stop) {
						break;
					}*/
                }
            }
        }
        if (length[lp] == 1000) {
            System.out.println("No path");
        } else {

            //System.out.print("Distance = " + length[lp] + " ");
            int llp = lp;
            int ffp = fp;
            totaldistance = totaldistance + length[lp];
            int tempo[] = new int[n];
            for (int i = 0; i < n; i++) {
                tempo[i] = 0;
            }
            int k = 0;
            while (llp != ffp) {
                tempo[k] = llp;
                llp = trace[llp];
                k++;
            }
            for (int i = 0; i < k; i++) {
                subop[index] = tempo[k - i - 1];
                index++;
            }
        }
    }

    public void printmin() {
        System.out.print("\nMin Path : ");
        int V = minroad.length;
        path = minroad;
        distance.add(mindistance);
        for (int ii = 0; ii < V; ii++) {
            System.out.print(minroad[ii] + " ");
        }
        System.out.println("Distance = " + mindistance);
        for (int ii = 0; ii < 1000; ii++) {
            optimall[ii] = 0;
            subop[ii] = 0;
        }
        index = 1;
        subop[0] = 0;
        for (int ii = 1; ii < V; ii++) {
            optimization(minroad[ii - 1], minroad[ii]);
        }
        if (totaldistance < mindistance) {
            ArrayList<Integer> optpath = new ArrayList<>();
            System.out.print("Optimization : ");
            for (int ii = 0; ii < index; ii++) {
                System.out.print(" " + subop[ii]);
                optpath.add(subop[ii]);
            }
            System.out.println(" Distance = " + totaldistance);
            path = new int[optpath.toArray().length];
            for (int ind = 0; ind < path.length; ind++) {
                //int val= (Integer)
                path[ind] = optpath.get(ind);
            }
            int[] cPath;
            cPath = changeStart(path);
            output.add(cPath);
        } else {
            System.out.println("Already the shortest possible path of this path");
            int[] cPath;
            cPath = changeStart(path);
            output.add(changeStart(cPath));
        }
        System.out.println();
    }

    private int[] changeStart(int[] path) {
        if (this.stNode == 0) {
            return path;
        }
        for (int i = 0; i < path.length; i++) {
            if (path[i] == 0) {
                path[i] = this.stNode;
            } else if (path[i] == this.stNode) {
                path[i] = 0;
            }
        }
        return path;
    }

    public static void main(String[] args) {
        Notime notime = new Notime();
        Scanner scan = new Scanner(System.in);
        System.out.println("HamiltonianCycle Algorithm Test\n");
        System.out.println("Enter number of vertices\n");
        int V = scan.nextInt();
        n = V;
        /** get graph **/
        System.out.println("\nEnter road matrix\n");
        int graph1[][] = new int[V][V];
        graph = new int[V][V];
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                graph1[i][j] = scan.nextInt();
        graph = graph1;
        notime.setup2();
        notime.attempt3(1);
        notime.printmin();
        scan.close();
    }

    public ArrayList<int[]> main(int vertices, int[][] graph, int stNode) {
        this.stNode = stNode;
        n = vertices;
        Notime.graph = graph;
        setup2();
        attempt3(1);
        printmin();
        return output;
    }

    public ArrayList<Integer> getDistance() {
        return distance;
    }
}