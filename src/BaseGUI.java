import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import processing.core.PApplet;
import processingDemo.*;

public class BaseGUI {
    private JPanel panel1;
    private JTextField verticesNo;
    private JTextArea graphWeightMap;
    private JTextField priList;
    private JButton generateButton;
    private JButton resetButton;
    private JTextField priNode;
    private JTextField speed;
    private JTextField startNodeField;

    public static void main(String[] args) {
        JFrame frame = new JFrame("BaseGUI");
        frame.setContentPane(new BaseGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void disableField(boolean i) {
        if (i) {
            verticesNo.setEditable(true);
            graphWeightMap.setEditable(true);
            priList.setEditable(true);
            generateButton.setEnabled(true);
            priNode.setEnabled(true);
            speed.setEnabled(true);
            startNodeField.setEnabled(true);
        } else {
            verticesNo.setEditable(false);
            graphWeightMap.setEditable(false);
            priList.setEditable(false);
            generateButton.setEnabled(false);
            priNode.setEnabled(false);
            speed.setEnabled(false);
            startNodeField.setEnabled(false);
        }
    }

    public BaseGUI() {
        verticesNo.setText("5");
        graphWeightMap.setText("0 1 6 8 4\n1 0 8 5 6\n6 8 0 9 7\n8 5 9 0 8\n4 6 7 8 0");
        priList.setText("0.2 0.5 0.6");
        priNode.setText("3");
        speed.setText("70");
        startNodeField.setText("0");
        generateButton.addActionListener(this::actionPerformed);
        resetButton.addActionListener(new Reset());
    }


    public void actionPerformed(ActionEvent e) {
        int verNo, priNo, Tspeed, stNode;
        int[][] weightGraph;
        boolean priVal = true;
        Double[] pList;
        String[] tmp;
        disableField(false);
        //Get No of vertices, priority node, speed and start node
        verNo = Integer.parseInt(verticesNo.getText());
        priNo = Integer.parseInt(priNode.getText());
        Tspeed = Integer.parseInt(speed.getText());
        stNode = Integer.parseInt(startNodeField.getText());
        //Get priority list
        String list;
        pList = new Double[priNo + 1];
        if (priNo == 0) {
            priVal = false;
        } else {

            list = priList.getText();
            tmp = list.split(" ");

            pList[0] = 100d;
            for (int i = 1; i < priNo + 1; i++) {
                pList[i] = Double.parseDouble(tmp[i - 1]);
            }
        }


        String map = graphWeightMap.getText();

        //create matrix
        tmp = map.split("\n");
        weightGraph = new int[verNo][verNo];
        for (int i = 0; i < tmp.length; i++) {
            String[] tmpline = tmp[i].split(" ");
            for (int j = 0; j < tmpline.length; j++) {
                weightGraph[i][j] = Integer.parseInt(tmpline[j]);
            }

        }

        //change matrix base on start node
        if (stNode != 0) {
            //int[][] newG;
            //MatrixChange mxChange = new MatrixChange();
            weightGraph = MatrixChange.main(weightGraph, stNode, verNo);
            //System.out.println(newG.equals(weightGraph));
            MatrixChange.reset();
        }


        System.out.println("Number of vertices: " + verNo);
        System.out.println("speed: " + Tspeed);
        System.out.println("Start Node: " + stNode);
        System.out.println("Weight map base on start node: ");
        for (int[] i : weightGraph) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println("Number of priority nodes: " + priNo);
        if (priVal) {
            for (double i : pList) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        ArrayList<int[]> output;
        ArrayList<Integer> distanceArray;
        long start = System.nanoTime();
        if (priVal) {
            System.out.println("DFS and hamilton solution: ");
            NhanHamilton ham = new NhanHamilton();


            output = ham.main(verNo, pList, weightGraph, Tspeed, stNode);
            distanceArray = ham.getDistanceArray();
        } else {
            System.out.println("No priority there for using default algorithm");
            Notime notime = new Notime();
            output = notime.main(verNo, weightGraph, stNode);
            distanceArray = notime.getDistance();
        }
        long end = System.nanoTime();
        System.out.print("Execution time is " + (end - start) + " nanoseconds");
        System.out.print("Distance array:");
        for (int dist : distanceArray) {
            System.out.print(dist + " ");
        }
        System.out.println();
        if (output.size() != 0) {
//        Demo demo = new Demo();
//            demo.main(verNo, output.get(1),weightGraph);
            Demo[] demo = new Demo[output.size()];

            for (Demo dem : demo) {
                dem = new Demo();
                dem.main(verNo, output, weightGraph, distanceArray);

            }
            System.out.println();
        } else {
            JOptionPane.showMessageDialog(null, "NO PATH FOUND! There is no solution! GO AWAY PLEASE!");
        }
    }


    class Reset implements ActionListener {
        Reset() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            disableField(true);
            Demo.reset();

        }
    }
}
