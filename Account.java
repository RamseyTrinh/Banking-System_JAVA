import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    public static final String CHECKING = "CHECKING";
    public static final String SAVINGS = "SAVINGS";

    protected long accountNumber;
    protected double balance;
    protected List<Transaction> transactionList = new ArrayList<>();

    /**
     * constructors.
     */
    public Account() {

    }

    /**
     * contrustor.
     */
    public Account(long accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public long getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    /**
     * Rút tiền.
     */
    public void doWithdrawing(double withdraw) throws BankException {
        if (withdraw < 0) {
            throw new InvalidFundingAmountException(withdraw);
        } else if (withdraw > this.balance) {
            throw new InsufficientFundsException(withdraw);
        }
        this.balance -= withdraw;

    }

    /**
     * Nạp tiền.
     */
    public void doDepositing(double deposit) throws InvalidFundingAmountException {
        if (deposit < 0) {
            throw new InvalidFundingAmountException(deposit);
        }
        this.balance += deposit;
    }

    public abstract void withdraw(double amount);

    public abstract void deposit(double amount);

    public void addTransaction(Transaction a) {
        transactionList.add(a);
    }

    /**
     * Lịch sử giao dịch.
     */
    public String getTransactionHistory() {
        String result = "";
        result += "Lịch sử giao dịch của tài khoản " + accountNumber + ":";
        for (Transaction transaction : transactionList) {
            result += "\n- " + transaction.getTransactionSummary();
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return getAccountNumber() == account.getAccountNumber()
                && Double.compare(account.getBalance(),
                getBalance()) == 0 && transactionList.equals(account.transactionList);
    }

}