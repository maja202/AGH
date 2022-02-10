package lab10;

import java.awt.*;

public class Star implements XmasShape{
    int x;
    int y;
    double scale = 1.0;
    Color lineColor;
    Color fillColor;

    Star(int x, int y, Color lineColor, Color fillColor) {
        this.x = x;
        this.y = y;
        this.lineColor = lineColor;
        this.fillColor = fillColor;
    }

    Star(int x, int y, double scale, Color lineColor, Color fillColor) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.lineColor = lineColor;
        this.fillColor = fillColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        int midX = 50;
        int midY = 34;
        int radius[] = {118,40,90,40};
        int nPoints = 16;
        int[] X = new int[nPoints];
        int[] Y = new int[nPoints];

        for (double current=0.0; current<nPoints; current++)
        {
            int i = (int) current;
            double x = Math.cos(current*((2*Math.PI)/nPoints))*radius[i % 4];
            double y = Math.sin(current*((2*Math.PI)/nPoints))*radius[i % 4];

            X[i] = (int) x+midX;
            Y[i] = (int) y+midY;
        }

        g2d.setColor(fillColor);
        g2d.fillPolygon(X, Y, nPoints);

    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
