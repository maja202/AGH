package lab7;

import java.io.PrintStream;

public class BoundingBox {
    double xmin = Double.NaN;
    double ymin = Double.NaN;
    double xmax = Double.NaN;
    double ymax = Double.NaN;

    void addPoint(double x, double y){

        if (isEmpty()) {
            xmin = xmax = x;
            ymin = ymax = y;

            return;
        }

        if (x > xmax) {
            xmax = x;
        }

        if (x < xmin) {
            xmin = x;
        }

        if (y > ymax) {
            ymax = y;
        }

        if (y < ymin) {
            ymin = y;
        }
    }

    boolean contains(double x, double y) {
        return x <= xmax && x >= xmin && y <= ymax && y >= ymin;
    }

    boolean contains(BoundingBox bb) {
        return bb.xmax <= xmax && bb.xmin >= xmin && bb.ymax <= ymax && bb.ymin >= ymin;
    }

    boolean intersects(BoundingBox bb){
        if ((bb.xmax <= xmin) || (bb.xmin >= xmax) || (bb.ymin >= bb.ymax) || (bb.ymax <= ymin)) {
            return false;
        }
        return true;
    }

    BoundingBox add(BoundingBox bb){
        if (this.contains(bb)) {
            return this;
        }

        this.addPoint(bb.xmax, bb.ymax);
        this.addPoint(bb.xmin, bb.ymin);

        return this;
    }

    boolean isEmpty(){
        return Double.isNaN(xmin) || Double.isNaN(xmax) || Double.isNaN(ymin) || Double.isNaN(ymax);
    }

    double getCenterX(){
        if (isEmpty()) {
            throw new RuntimeException("BB jest pusty!");
        }
        return (xmax - xmin)/2.0;
    }

    double getCenterY(){
        if (isEmpty()) {
            throw new RuntimeException("BB jest pusty!");
        }
        return (ymax - ymin)/2.0;
    }

    static double haversine(double x1, double x2, double y1, double y2) {
        double dx = Math.toRadians(x2 - x1);
        double dy = Math.toRadians(y2 - y1);

        // convert to radians
        x1 = Math.toRadians(x1);
        x2 = Math.toRadians(x2);

        // apply formulae
        double a = Math.pow(Math.sin(dx / 2), 2) +
                Math.pow(Math.sin(dy / 2), 2) *
                        Math.cos(x1) *
                        Math.cos(x2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

    double distanceTo(BoundingBox bbx){
        if(isEmpty() || bbx.isEmpty()) {
            throw new RuntimeException("BB jest pusty!");
        }

        return haversine(xmin, xmax, ymin, ymax);
    }

    public void printToString(PrintStream out) {
        out.println("(" + xmin + " " + ymin + ", " + xmax + " " + ymax + ")");
    }

}
