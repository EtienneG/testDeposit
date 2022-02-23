package exception;

public class InvalidDepositException extends RuntimeException {
    public InvalidDepositException(String errorMessage) {
        super(errorMessage);
    }
}
