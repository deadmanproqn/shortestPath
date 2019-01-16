package processingDemo;

import processing.core.PApplet;

import java.util.ArrayList;

public class Graph extends PApplet {
    PApplet parent;
    ArrayList<Node> nodes = new ArrayList<Node>();
    FlowAlgorithm flower = new CircleFlowAlgorithm(this);

    Graph(PApplet p) {
        parent = p;
    }

    void addNode(Node node) {
        if (!nodes.contains(node)) {
            nodes.add(node);
        }
    }

    void setFlowAlgorithm(FlowAlgorithm f) {
        flower = f;
    }

    int size() {
        return nodes.size();
    }


    Node getNode(int index) {
        return nodes.get(index);
    }

    boolean linkNodes(Node n1, Node n2) {
        if (nodes.contains(n1) && nodes.contains(n2)) {
            n1.addOutgoingLink(n2);
            n2.addIncomingLink(n1);
            return true;
        }
        return false;
    }

    ArrayList<Node> getNodes() {
        return nodes;
    }

    boolean reflow() {
        return flower.reflow(this);
    }

    public void draw(int distt, int start) {
        for (Node n : nodes) {
            n.draw();
        }
        parent.text("Start at: " + start, parent.width / 9, parent.height / 9);
        parent.text("D = " + distt, parent.width / 9, (float) (parent.height * 0.9));
    }

    public void drawPath(Node a, Node b) {
        a.drawPath(b);
    }
}
