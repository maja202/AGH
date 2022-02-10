package lab2;
import java.util.*;

public class Matrix {
    double[]data;
    int rows;
    int cols;

    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }

    Matrix (double[][] d) {
        int maxCol = 0;

        for (int i = 0; i < d.length; i++) {
            if (d[i].length > maxCol) {
                maxCol = d[i].length;
            }
        }

        this.rows = d.length;
        this.cols = maxCol;
        data = new double[this.rows * this.cols];

        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                this.data[i * cols + j] = d[i][j];
            }
        }
    }

    double[][] asArray() {
        double[][] twoDim = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                twoDim[i][j] = data[i*cols + j];
            }
        }
        return twoDim;
    }

    double get(int r, int c) {
        return data[this.cols * r + c];
    }

    void set(int r, int c, double value) {
        this.data[this.cols * r + c] = value;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i=0;i<rows;i++){
            buf.append("[");
            for (int j = 0; j < cols; j++) {
                buf.append(get(i, j));
                buf.append(",");
            }
            buf.append("], ");
        }
        buf.append("]");
        return buf.toString();
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols) {
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));
        } else {
            rows = newRows;
            cols = newCols;
        }
    }

    int[] shape() {
        int[] s = new int[2];
        s[0] = this.rows;
        s[1] = this.cols;

        return s;
    }

    Matrix add(Matrix m) {
        if (m.rows == this.rows && m.cols == this.cols) {
            Matrix newMatrix = new Matrix(this.rows, this.cols);
            System.arraycopy(this.data, 0, newMatrix.data, 0, this.rows * this.cols);

            for (int i = 0; i < rows*cols; i++) {
                newMatrix.data[i] += m.data[i];
            }
            return newMatrix;
        } else {
            throw new RuntimeException();
        }
    }

    Matrix sub(Matrix m) {
        if (m.rows == this.rows && m.cols == this.cols) {
            Matrix newMatrix = new Matrix(this.rows, this.cols);
            System.arraycopy(this.data, 0, newMatrix.data, 0, this.rows * this.cols);

            for (int i = 0; i < rows*cols; i++) {
                newMatrix.data[i] -= m.data[i];
            }
            return newMatrix;
        } else {
            throw new RuntimeException();
        }
    }

    Matrix mul(Matrix m) {
        if (m.rows == this.rows && m.cols == this.cols) {
            Matrix newMatrix = new Matrix(this.rows, this.cols);
            System.arraycopy(this.data, 0, newMatrix.data, 0, this.rows * this.cols);

            for (int i = 0; i < rows*cols; i++) {
                newMatrix.data[i] *= m.data[i];
            }
            return newMatrix;
        } else {
            throw new RuntimeException();
        }
    }

    Matrix div(Matrix m) {
        if (m.rows == this.rows && m.cols == this.cols) {
            Matrix newMatrix = new Matrix(this.rows, this.cols);
            System.arraycopy(this.data, 0, newMatrix.data, 0, this.rows * this.cols);

            for (int i = 0; i < rows*cols; i++) {
//                if(m.data[i] != 0) {
                    newMatrix.data[i] /= m.data[i];
//                }
            }
            return newMatrix;
        } else {
            throw new RuntimeException();
        }
    }

    Matrix add(double w) {
        for (int i = 0; i < rows * cols; i++) {
            this.data[i] += w;
        }
        return this;
    }

    Matrix sub(double w) {
        for (int i = 0; i < rows * cols; i++) {
            this.data[i] -= w;
        }
        return this;
    }

    Matrix mul(double w) {
        for (int i = 0; i < rows * cols; i++) {
            this.data[i] *= w;
        }
        return this;
    }

    Matrix div(double w) {
        if (w != 0) {
            for (int i = 0; i < rows * cols; i++) {
                this.data[i] /= w;
            }
            return this;
        } else {
            throw new RuntimeException();
        }
    }

    Matrix dot(Matrix m) {
        if (this.cols == m.rows) {
            double[][] thisTwoDim = this.asArray();
            double[][] mTwoDim = m.asArray();
            double[][] result = new double[this.rows][m.cols];

            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < m.cols; j++) {
                    for (int k = 0; k < this.cols; k++)
                        result[i][j] += thisTwoDim[i][k] * mTwoDim[k][j];
                }
            }
            Matrix endmatrix = new Matrix(result);
            return endmatrix;

        } else {
            throw new RuntimeException();
        }
    }

    double forbenius() {
        double forb = 0;

        for (int i = 0; i < this.rows * this.cols; i++) {
            forb += this.data[i] * this.data[i];
        }

        return Math.sqrt(forb);
    }

    public static Matrix random(int rows, int cols){
        Matrix m = new Matrix(rows,cols);
        Random r = new Random();
        m.set(0,0,r.nextDouble());

        for (int i = 0; i < rows * cols; i++) {
            m.data[i] = r.nextDouble();
        }
        return m;
    }

    public static Matrix eye(int n){
        Matrix m = new Matrix(n,n);
        m.add(1);

        return m;
    }

    public static void main(String[] args) {
        /*
        // test create object, set, get, toString
        Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        m.get(1, 1);
        m.set(1,0, 5.6);
        String s = m.toString();
        System.out.println(s);

        // test add, sub, mul, div
        m.add(2);
        m.sub(1);
        m.mul(4);
        m.div(7);

        String s1 = m.toString();
        System.out.println(s1);

        // test eye, random, forbenius

        String s2 = eye(4).toString();
        String s3 = random(3,4).toString();
        double forb = m.forbenius();

        System.out.println(s2);
        System.out.println(s3);
        System.out.println(forb);

        //test add, sub, mul, div matrix
        Matrix m2 = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});

        Matrix m3 = m.add(m2);
        m3 = m.sub(m2);
        m3 = m.div(m2);
        m3 = m.mul(m2);
        m3 = m.add(2);
        String s5 = m3.toString();
        String s4 = m2.toString();
        System.out.println(s4);
        System.out.println(s5);

        //test asArray

        double[][] arr = m.asArray();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println("");
        }
        System.out.println(Arrays.deepToString(arr));

        // test shape, reshape
        int[] shape = m.shape();
        System.out.println(Arrays.toString(shape));

        m.reshape(2, 8);
        shape = m.shape();
        System.out.println(Arrays.toString(shape));

        // test dot

        Matrix m4 = m.dot(m3);
        String s6 = m4.toString();
        System.out.println(s6);

      */
    }

}
