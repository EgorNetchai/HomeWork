import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample {
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    public void operation1() throws InterruptedException {
        lock1.lock();
        System.out.println("lock1 acquired, waiting to acquire lock2");
        Thread.sleep(200);

        lock2.lock();
        System.out.println("lock2 acquired");

        System.out.println("Executing operation 1");

        lock2.unlock();
        lock1.unlock();
    }

    public void operation2() throws InterruptedException {
        lock2.lock();
        System.out.println("lock2 acquired, waiting to acquire lock1");
        Thread.sleep(200);

        lock1.lock();
        System.out.println("lock1 acquired");

        System.out.println("Executing operation 2");

        lock1.unlock();
        lock2.unlock();
    }

    public static void main(String[] args) {
        DeadLockExample deadLock = new DeadLockExample();

        Thread t1 = new Thread(() -> {
            try {
                deadLock.operation1();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                deadLock.operation2();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();
    }
}
