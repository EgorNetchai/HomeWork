package Decorator;

public class Sugar extends DrinkDecorator {
    private final double SUGAR_PRICE = 7.0d;

    public Sugar(Drink wrappedDrink) {
        super(wrappedDrink);
    }

    @Override
    public String getDescription() {
        return wrappedDrink.getDescription() + ", sugar";
    }

    @Override
    public double getPrice() {
        return wrappedDrink.getPrice() + SUGAR_PRICE;
    }
}
