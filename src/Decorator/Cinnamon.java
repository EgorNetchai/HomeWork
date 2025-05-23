package Decorator;

public class Cinnamon extends DrinkDecorator {
    private final double CINNAMON_PRICE = 10.0d;

    public Cinnamon(Drink wrappedDrink) {
        super(wrappedDrink);
    }

    @Override
    public String getDescription() {
        return wrappedDrink.getDescription() + ", cinnamon";
    }

    @Override
    public double getPrice() {
        return wrappedDrink.getPrice() + CINNAMON_PRICE;
    }
}
