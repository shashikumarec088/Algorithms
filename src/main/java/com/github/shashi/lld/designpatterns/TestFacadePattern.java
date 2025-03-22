package com.github.shashi.lld.designpatterns;

// Authentication System
class AuthenticationService {
    public boolean authenticate(String userId, String password) {
        System.out.println("Authenticating user: " + userId);
        // Simulate authentication
        return "password123".equals(password);
    }
}

// Account Service
class AccountService {
    public boolean debit(String accountId, double amount) {
        System.out.println("Debiting " + amount + " from account: " + accountId);
        // Simulate account debit
        return true;
    }

    public boolean credit(String accountId, double amount) {
        System.out.println("Crediting " + amount + " to account: " + accountId);
        // Simulate account credit
        return true;
    }
}

// Notification Service
class NotificationService {
    public void sendNotification(String message) {
        System.out.println("Notification sent: " + message);
    }
}

// Transaction Logging
class TransactionLogger {
    public void log(String transactionDetails) {
        System.out.println("Transaction logged: " + transactionDetails);
    }
}

// Facade
class BankingFacade {
    private AuthenticationService authenticationService;
    private AccountService accountService;
    private NotificationService notificationService;
    private TransactionLogger transactionLogger;

    public BankingFacade() {
        this.authenticationService = new AuthenticationService();
        this.accountService = new AccountService();
        this.notificationService = new NotificationService();
        this.transactionLogger = new TransactionLogger();
    }

    public boolean transferFunds(String userId, String password, String fromAccount, String toAccount, double amount) {
        System.out.println("Initiating funds transfer...");

        // Step 1: Authenticate the user
        if (!authenticationService.authenticate(userId, password)) {
            System.out.println("Authentication failed!");
            return false;
        }

        // Step 2: Debit the source account
        if (!accountService.debit(fromAccount, amount)) {
            System.out.println("Debit failed!");
            return false;
        }

        // Step 3: Credit the destination account
        if (!accountService.credit(toAccount, amount)) {
            System.out.println("Credit failed!");
            return false;
        }

        // Step 4: Log the transaction
        String transactionDetails = "Transfer of " + amount + " from " + fromAccount + " to " + toAccount;
        transactionLogger.log(transactionDetails);

        // Step 5: Send a notification
        notificationService.sendNotification("Transaction successful: " + transactionDetails);

        System.out.println("Funds transfer successful!");
        return true;
    }
}

// Client Code
public class TestFacadePattern {
    public static void main(String[] args) {
        BankingFacade bankingFacade = new BankingFacade();

        // Perform a funds transfer
        boolean success = bankingFacade.transferFunds(
                "user123", "password123", "ACC001", "ACC002", 500.00
        );

        if (success) {
            System.out.println("Transaction completed successfully.");
        } else {
            System.out.println("Transaction failed.");
        }
    }
}
