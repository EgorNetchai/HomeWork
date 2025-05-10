package Decorator;

public class Milk extends DrinkDecorator{
    public Milk(Drink wrappedDrink) {
        super(wrappedDrink);
    }

    @Override
    public String getDescription() {
        return wrappedDrink.getDescription() + ", milk";
    }

    @Override
    public double getPrice() {
        return wrappedDrink.getPrice() + 5.0d;
    }
}
