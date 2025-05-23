package ChainOfResponsibility;

public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        AbstractHandler nullAbstractHandler = new NullCheckHandler();
        AbstractHandler numberAbstractHandler = new NumberHandler();
        AbstractHandler letterAbstractHandler = new LetterHandler();

        nullAbstractHandler.setNextHandler(numberAbstractHandler);
        numberAbstractHandler.setNextHandler(letterAbstractHandler);

        nullAbstractHandler.handleRequest("123");
        nullAbstractHandler.handleRequest("abc");
        nullAbstractHandler.handleRequest("!@#");
        nullAbstractHandler.handleRequest(null);
    }
}
