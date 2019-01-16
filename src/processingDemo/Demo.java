package processingDemo;

import processing.core.PApplet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class Demo extends PApplet {
    private static int nodeNo;
    private static ArrayList<int[]> pathArray = new ArrayList<>();
    private static int[][] graph;
    private Node[] nod;
    private static int count = 0;
    private static ArrayList<Integer> distanceArray = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main("processingDemo.Demo");
        nodeNo = Integer.parseInt(args[0]);

    }

    public void main(int i, ArrayList<int[]> patht, int[][] graphA, ArrayList<Integer> distanceA) {
        PApplet.main("processingDemo.Demo");

        nodeNo = i;
        pathArray = (patht);
        graph = graphA;
        distanceArray = distanceA;
    }

    Graph g = null;
    int padding = 30;

    public void settings() {
        size(300, 300);
    }

    public void setup() {
        frameRate(24);
        noLoop();


    }

    public void draw() {
        background(255);
        makeGrapp();
        //redraw();
        if (g != null) {
            boolean done = g.reflow();

            int[] path = pathArray.get(count);
            int distance = distanceArray.get(count);
            count++;
            g.draw(distance, path[0]);
            for (int i = 0; i < path.length - 1; i++) {
                g.drawPath(nod[path[i]], nod[path[i + 1]]);
            }

            if (!done) {
                loop();
            } else {
                noLoop();
            }
        } else {
            makeGrapp();
            redraw();
        }

    }

    //    public void keyPressed()
//    {
//        // tree
//
//        // circular graph
//        if(key=='c' || key==99) {
//            this.dispose();  }
//        // force-directed graph
//
//    }
    void makeGrapp() {
        g = new Graph(this);
        int n = nodeNo;
        nod = new Node[nodeNo];
//        Node n1 = new Node("1",0,0,this);
//        Node n2 = new Node("2",0,0,this);
//        Node n3 = new Node("3",0,0,this);
//        Node n4 = new Node("4",0,0,this);
//        Node n5 = new Node("5",0,0,this);
//        Node n6 = new Node("6",0,0,this);
//        Node n7 = new Node("7",0,0,this);
//        Node n8 = new Node("8",0,0,this);
//        Node n9 = new Node("9",0,0,this);
//        Node n10 = new Node("10",0,0,this);
//        Node n11 = new Node("11",0,0,this);
        for (int i = 0; i < n; i++) {
            nod[i] = new Node(str(i), 0, 0, this);
            g.addNode(nod[i]);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    g.linkNodes(nod[i], nod[j]);
                }
            }
        }

        // add nodes to graph
//        g.addNode(n1);
//        g.addNode(n2);
//        g.addNode(n3);
//        g.addNode(n4);
//        g.addNode(n5);
//        g.addNode(n6);
//
//
//        g.linkNodes(n1,n2);
//        g.linkNodes(n2,n3);
//        g.linkNodes(n3,n4);
//        g.linkNodes(n4,n1);
//        g.linkNodes(n1,n3);
//        g.linkNodes(n2,n4);
//        g.linkNodes(n5,n6);
//        g.linkNodes(n1,n6);
//        g.linkNodes(n2,n5);
    }

    public static void reset() {
        nodeNo = 0;
        graph = null;
        pathArray = null;
        count = 0;

    }


}