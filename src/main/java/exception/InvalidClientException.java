package exception;

public class InvalidClientException extends RuntimeException{
    public InvalidClientException(String errorMessage) {
        super(errorMessage);
    }
}
