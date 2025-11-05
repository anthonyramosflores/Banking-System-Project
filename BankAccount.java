/*
 * Author: Anthony Ramos Flores
 * Email: ramosflores@wisc.edu
 * Course: CS 300, Fall 2025
 * Assignment: Program 03
 * Citations: NA
 */

import java.util.ArrayList;

/**
 * Represents a bank account with account number, name holder, and balance
 * Provides methods for deposit, withdrawal, and account information retrieval
 */

public class BankAccount {
  private String accountNumber;
  private double balance;
  private String accountHolderName;
    
  /**
   * Creates new bank account with validation
   * @param accountNumber Must be 8 digits
   * @param accountHolderName Cannot be null or empty
   * @param initialBalance Cannot be negative
   */
  public BankAccount(String accountNumber, String accountHolderName, 
        double initialBalance) {
    if (accountNumber == null || accountNumber.length() != 8) {
      throw new InvalidAccountException("Account number must be exactly 8 digits");
    }
      
    for (int i = 0; i < accountNumber.length(); i++) {
      if (!Character.isDigit(accountNumber.charAt(i))) {
        throw new InvalidAccountException("Account number must be exactly 8 digits");
      }
    }

    if (accountHolderName == null || accountHolderName.trim().isEmpty()) {
      throw new IllegalArgumentException("Account holder name cannot be null or empty");
    }
      
    if (initialBalance < 0) {
      throw new IllegalArgumentException("Initial balance cannot be negative");
    }
        
    this.accountNumber = accountNumber;
    this.accountHolderName = accountHolderName;
    this.balance = initialBalance;
  }

  /**
   * Deposits money into account
   * @param amount The amount deposited
   */
  public void deposit(double amount) throws IllegalArgumentException {
    if (amount < 0) {
      throw new IllegalArgumentException("Deposit amount cannot be negative");
    }
    balance += amount;
  }
    
  /**
   * Withdraws money from account
   * @param amount The amount to withdraw
   */ 
  public void withdraw(double amount) throws InsufficientFundsException {
    if (amount < 0) {
      throw new IllegalArgumentException("Withdrawal amount cannot be negative");
    }

    if (amount > balance) {
      throw new InsufficientFundsException(
          "Insufficient funds. Balance: " + balance + ", Withdrawal: " + amount);
    }
        
    balance -= amount;
  }

  /**
   * Returns current balance
   * @return The account balance
   */
    
  public double getBalance() {
    return balance;
  }

  /**
   * Returns account number
   * @return The account number
   */
    
  public String getAccountNumber() {
    return accountNumber;
  }

  /**
   * Returns account holder name
   * @return The name of account holder
   */
    
  public String getAccountHolderName() {
    return accountHolderName;
  }

  /**
   * Returns formatted account info
   * @return String containing account details
   */
    
  public String toString() {
    return String.format("Account: %s, Holder: %s, Balance: $%.2f", 
        accountNumber, accountHolderName, balance);
  }
}
