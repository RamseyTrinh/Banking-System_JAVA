public class InvalidFundingAmountException extends BankException {

    /**
     * contructor.
     */
    public InvalidFundingAmountException(String message) {
        super(message);
    }

    /**
     * contructors.
     */
    public InvalidFundingAmountException(double amount) {
        super("Số tiền không hợp lệ: $" + String.format("%.2f", amount));
    }
}
