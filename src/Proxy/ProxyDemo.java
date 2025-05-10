package Proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        Service service = new ProxyService();
        service.performAction();
    }
}
