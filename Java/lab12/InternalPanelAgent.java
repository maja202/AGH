package lab12;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InternalPanelAgent extends Thread {
    static class InternalCall{
        private final int toFloor;

        InternalCall(int toFloor){
            this.toFloor = toFloor;
        }
    }

    InternalPanelAgent(ElevatorCar elevatorCar){
        this.elevatorCar = elevatorCar;
    }

    BlockingQueue<InternalCall> input = new ArrayBlockingQueue<>(100);
    ElevatorCar elevatorCar;

    public void run(){
        InternalCall call = null;

        for(;;){

            try {
                call = input.take();

                if (call.toFloor > elevatorCar.floor) {
                    ElevatorStops.get().setLiftStopUp(call.toFloor);
                } else {
                    ElevatorStops.get().setLiftStopDown(call.toFloor);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

}