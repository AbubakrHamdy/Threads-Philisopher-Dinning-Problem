package dto;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private final int id;
    private final Lock lock;

    Fork(int id) {
        this.id = id;
        this.lock =new ReentrantLock();
    }

    public boolean pickupFork(Philosopher philosopher, State state) throws InterruptedException {
        if(this.lock.tryLock(100, TimeUnit.MILLISECONDS)) {
            this.lock.lock();
            System.out.println("Philosopher " + philosopher.getId() + " picked up fork " + id +"as " + state);
            return true;
        }
        return false;
    }
    public void putDownFork(Philosopher philosopher, State state) {
        this.lock.unlock();
        System.out.println("Philosopher " + philosopher.getId() + " put down fork " + id  +"as " + state);
    }
}
