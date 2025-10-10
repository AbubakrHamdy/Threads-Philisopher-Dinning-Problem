package dto;

import java.util.Random;

public class Philosopher implements Runnable {
    private final int id;
    private volatile boolean isFull;
    private Fork leftFork;
    private Fork rightFork;
    private final Random randomTime;
    private int timeToEatCounter;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.randomTime = new Random();
        this.timeToEatCounter = 0;
    }

    @Override
    public void run() {
        if (!isFull) {
            try {
                think();

                if (leftFork.pickupFork(this, State.LEFT)) {
                    if (rightFork.pickupFork(this, State.RIGHT)) {
                        eat();
                    }
                    rightFork.putDownFork(this, State.RIGHT);
                }
                leftFork.putDownFork(this, State.LEFT);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating");
        Thread.sleep(randomTime.nextInt(1000));
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking");
        timeToEatCounter++;
        Thread.sleep(randomTime.nextInt(1000));
    }

    private void setIsFull(boolean isFull) {
        this.isFull = isFull;
    }

    public int getId() {
        return id;
    }


}
