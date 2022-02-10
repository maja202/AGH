package lab10;

import java.awt.*;

public class Branch implements XmasShape{
    int x;
    int y;
    double scale = 1.0;
    Color lineColor;
    Color fillColor;

    Branch(int x, int y, Color lineColor, Color fillColor) {
        this.x = x;
        this.y = y;
        this.lineColor = lineColor;
        this.fillColor = fillColor;
    }

    Branch(int x, int y, double scale, Color lineColor, Color fillColor) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.lineColor = lineColor;
        this.fillColor = fillColor;
    }


    @Override
    public void render(Graphics2D g2d) {
        int x1[]={50,100,0};
        int y1[]={0,100,100};
        g2d.setColor(fillColor);
        g2d.fillPolygon(x1,y1,x1.length);
        g2d.setColor(lineColor);
        g2d.drawPolygon(x1, y1, x1.length);

    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
