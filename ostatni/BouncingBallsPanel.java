package ostatni;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBallsPanel extends JPanel {

    AnimationThread a = new AnimationThread();
    boolean suspend = true;

    List<Ball> balls = new ArrayList<>();

    static class Ball {
        int x;
        int y;
        double vx;
        double vy;
        Color color;
        double acceleration;
        public static int SIZE = 20;

        Ball (int x, int y, double vx, double vy, Color color, double acceleration) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.color = color;
            this.acceleration = acceleration;
        }

        public void moveNorth(){
            vy = Math.abs(vy) * -1;
        }

        public void moveSouth(){
            vy = Math.abs(vy);
        }

        public void moveWest(){
            vx = Math.abs(vx) * -1;
        }

        public void moveEast(){
            vx = Math.abs(vx);
        }
    }

    class AnimationThread extends Thread {
        public void run() {
            for (; ; ) {
                //przesuń kulki
                //wykonaj odbicia od ściany
                //wywołaj repaint
                //uśpij

                for (Ball b : balls) {
                    b.x += b.vx;
                    b.y += b.vy;
                }

                for (Ball b : balls) {

                    if (b.x < Ball.SIZE) {
                        b.x = Ball.SIZE;
                        b.vx *= -1;
                    }
                    if (b.x > getWidth() - Ball.SIZE) {
                        b.x = getWidth() - Ball.SIZE;
                        b.vx *= -1;
                    }
                    if (b.y < Ball.SIZE) {
                        b.y = Ball.SIZE;
                        b.vy *= -1;
                    }
                    if (b.y > getHeight() - Ball.SIZE) {
                        b.y = getHeight() - Ball.SIZE;
                        b.vy *= -1;
                    }

                }

                Collisions();

                repaint();

                try {
                    do {
                        sleep(1);
                    } while (suspend);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean Colliding(int x1, int y1, int x2, int y2) {
        int x = Math.abs(x1 - x2);
        int y = Math.abs(y1 - y2);
        int distance = x * x + y * y;
        int radiusSum = Ball.SIZE + Ball.SIZE;

        return distance < radiusSum * radiusSum;
    }

    public void Collisions() {

        for (Ball b : balls) {
            for (Ball b2 : balls) {
                if (b != b2) {
                    if (Colliding(b.x, b.y, b2.x, b2.y)) {
                        if(b.x < b2.x){ // b1 above b2
                            b.moveNorth();
                            b2.moveSouth();

//                            double temp = b.vx;
//                            b.vx = b2.vx;
//                            b2.vx = temp;
                        }
                        else{
                            b.moveSouth();
                            b2.moveNorth();

//                            double temp = b.vx;
//                            b.vx = b2.vx;
//                            b2.vx = temp;
                        }
                        if(b.y < b2.y){ // b1 at left side of b2
                            b.moveWest();
                            b2.moveEast();

//                            double temp = b.vy;
//                            b.vy = b2.vy;
//                            b2.vy = temp;
                        }
                        else{
                            b.moveEast();
                            b2.moveWest();

//                            double temp = b.vy;
//                            b.vy = b2.vy;
//                            b2.vy = temp;
                        }
                    }
                }
            }
        }
    }


    BouncingBallsPanel() {
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
        a.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Ball b : balls) {
            g.setColor(b.color);
            g.fillOval(b.x, b.y, 2 * Ball.SIZE, 2 * Ball.SIZE);

            g.setColor(b.color);
            g.drawOval(b.x, b.y, 2 * Ball.SIZE, 2 * Ball.SIZE);
        }
    }

    private void addBall() {
        Random random = new Random();

        double vx = ((random.nextDouble() > 0.5) ? -1 : 1 ) * random.nextDouble() * (random.nextInt(4) + 2);
        double vy = ((random.nextDouble() > 0.5) ? -1 : 1) * random.nextDouble() * (random.nextInt(4) + 2);

        int x = (int) (Math.abs(Ball.SIZE - (getWidth() - Ball.SIZE)) * random.nextDouble());
        int y = (int) (Math.abs(Ball.SIZE - (getHeight() - Ball.SIZE)) * random.nextDouble());

        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();

        Color c = new Color(r, g, b);

        double a = random.nextDouble();

        balls.add(new Ball(x, y, vx, vy, c, a));
    }


    void onStart() {
        System.out.println("Start or resume animation thread");
        suspend = false;
    }

    void onStop() {
        System.out.println("Suspend animation thread");
        suspend = true;
    }

    void onPlus() {
        System.out.println("Add a ball");

        addBall();
        repaint();
    }

    void onMinus() {
        System.out.println("Remove a ball");

        if(!balls.isEmpty()) {
            balls.remove(0);
            repaint();
        }
    }
}
