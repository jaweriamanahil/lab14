// Import necessary classes
public class MultiThreadingExample {
    public static void main(String[] args) {
        // Create thread for printing numbers from 1 to 10
        Thread numberThread = new Thread(new NumberPrinter());

        // Create thread for printing squares of numbers from 1 to 10
        Thread squareThread = new Thread(new SquarePrinter());

        // Start both threads
        numberThread.start();
        squareThread.start();
    }
}

// First thread: Prints numbers from 1 to 10
class NumberPrinter implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Number: " + i);
            try {
                Thread.sleep(500); // Pause to simulate concurrent behavior
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Second thread: Prints squares of numbers from 1 to 10
class SquarePrinter implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Square: " + (i * i));
            try {
                Thread.sleep(500); // Pause to simulate concurrent behavior
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
