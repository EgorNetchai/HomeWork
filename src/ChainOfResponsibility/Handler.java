package ChainOfResponsibility;

public interface Handler {
    void handleRequest(String request);

    void setNextHandler(Handler nextHandler);
}
