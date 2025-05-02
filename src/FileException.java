public class FileException extends Exception{
    FileException(String message) {
        super(message);
    }

    FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
