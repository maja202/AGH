package lab10;

import java.awt.*;

public class Light implements XmasShape {
    int x, x1;
    int y, y1;
    double scale = 1.0;
    Color lineColor;

    Light(int x, int y, int x1, int y1, double scale, Color lineColor) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.scale = scale;
        this.lineColor = lineColor;
    }

    Light(int x, int y, int x1, int y1, Color lineColor) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.lineColor = lineColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(lineColor);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(x, y, x1, y1);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
