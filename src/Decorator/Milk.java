package Decorator;

public class Milk extends DrinkDecorator {
    private final double MILK_PRICE = 5.0d;

    public Milk(Drink wrappedDrink) {
        super(wrappedDrink);
    }

    @Override
    public String getDescription() {
        return wrappedDrink.getDescription() + ", milk";
    }

    @Override
    public double getPrice() {
        return wrappedDrink.getPrice() + MILK_PRICE;
    }
}
