package ChainOfResponsibility;

public class NumberHandler extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        if (request != null && request.matches("\\d+")) {
            System.out.println("NumberHandler processed: " + request);
        } else {
            System.out.println("NumberHandler: request is not numbers. Passing it on");
            super.handleRequest(request);
        }
    }
}
