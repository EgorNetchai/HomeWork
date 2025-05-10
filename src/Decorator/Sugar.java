package Decorator;

public class Sugar extends DrinkDecorator{
    public Sugar(Drink wrappedDrink) {
        super(wrappedDrink);
    }

    @Override
    public String getDescription() {
        return wrappedDrink.getDescription() + ", sugar";
    }

    @Override
    public double getPrice() {
        return wrappedDrink.getPrice() + 7.0d;
    }
}
