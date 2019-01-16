import java.util.ArrayList;
import java.util.Scanner;


public class NhanHamilton {
    ArrayList<Integer> mindist;
    public int totaldistance;
    public int subop[] = new int[1000];
    public int optimall[] = new int[1000];
    public int index;
    public boolean free[];
    public static double times[];
    public int hamil[];
    public int prior[];
    public int trace[] = new int[1000];
    public static int n;
    public static int pri;
    public static int[][] graph;
    ArrayList<int[]> outputArray = new ArrayList<>();
    ArrayList<Integer> distanceArray = new ArrayList<>();
    public int[] path;
    public int stNode;

    public void PrintResult() {
        System.out.print("\nPath : ");
        int V = hamil.length;
        int vv = prior.length;
        //path=new int[V+vv-1];

        int distance = 0;
        for (int i = 0; i < vv; i++) {
            System.out.print(prior[i] + " ");
            path[i] = prior[i];
        }
        for (int i = 1; i < V; i++) {
            System.out.print(hamil[i] + " ");
            path[prior.length + i - 1] = hamil[i];
        }

        for (int i = 1; i < V; i++) {
            distance = distance + graph[hamil[i - 1]][hamil[i]];
        }
        for (int i = 1; i < vv; i++) {
            distance = distance + graph[prior[i - 1]][prior[i]];
        }

        System.out.print("Distance = " + distance);
        //outputArray.add(path);
        System.out.println();
    }

    public void setup() {
        free = new boolean[n];
        prior = new int[times.length];
        hamil = new int[n - pri + 2];
        for (int i = 0; i < n; i++) {
            free[i] = true;
        }
        prior[0] = 0;
        free[0] = false;
    }

    public int timenow() {
        int vv = prior.length;
        int kk = 0;
        for (int i = 0; i < vv; i++) {
            if (free[i] == false) {
                kk++;
            }
        }
        int time1 = 0;
        if (kk == 1) {
            return 0;
        }
        if (kk == 2) {
            return graph[prior[0]][prior[1]];
        } else if (kk > 2) {
            for (int i = 0; i < kk - 1; i++) {
                if (free[i] == false) {
                    time1 = time1 + graph[prior[i]][prior[i + 1]];
                }
            }
        }
        return time1;
    }
//    public void attempt(int i) {
//        //Using Halminton
//        int j;
//        for (j=pri-1;j<n;j++) {
//            if ((free[j]) && (graph[hamil[i-1]][j]>0)) {
//                hamil[i] = j;
//                if (i<n-pri) {
//                    free[j] = false;
//                    attempt(i+1);
//                    free[j] = true;
//                }
//                else if (i==n-pri) {
//                    if (graph[j][hamil[0]] > 0) {
//                        System.out.print("\nPath : ");
//                        int V = hamil.length;
//                        int vv = prior.length;
//                        path= new int[V + vv - 1];
//                        totaldistance = 0;
//
//                        int distance = 0;
//                        for (int ii = 0; ii < vv; ii++) {
//                            System.out.print(prior[ii] +" ");
//                            path[ii]=prior[ii];
//                        }
//                        for (int ii = 1; ii < V; ii++) {
//                            System.out.print(hamil[ii] +" ");
//                            path[prior.length+ii-1]=hamil[ii];
//                        }
//
//                        for (int ii = 1; ii < V; ii++) {
//                            distance = distance + graph[hamil[ii-1]][hamil[ii]];
//                        }
//                        for (int ii = 1; ii < vv; ii++) {
//                            distance = distance + graph[prior[ii-1]][prior[ii]];
//                        }
//                        System.out.print("Distance = "+ distance);
//                        distanceArray.add(distance);
//                        System.out.println();
//
//                        for (int ii=0;ii<1000;ii++) {
//                            optimall[ii] = 0;
//                            subop[ii] =0;
//                        }
//                        index =1;
//                        subop[0] =0;
//                        for (int ii = 1; ii < vv; ii++) {
//                            optimization(prior[ii-1],prior[ii]);
//                        }
//                        for (int ii = 1; ii < V; ii++) {
//                            optimization(hamil[ii-1],hamil[ii]);
//                        }
//				        /*for (int ii =0;ii<index;ii++) {
//				        	optimall[ii] = subop[index -ii-1];
//				        }*/
//                        if (totaldistance < distance) {
//                            ArrayList<Integer>optpath = new ArrayList<>();
//                            System.out.print("Optimization : ");
//                            for (int ii=0;ii<index;ii++) {
//                                System.out.print(" " + subop[ii] );
//                                optpath.add(subop[ii]);
//                            }
//                            System.out.println(" Distance = " + totaldistance);
//
//                            path = new int[optpath.toArray().length];
//                            for(int ind=0;ind<path.length;ind++){
//                                //int val= (Integer)
//                                path[ind]= optpath.get(ind);
//                            }
//                            int[] cPath;
//                            cPath =changeStart(path);
//                            outputArray.add(cPath);
//                        } else {
//                            System.out.println("Already the shortest possible path of this path");
//                            int[] cPath;
//                            cPath =changeStart(path);
//                            outputArray.add(changeStart(cPath));
//                        }
//                    }
//
//                }
//            }
//        }
//    }

    public void attempt(int i) {
        //Using Halminton
        int j;
        boolean check = false;
        for (j = pri - 1; j < n; j++) {
            if ((free[j]) && (graph[hamil[i - 1]][j] > 0)) {
                check = true;
                hamil[i] = j;
                if (i < n - pri) {
                    free[j] = false;
                    attempt(i + 1);
                    free[j] = true;
                } else if (i == n - pri) {
                    if (graph[j][hamil[0]] > 0) {
                        System.out.print("\nPath : ");
                        int V = hamil.length;
                        int vv = prior.length;
                        path = new int[V + vv - 1];
                        totaldistance = 0;

                        int distance = 0;
                        for (int ii = 0; ii < vv; ii++) {
                            System.out.print(prior[ii] + " ");
                            path[ii] = prior[ii];
                        }
                        for (int ii = 1; ii < V; ii++) {
                            System.out.print(hamil[ii] + " ");
                            path[prior.length + ii - 1] = hamil[ii];
                        }

                        for (int ii = 1; ii < V; ii++) {
                            distance = distance + graph[hamil[ii - 1]][hamil[ii]];
                        }
                        for (int ii = 1; ii < vv; ii++) {
                            distance = distance + graph[prior[ii - 1]][prior[ii]];
                        }
                        System.out.print("Distance = " + distance);
                        distanceArray.add(distance);
                        System.out.println();

                        for (int ii = 0; ii < 1000; ii++) {
                            optimall[ii] = 0;
                            subop[ii] = 0;
                        }
                        index = 1;
                        subop[0] = 0;
                        for (int ii = 1; ii < vv; ii++) {
                            optimization(prior[ii - 1], prior[ii]);
                        }
                        for (int ii = 1; ii < V; ii++) {
                            optimization(hamil[ii - 1], hamil[ii]);
                        }
				        /*for (int ii =0;ii<index;ii++) {
				        	optimall[ii] = subop[index -ii-1];
				        }*/
                        if (totaldistance < distance) {
                            ArrayList<Integer> optpath = new ArrayList<>();
                            System.out.print("Optimization : ");
                            for (int ii = 0; ii < index; ii++) {
                                System.out.print(" " + subop[ii]);
                                optpath.add(subop[ii]);
                            }
                            path = new int[optpath.toArray().length];
                            for (int ind = 0; ind < path.length; ind++) {
                                //int val= (Integer)
                                path[ind] = optpath.get(ind);
                            }
                            int[] cPath;
                            cPath = changeStart(path);
                            outputArray.add(cPath);
                            System.out.println(" Distance = " + totaldistance);
                            distanceArray.remove(distanceArray.size() - 1);
                            distanceArray.add(totaldistance);
                        } else {
                            int[] cPath;
                            cPath = changeStart(path);
                            outputArray.add(cPath);
                            System.out.println("Already the shortest possible path of this path");
                        }
                    }
                }
            }
        }
        if (!check) {
            System.out.print("\nPath : ");
            int V = hamil.length;
            int vv = prior.length;
            path = new int[V + vv - 1];
            totaldistance = 0;

            int distance = 0;
            for (int ii = 0; ii < vv; ii++) {
                System.out.print(prior[ii] + " ");
                path[ii] = prior[ii];
            }
            for (int ii = 1; ii < V; ii++) {
                System.out.print(hamil[ii] + " ");
                path[prior.length + ii - 1] = hamil[ii];
            }

            for (int ii = 1; ii < V; ii++) {
                distance = distance + graph[hamil[ii - 1]][hamil[ii]];
            }
            for (int ii = 1; ii < vv; ii++) {
                distance = distance + graph[prior[ii - 1]][prior[ii]];
            }
            System.out.print("Distance = " + distance);
            distanceArray.add(distance);
            System.out.println();

            for (int ii = 0; ii < 1000; ii++) {
                optimall[ii] = 0;
                subop[ii] = 0;
            }
            index = 1;
            subop[0] = 0;
            for (int ii = 1; ii < vv; ii++) {
                optimization(prior[ii - 1], prior[ii]);
            }
            for (int ii = 1; ii < V; ii++) {
                optimization(hamil[ii - 1], hamil[ii]);
            }
	        /*for (int ii =0;ii<index;ii++) {
	        	optimall[ii] = subop[index -ii-1];
	        }*/
            if (totaldistance < distance) {
                ArrayList<Integer> optpath = new ArrayList<>();
                System.out.print("Optimization : ");
                for (int ii = 0; ii < index; ii++) {
                    System.out.print(" " + subop[ii]);
                    optpath.add(subop[ii]);
                }
                path = new int[optpath.toArray().length];
                for (int ind = 0; ind < path.length; ind++) {
                    //int val= (Integer)
                    path[ind] = optpath.get(ind);
                }
                int[] cPath;
                cPath = changeStart(path);
                outputArray.add(cPath);
                System.out.println(" Distance = " + totaldistance);
                distanceArray.remove(distanceArray.size() - 1);
                distanceArray.add(totaldistance);
            } else {
                int[] cPath;
                cPath = changeStart(path);
                outputArray.add(cPath);
                System.out.println("Already the shortest possible path of this path");
            }
        }
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

    public void attempt2(int i) {
        //Using Halminton
        //System.out.println("i = " +i);
        int j;
        for (j = 0; j < pri; j++) {
			/*System.out.println("i= "+ i+" j = " +j + " " + free[j]);
			int xxx = timenow() + a[prior[i-1]][j];
			System.out.println(j+ " " + free[j] +" " +times[j]+ " >= "+ xxx  + "?" );
			int vv = prior.length;
			for (int ii=0;ii<vv;ii++) {
				if  (free[ii] == false) {
					System.out.print(prior[ii]+" ");
				}
			}
			System.out.println(" Time = "+timenow());*/
            if ((free[j]) && (graph[prior[i - 1]][j] > 0) && (times[j] >= timenow() + graph[prior[i - 1]][j])) {
                //System.out.println("Attempt of " + i + " and " + j + " time now = "+ timenow()+ graph[prior[i-1]][j] );
                prior[i] = j;
                if (i < pri - 1) {
                    free[j] = false;
                    attempt2(i + 1);
                    free[j] = true;
                } else if (i == pri - 1) {
                    hamil[0] = prior[i];
                    free[prior[i]] = false;
                    attempt(1);
                    free[prior[pri - 1]] = true;
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

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("HamiltonianCycle Algorithm Test\n");
        System.out.println("Enter number of vertices\n");
        int V = scan.nextInt();
        n = V;
        NhanHamilton nhanHamilton = new NhanHamilton();
        System.out.println("\nEnter amout of time contrains ( priority list)\n");
        int hh = scan.nextInt();
        pri = hh;
        times = new double[hh];
        times[0] = 100;
        for (int i = 1; i < hh; i++) {
            times[i] = scan.nextDouble();
        }
        System.out.println("Enter speed you want");
        int speed = scan.nextInt();
        for (int i = 1; i < hh; i++) {
            times[i] = times[i] * speed;
        }
        System.out.println("\nEnter road matrix\n");
        int[][] graph1 = new int[V][V];
        graph = new int[V][V];
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                graph1[i][j] = scan.nextInt();
        graph = graph1;
        nhanHamilton.setup();
        nhanHamilton.attempt2(1);
        scan.close();
    }

    public ArrayList<int[]> main(int vertices, Double[] times, int[][] graph, int speed, int stNode) {
        this.stNode = stNode;
        n = vertices;
        pri = times.length;
        NhanHamilton.graph = graph;
        NhanHamilton.times = new double[pri];
        for (int i = 1; i < pri; i++) {
            NhanHamilton.times[i] = times[i] * speed;
        }
        setup();
        attempt2(1);
        int min = Integer.MAX_VALUE / 2;
        int i, minid = vertices;
        for (i = 0; i < distanceArray.size(); i++) {
            if (distanceArray.get(i) < min) {
                min = distanceArray.get(i);
                minid = i;
            }
        }
        mindist = new ArrayList<>();
        ArrayList<int[]> newpath = new ArrayList<>();
        newpath.add(outputArray.get(minid));
        mindist.add(distanceArray.get(minid));
        return newpath;
    }

    public ArrayList<Integer> getDistanceArray() {

        return mindist;
    }
}

