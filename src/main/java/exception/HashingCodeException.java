package exception;

import java.security.NoSuchAlgorithmException;

public class HashingCodeException extends RuntimeException {
    public HashingCodeException(String message, NoSuchAlgorithmException e) {
        super(message, e);
    }
}
