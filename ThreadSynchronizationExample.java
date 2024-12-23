public class ThreadSynchronizationExample {
    public static void main(String[] args) {
        Counter counter = new Counter(); // Shared counter object

        // Create three threads that increment the shared counter
        Thread t1 = new Thread(new IncrementTask(counter));
        Thread t2 = new Thread(new IncrementTask(counter));
        Thread t3 = new Thread(new IncrementTask(counter));

        // Start all threads
        t1.start();
        t2.start();
        t3.start();

        // Wait for all threads to complete
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final value of the counter
        System.out.println("Final Counter Value: " + counter.getValue());
    }
}

// Counter class with a synchronized method
class Counter {
    private int value = 0;

    // Synchronized method to ensure thread safety
    public synchronized void increment() {
        value++;
    }

    // Method to get the current counter value
    public int getValue() {
        return value;
    }
}

// Task that increments the counter 100 times
class IncrementTask implements Runnable {
    private Counter counter;

    // Constructor to pass the shared counter object
    public IncrementTask(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            counter.increment();
        }
    }
}

