package lab10;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    List<XmasShape> shapes = new ArrayList<>();

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // tree
        addBranch(g, 100, 30, new Color(0,100,0), new Color(0,100,0));
        addBranchWithScale(g, 75, 60, 1.5, new Color(0,100,0), new Color(0,100,0));
        addBranchWithScale(g, 50, 110, 2, new Color(0,100,0), new Color(0,100,0));
        addTrunk(g, 67, 155, new Color(101,34,34), new Color(101,34,34), 30, 40);

        // star
        addStar(g, 138, 20, 0.25, Color.yellow, Color.yellow);

        // lights
        addLight(g, 68, 30, 122, 80,  1, Color.yellow);
        addLight(g, 55, 70, 147, 140,  1, Color.yellow);
        addLight(g, 47, 110, 180, 200,  1, Color.yellow);

        // blue bubbles
        addBubble(g, 100, 101, Color.blue, Color.blue);
        addBubble(g, 50, 91, Color.blue, Color.blue);
        addBubble(g, 60, 40, Color.blue, Color.blue);
        addBubble(g, 80, 140, Color.blue, Color.blue);

        // red bubbles
        addBubble(g, 70, 91, Color.red, Color.red);
        addBubble(g, 80, 45, Color.red, Color.red);
        addBubble(g, 100, 130, Color.red, Color.red);
        addBubble(g, 60, 120, Color.red, Color.red);

        // orange bubbles
        addBubble(g, 90, 110, Color.orange, Color.orange);
        addBubble(g, 80, 71, Color.orange, Color.orange);
        addBubble(g, 70, 91, Color.orange, Color.orange);
        addBubble(g, 50, 140, Color.orange, Color.orange);

        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }

    }

    public void addBubble(Graphics g, int x, int y, Color lineColor, Color fillColor) {
        Bubble newBubble = new Bubble(x, y, lineColor, fillColor);

        shapes.add(newBubble);
    }

    public void addBubbleWithScale(Graphics g, int x, int y, double scale, Color lineColor, Color fillColor) {
        Bubble newBubble = new Bubble(x, y, scale, lineColor, fillColor);

        shapes.add(newBubble);
    }

    public void addBranch(Graphics g, int x, int y, Color lineColor, Color fillColor) {
        Branch newBranch = new Branch(x, y, lineColor, fillColor);

        shapes.add(newBranch);
    }

    public void addBranchWithScale(Graphics g, int x, int y, double scale, Color lineColor, Color fillColor) {
        Branch newBranch = new Branch(x, y, scale, lineColor, fillColor);

        shapes.add(newBranch);
    }

    public void addTrunk(Graphics g, int x, int y, Color lineColor, Color fillColor, int width, int length) {
        Trunk newTrunk = new Trunk(x, y, lineColor, fillColor, width, length);

        shapes.add(newTrunk);
    }

    public void addStar(Graphics g, int x, int y, double scale, Color lineColor, Color fillColor) {
        Star newStar = new Star(x, y, scale, lineColor, fillColor);

        shapes.add(newStar);
    }

    public void addLight(Graphics g, int x, int y, int x1, int y1, double scale, Color lineColor) {
        Light newLight = new Light(x, y, x1, y1, scale, lineColor);

        shapes.add(newLight);
    }
}
