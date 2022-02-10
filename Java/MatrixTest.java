package lab2;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MatrixTest {

    @org.junit.Test
    public void Matrix() {
        Matrix original = new Matrix(new double[][]{{1,2},{3,4}});
        assertEquals(original.rows, 2);
        assertEquals(original.cols, 2);
    }

    @org.junit.Test
    public void testMatrix() {
        Matrix original = new Matrix(new double[][]{{1,2,5},{3,4}});
        double[][] originalArr = original.asArray();
        assertEquals(original.rows, originalArr.length);
        assertEquals(originalArr[1][2], 0, 0.1);
    }

    @org.junit.Test
    public void asArray() {
        Matrix original = new Matrix(new double[][]{{1,2,3},{3,4}});
        double[][] originalArr = original.asArray();

        assertEquals(original.rows, originalArr.length);

        for (int i = 0; i < originalArr.length; i++) {
            for (int j = 0; j < originalArr[i].length; j++) {
                assertEquals(originalArr[i][j], original.get(i, j), 0.1);
            }
        }
    }

    @org.junit.Test
    public void get() {
        Matrix original = new Matrix(new double[][]{{1,2},{3,4}});
        double fromGet = original.get(0, 1);
        assertEquals(2,fromGet,0.1);
    }

    @org.junit.Test
    public void set() {
        Matrix original = new Matrix(new double[][]{{1,2},{3,4}});
        original.set(0, 1, 9);
        double fromGet = original.get(0, 1);
        assertEquals(9,fromGet,0.1);
    }

    @org.junit.Test
    public void testToString() {
        String s= "[[1.0,2.3,4.56], [12.3,  45, 21.8]]";
        s= s.replaceAll("(\\[|\\]|\\s)+","");
        String[] t = s.split("(,)+");
        for(String x:t){
            System.out.println(String.format("\'%s\'",x ));
        }

        double[]d=new double[t.length];
        for(int i=0;i<t.length;i++) {
            d[i] = Double.parseDouble(t[i]);
        }

        double arr[][]=new double[1][];
        arr[0]=d;

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.println(arr[i][j]);
            }
        }
    }

    @org.junit.Test
    public void reshape() {
        try {
            Matrix original = new Matrix(new double[][]{{1,2},{3,4}});
            original.reshape(3, 3);
        } catch (Exception e) {
            return;
        }
        fail("Oczekiwano wyjatku!");
    }

    @org.junit.Test
    public void shape() {
        Matrix original = new Matrix(new double[][]{{1,2},{3,4}});
        int[] expected = {2, 2};
        int[] shape = original.shape();

        assertEquals(expected[0], shape[0]);
        assertEquals(expected[1], shape[1]);
    }

    @org.junit.Test
    public void add() {
        Matrix m1 = new Matrix(new double[][]{{1,1},{1,1}});
        Matrix m2 = new Matrix(new double[][]{{1,1},{1,1}});
        Matrix diff = m2.add(m1);
        double err = diff.forbenius();
        assertEquals(err,4,1e-5);
    }

    @org.junit.Test
    public void sub() {
        Matrix m1 = new Matrix(new double[][]{{1,2},{3,4}});
        Matrix m2 = new Matrix(new double[][]{{1,2},{3,4}});
        Matrix diff = m2.sub(m1);
        double err = diff.forbenius();
        assertEquals(err,0,1e-5);
    }

    @org.junit.Test
    public void mul() {
        Matrix m1 = new Matrix(new double[][]{{1,2},{3,4}});
        Matrix m2 = new Matrix(new double[][]{{0,0},{0,0}});
        Matrix diff = m2.mul(m1);
        double err = diff.forbenius();
        assertEquals(err,0,1e-5);
    }

    @org.junit.Test
    public void div() {
        Matrix m1 = new Matrix(new double[][]{{2,2},{2,2}});
        Matrix m2 = new Matrix(new double[][]{{1,1},{1,1}});
        Matrix diff = m1.div(m2);
        double err = diff.forbenius();
        assertEquals(err,4,1e-5);
    }

    @org.junit.Test
    public void testAdd() {
        Matrix m1 = new Matrix(new double[][]{{1,1},{1,1}});
        Matrix diff = m1.add(1);
        double err = diff.forbenius();
        assertEquals(err,4,1e-5);
    }

    @org.junit.Test
    public void testSub() {
        Matrix m1 = new Matrix(new double[][]{{4,4},{4,4}});
        Matrix diff = m1.sub(2);
        double err = diff.forbenius();
        assertEquals(err,4,1e-5);
    }

    @org.junit.Test
    public void testMul() {
        Matrix m1 = new Matrix(new double[][]{{1,1},{1,1}});
        Matrix diff = m1.mul(2);
        double err = diff.forbenius();
        assertEquals(err,4,1e-5);
    }

    @org.junit.Test
    public void testDiv() {
        Matrix m1 = new Matrix(new double[][]{{4,4},{4,4}});
        Matrix diff = m1.div(2);
        double err = diff.forbenius();
        assertEquals(err,4,1e-5);
    }

    @org.junit.Test
    public void dot() {
        try {
            Matrix m1 = new Matrix(new double[][]{{2,2},{2,2}});
            Matrix m2 = new Matrix(new double[][]{{1,0,1},{0,1,0},{2,2,3}});
            Matrix diff = m2.dot(m1);
            double err = diff.forbenius();
            assertEquals(err,4,1e-5);
        } catch (Exception e) {
            return;
        }
        fail("Oczekiwano wyjątku!");
    }

    @org.junit.Test
    public void forbenius() {
        Matrix m = new Matrix(new double[][]{{2, 2}, {2, 2}});
        double forb = m.forbenius();
        assertEquals(forb,4,1e-5);
    }

    @org.junit.Test
    public void eye() {
        Matrix m = Matrix.eye(3);
        double forb = m.forbenius();
        assertEquals(forb,3,1e-5);
    }
}