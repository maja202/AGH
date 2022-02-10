package lab10;

import java.awt.*;

public class Trunk implements XmasShape {
    int x;
    int y;
    int width;
    int length;
    double scale = 1.0;
    Color lineColor;
    Color fillColor;

    Trunk(int x, int y, Color lineColor, Color fillColor, int width, int length) {
        this.x = x;
        this.y = y;
        this.lineColor = lineColor;
        this.fillColor = fillColor;
        this.width = width;
        this.length = length;
    }

    Trunk(int x, int y, double scale, Color lineColor, Color fillColor, int width, int length) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.lineColor = lineColor;
        this.fillColor = fillColor;
        this.width = width;
        this.length = length;
    }


    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(lineColor);
        g2d.drawRect(x, y, width, length);
        g2d.setColor(fillColor);
        g2d.fillRect(x, y, width, length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
