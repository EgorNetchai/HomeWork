package ChainOfResponsibility;

public abstract class AbstractHandler implements Handler{
   private Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(String request) {
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("The request could not be processed: end of chain");
        }
    }
}
