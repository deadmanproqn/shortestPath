package processingDemo;
/**
 * Simmple graph layout system
 * http://processingjs.nihongoresources.com/graphs
 * This code is in the public domain
 */

import processing.core.PApplet;

import static java.lang.Math.PI;

/**
 * Flow algorithm for drawing nodes in a circle
 */
class CircleFlowAlgorithm extends PApplet implements FlowAlgorithm {
    // draw all nodes in a big circle
    PApplet parent;

    CircleFlowAlgorithm(PApplet p) {
        parent = p;
    }

    public boolean reflow(Graph g) {
        float interval = (float) (2 * PI / (float) g.size());
        int cx = 300 / 2;
        int cy = 300 / 2;
        float vl = cx - (2 * g.getNode(0).r1) - 10;
        for (int a = 0; a < g.size(); a++) {
            int[] nc = universal.rotateCoordinate(vl, 0, (float) a * interval);
            g.getNode(a).x = cx + nc[0];
            g.getNode(a).y = cy + nc[1];
        }
        return true;
    }
}