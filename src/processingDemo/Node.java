package processingDemo;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.PropertyPermission;

public class Node extends PApplet {
    PApplet parent;
    String label;
    ArrayList<Node> links = new ArrayList<Node>();
    ArrayList<Node> inlinks = new ArrayList<Node>();
    ArrayList<Node> outlinks = new ArrayList<Node>();
    ;

    Node(String _label, int _x, int _y, PApplet p) {
        parent = p;
        label = _label;
        x = _x;
        y = _y;
        r1 = 10;
        r2 = 10;
    }

    void addIncomingLink(Node n) {
        if (!inlinks.contains(n)) {
            inlinks.add(n);
        }
    }

    ArrayList<Node> getIncomingLinks() {
        return inlinks;
    }

    int getIncomingLinksCount() {
        return inlinks.size();
    }

    void addOutgoingLink(Node n) {
        if (!outlinks.contains(n)) {
            outlinks.add(n);
        }
    }

    ArrayList<Node> getOutgoingLinks() {
        return outlinks;
    }

    int getOutgoingLinksCount() {
        return outlinks.size();
    }

    //Visualization stuff
    int x;
    int y;
    int r1;
    int r2;

    void setPosition(int _x, int _y) {
        x = _x;
        y = _y;
    }

    void setRadii(int _r1, int _r2) {
        r1 = _r1;
        r2 = _r2;
    }

    public void draw() {

        parent.stroke(200);
        parent.fill(255);
        for (Node o : outlinks) {
            drawArrow(x, y, o.x, o.y);
        }
        parent.ellipse(x, y, r1 * 2, r2 * 2);
        parent.fill(50, 50, 255);
        parent.text(label, x + r1 * 2, y + r2 * 2);
    }

    int[] arrowhead = {0, -4, 0, 4, 7, 0};

    void drawArrow(int x, int y, int ox, int oy) {
        int dx = ox - x;
        int dy = oy - y;
        float angle = universal.getDirection(dx, dy);
        float vl = (float) (sqrt(dx * dx + dy * dy) - sqrt(r1 * r1 + r2 * r2) * 1.5);
        int[] end = universal.rotateCoordinate(vl, 0, angle);
        parent.line(x, y, x + end[0], y + end[1]);
//        drawArrowHead(x+end[0], y+end[1], angle);
    }

    void drawArrowW(int x, int y, int ox, int oy) {
        int dx = ox - x;
        int dy = oy - y;
        float angle = universal.getDirection(dx, dy);
        float vl = (float) (sqrt(dx * dx + dy * dy) - sqrt(r1 * r1 + r2 * r2) * 1.5);
        int[] end = universal.rotateCoordinate(vl, 0, angle);
        parent.line(x, y, x + end[0], y + end[1]);
        drawArrowHead(x + end[0], y + end[1], angle);
    }

    void drawArrowHead(int ox, int oy, float angle) {
        int[] rc1 = universal.rotateCoordinate(arrowhead[0], arrowhead[1], angle);
        int[] rc2 = universal.rotateCoordinate(arrowhead[2], arrowhead[3], angle);
        int[] rc3 = universal.rotateCoordinate(arrowhead[4], arrowhead[5], angle);
        int[] narrow = {ox + rc1[0], oy + rc1[1], ox + rc2[0], oy + rc2[1], ox + rc3[0], oy + rc3[1]};
        parent.stroke(0);
        parent.fill(255);
        parent.triangle(narrow[0], narrow[1], narrow[2], narrow[3], narrow[4], narrow[5]);
    }

    public void drawPath(Node b) {
        if (outlinks.contains(b)) {

            parent.stroke(255, 0, 0);
            parent.fill(0);

            drawArrowW(x, y, b.x, b.y);

        }
    }
}
