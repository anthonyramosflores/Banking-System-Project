/*
 * Author: Anthony Ramos Flores
 * Email: ramosflores@wisc.edu  
 * Course: CS 300, Fall 2025
 * Assignment: Program 03
 * Citations: NA
 */

import java.util.ArrayList;

/**
 * Manages a collection of bank accounts
 * Provides methods for account creation and banking transactions
 */

public class BankingSystem {
  private ArrayList<BankAccount> accounts;

  /** 
   * Creates a new banking system with an empty list of accounts
   */
    
  public BankingSystem() {
    accounts = new ArrayList<BankAccount>();
  }

  /**
   * Creates new account and adds it to system
   * @param accountNumber The account number
   * @param name The holders name
   * @param initialDeposit The initial amount deposited
   */
  public void createAccount(String accountNumber, String name, 
      double initialDeposit) {

    for (BankAccount account : accounts) {
      if (account.getAccountNumber().equals(accountNumber)) {
        throw new InvalidAccountException("Account number " + accountNumber + " already exists");
      }
    }

    BankAccount account = new BankAccount(accountNumber, name, initialDeposit);
    accounts.add(account);
  }
    
  /**
   * Finds an account by number
   * @param accountNumber The account number to search
   * @return The BankAccount with given number
   */
  public BankAccount findAccount(String accountNumber)
      throws InvalidAccountException {
    for (BankAccount account : accounts) {
      if (account.getAccountNumber().equals(accountNumber)) {
        return account;
      }
    }
    throw new InvalidAccountException(
        "Account number " + accountNumber + " not found"); // Replace this with exception throwing 
  }
    
  /**
   * Transfers money between accounts
   * @param fromAccountNum The source of account number
   * @param toAccountNum The destination account number
   * @param amount The amount to transfer
   */
  public void transferMoney(String fromAccountNum, String toAccountNum,
      double amount) throws InsufficientFundsException, InvalidAccountException {
    if (amount <= 0) {
      throw new IllegalArgumentException("Transfer amount cannot be negative");
    }

    if (fromAccountNum.equals(toAccountNum)) {
      throw new IllegalArgumentException("Cannot transfer to the same account");
    }
        
    BankAccount fromAccount = findAccount(fromAccountNum);
    BankAccount toAccount = findAccount(toAccountNum);
        
    fromAccount.withdraw(amount);
    toAccount.deposit(amount);
  }

  /**
   * Displays account information from console
   * @param accountNumber The account number to display
   */
    
  public void displayAccountInfo(String accountNumber)
      throws InvalidAccountException {
    BankAccount account = findAccount(accountNumber);
    System.out.println(account.toString());
  }

  /** 
   * Calculates total balance from all accounts
   * @return The sum of all account balances
   */
    
  public double getTotalBankBalance() {
    double total = 0;
    for (BankAccount account : accounts) {
      total += account.getBalance();
    }
    return total;
  }
}
