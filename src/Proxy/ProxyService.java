package Proxy;

public class ProxyService implements Service {
    private RealService realService;

    @Override
    public void performAction() {
        if (realService == null) {
            realService = new RealService();
        }
        System.out.println("ProxyService: perform before action");
        for (int i = 5; i < 10; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        realService.performAction();
        System.out.println("ProxyService: perform after action:");
        for (int i = 10; i < 15; i++) {
            System.out.print(" " + i);
        }
    }
}
