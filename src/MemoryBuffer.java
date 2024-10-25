import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import java.util.Random;

public class MemoryBuffer {
    final ArrayList<Integer> buffer;
    int maxSize;
    private ReentrantLock lock;
    final Condition notFull;
    final Condition notEmpty;

    public MemoryBuffer(int maxSize) {
        this.lock = new ReentrantLock();
        this.buffer = new ArrayList<>();
        this.maxSize = maxSize;
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();
    }

    public void produceData() throws InterruptedException{

        this.lock.lock();
        try {
            while (this.buffer.size() == maxSize) {
                System.out.println("Thread " + Thread.currentThread().getId() + " waiting for notFull Signal");
                notFull.await();
            }
            System.out.println("Producer accesses memory buffer");
            Random rand = new Random();
            this.buffer.add(rand.nextInt(1,10));
            System.out.println(this.buffer);
            notEmpty.signal();
        } finally {
            this.lock.unlock();
        }
        Thread.sleep(1000);
    }

    public void consumeData() throws InterruptedException {
        this.lock.lock();
        try {
            while(this.buffer.size() == 0) {
                System.out.println("Thread " + Thread.currentThread().getId() + " waiting for notEmpty Signal");
                notEmpty.await();
            }
            System.out.println("Consumer accesses memory buffer");
            System.out.println(this.buffer);
            this.buffer.remove(0);
            notFull.signal();
        } finally {
            this.lock.unlock();
        }
        Thread.sleep(1100);
    }

    public void displayBuffer() {
        System.out.println(this.buffer);
    }
}


