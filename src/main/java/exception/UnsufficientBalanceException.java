package exception;

public class UnsufficientBalanceException extends RuntimeException {
    public UnsufficientBalanceException(String errorMessage) {
        super(errorMessage);
    }
}
