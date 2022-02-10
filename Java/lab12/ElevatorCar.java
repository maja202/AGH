package lab12;

public class ElevatorCar extends Thread{
    int floor=0;

    public int getFloor() {
        return floor;
    }

    enum Tour {UP, DOWN};
    Tour tour = Tour.UP;
    enum Movement {STOP,MOVING};
    Movement movementState = Movement.STOP;

    public void run(){

        for(;;){

            if (ElevatorStops.get().getMinSetFloor() > floor) {
                    movementState = Movement.MOVING;
                    tour = Tour.UP;

                    try {
                        sleep(100L * (ElevatorStops.get().getMinSetFloor() - floor));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    floor = ElevatorStops.get().getMinSetFloor();

                    System.out.println("Piętro " + floor);

                    ElevatorStops.get().clearStopUp(floor);
                    movementState = Movement.STOP;

            } else if (ElevatorStops.get().getMaxSetFloor() < floor) {
                    movementState = Movement.MOVING;
                    tour = Tour.DOWN;

                    try {
                        sleep(100L * (floor - ElevatorStops.get().getMaxSetFloor()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                floor = ElevatorStops.get().getMaxSetFloor();

                System.out.println("Piętro " + floor);

                ElevatorStops.get().clearStopDown(floor);
                movementState = Movement.STOP;
            }

        }
    }
}
