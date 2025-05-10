package Decorator;

public class Coffee implements Drink{
    @Override
    public String getDescription() {
        return "Coffee";
    }

    @Override
    public double getPrice() {
        return 50.0d;
    }
}
