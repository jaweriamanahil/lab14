import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class BankTransactionSimulation {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(); // Shared bank account object

        // Create multiple client threads
        Thread client1 = new Thread(new Client(account));
        Thread client2 = new Thread(new Client(account));
        Thread client3 = new Thread(new Client(account));

        // Start the threads
        client1.start();
        client2.start();
        client3.start();

        // Wait for all threads to finish
        try {
            client1.join();
            client2.join();
            client3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final balance
        System.out.println("Final Account Balance: " + account.getBalance());
    }
}

// BankAccount class with thread-safe operations
class BankAccount {
    private AtomicInteger balance = new AtomicInteger(0); // Atomic integer for thread-safe operations

    // Deposit method
    public void deposit(int amount) {
        balance.addAndGet(amount);
        System.out.println(Thread.currentThread().getName() + " deposited: " + amount);
    }

    // Withdraw method
    public void withdraw(int amount) {
        if (balance.get() >= amount) {
            balance.addAndGet(-amount);
            System.out.println(Thread.currentThread().getName() + " withdrew: " + amount);
        } else {
            System.out.println(Thread.currentThread().getName() + " insufficient funds to withdraw: " + amount);
        }
    }

    // Getter for balance
    public int getBalance() {
        return balance.get();
    }
}

// Client class simulates random transactions
class Client implements Runnable {
    private BankAccount account;

    public Client(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int i = 0; i < 10; i++) { // Each client performs 10 transactions
            int action = random.nextInt(2); // 0 for deposit, 1 for withdraw
            int amount = random.nextInt(100) + 1; // Random amount between 1 and 100

            if (action == 0) {
                account.deposit(amount);
            } else {
                account.withdraw(amount);
            }

            try {
                Thread.sleep(200); // Simulate delay between transactions
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



(3*2/4)+8