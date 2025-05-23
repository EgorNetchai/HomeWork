package ChainOfResponsibility;

public class NullCheckHandler extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        if (request == null) {
            System.out.println("NullCheckHandler: request is null. Processing complete");
        } else {
            System.out.println("NullCheckHandler: request is not null. Passing it on");
            super.handleRequest(request);
        }
    }
}
