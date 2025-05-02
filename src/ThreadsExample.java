import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadsExample {
    private boolean isThreadOneTurn = true;
    private final Lock threadLock = new ReentrantLock();
    private final Condition waitForThreadOne = threadLock.newCondition();
    private final Condition waitForThreadTwo = threadLock.newCondition();

    public void printByThreadOne() {
        while (true) {
            threadLock.lock();
            try {
                while (!isThreadOneTurn) {
                    waitForThreadOne.await();
                }
                System.out.print("1");
                isThreadOneTurn = false;
                waitForThreadTwo.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread 1 was interrupted");
            } finally {
                threadLock.unlock();
            }
        }
    }

    public void printByThread2() {
        while (true) {
            threadLock.lock();
            try {
                while (isThreadOneTurn) {
                    waitForThreadTwo.await();
                }
                System.out.print("2");
                isThreadOneTurn = true;
                waitForThreadOne.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread 2 was interrupted");
            } finally {
                threadLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ThreadsExample example = new ThreadsExample();

        Thread firstThread = new Thread(example::printByThreadOne);
        Thread secondThread = new Thread(example::printByThread2);

        firstThread.start();
        secondThread.start();
    }
}
