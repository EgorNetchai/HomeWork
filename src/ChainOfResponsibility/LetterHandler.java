package ChainOfResponsibility;

public class LetterHandler extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        if (request != null && request.matches("[a-zA-Z]+")) {
            System.out.println("LetterHandler processed: " + request);
        } else {
            System.out.println("LetterHandler: request is not a string. Passing it on");
            super.handleRequest(request);
        }
    }
}
