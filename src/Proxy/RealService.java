package Proxy;

public class RealService implements Service{
    @Override
    public void performAction() {
        System.out.println("RealService: Performing action:");
        for (int i = 0; i < 5; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
    }
}
