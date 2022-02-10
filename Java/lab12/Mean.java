package lab12;

import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Mean {
    static double[] array;
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);

    static void initArray(int size) {
        array = new double[size];

        for(int i=0;i<size;i++){
            array[i]= Math.random()*size/(i+1);
        }
    }

    static double calculateMean(int start, int end) {
        double sum = 0;
        for (int i = start; i < end; i++) {
            sum += array[i];
        }

        return (sum / (end - start));
    }

    static class MeanCalc extends Thread {
        private final int start;
        private final int end;
        double mean = 0;

        MeanCalc(int start, int end) {
            this.start = start;
            this.end=end;
        }

        public void run() {
            mean = calculateMean(start, end);

            try {
                results.put(mean);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
        }
    }

    static void parallelMean2(int cnt) throws InterruptedException {
        int counter = 0;
        int jump = array.length / cnt;
        double sum = 0;

        // utwórz tablicę wątków
        MeanCalc[] threads =new MeanCalc[cnt];

        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        // załóż, że array.length dzieli się przez cnt)
        for ( int i = 0; i < cnt; i++) {
            threads[i] = new MeanCalc(counter, counter + jump);
            counter += jump;
        }

        double t1 = System.nanoTime()/1e6;

        //uruchom wątki
        for(MeanCalc mc : threads) {
            mc.start();
        }

        double t2 = System.nanoTime()/1e6;

        for (int i = 0; i < cnt; i++) {
            sum += results.take();
        }

        sum /= cnt;

        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                sum);
    }

    static void parallelMean(int cnt) throws InterruptedException {
        double m = 0;
        int counter = 0;
        int jump = array.length / cnt;

        // utwórz tablicę wątków
        MeanCalc threads[]=new MeanCalc[cnt];

        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        // załóż, że array.length dzieli się przez cnt)
        for ( int i = 0; i < cnt; i++) {
            threads[i] = new MeanCalc(counter, counter + jump);
            counter += jump;
        }

        double t1 = System.nanoTime()/1e6;

        //uruchom wątki
        for(MeanCalc mc : threads) {
            mc.start();
        }

        double t2 = System.nanoTime()/1e6;

        // czekaj na ich zakończenie używając metody ''join''
        for(MeanCalc mc:threads) {
            mc.join();
        }
        // oblicz średnią ze średnich
        for(MeanCalc mc : threads) {
            m += mc.mean;
        }

        m /= cnt;

        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                m);
    }



    public static void main(String[] args) throws InterruptedException {
//        System.out.println("125 watkow");
//        initArray(1000000);
//        parallelMean2(125);
//
//        System.out.println("----------------------------------------------");
//
//        System.out.println("8 watkow");
//        initArray(1000000);
//        parallelMean2(8);
//
//        System.out.println("----------------------------------------------");
//
//        System.out.println("8 watkow");
//        initArray(1000000);
//        parallelMean(8);


            initArray(128000000);
            for(int cnt:new int[]{1,2,4,8,16,32,64,128}){
                parallelMean2(cnt);
            }

    }
}
