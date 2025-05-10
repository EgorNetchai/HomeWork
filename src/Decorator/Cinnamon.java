package Decorator;

public class Cinnamon extends DrinkDecorator{
    public Cinnamon(Drink wrappedDrink) {
        super(wrappedDrink);
    }

    @Override
    public String getDescription() {
        return wrappedDrink.getDescription() + ", cinnamon";
    }

    @Override
    public double getPrice() {
        return wrappedDrink.getPrice() + 10.0d;
    }
}
