package Decorator;

public class Coffee implements Drink {
    private final double COFFE_PRICE = 50.0d;

    @Override
    public String getDescription() {
        return "Coffee";
    }

    @Override
    public double getPrice() {
        return COFFE_PRICE;
    }
}
