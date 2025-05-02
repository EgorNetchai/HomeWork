import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLockExample {
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    public void operation1() throws InterruptedException {
        while (true) {
            if (lock1.tryLock(200, TimeUnit.MILLISECONDS)) {
                System.out.println("lock1 acquired, trying to acquire lock2");
            }
            Thread.sleep(200);

            if (lock2.tryLock()) {
                System.out.println("lock2 acquired");
            } else {
                System.out.println("Cannot acquire lock2, releasing lock1.");
                lock1.unlock();
                continue;
            }

            System.out.println("Executing operation 1");
            break;
        }
        lock2.unlock();
        lock1.unlock();
    }

    public void operation2() throws InterruptedException {
        while (true) {
            if (lock2.tryLock(200, TimeUnit.MILLISECONDS)) {
                System.out.println("lock2 acquired, trying to acquire lock1");
            }
            Thread.sleep(200);

            if (lock1.tryLock()) {
                System.out.println("lock1 acquired");
            } else {
                System.out.println("Cannot acquire lock1, releasing lock2.");
                lock2.unlock();
                continue;
            }

            System.out.println("Executing operation 2");
            break;
        }
        lock1.unlock();
        lock2.unlock();
    }

    public static void main(String[] args) {
        LiveLockExample liveLock = new LiveLockExample();

        Thread t1 = new Thread(() -> {
            try {
                liveLock.operation1();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                liveLock.operation2();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();
    }
}
