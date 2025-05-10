package Decorator;

public abstract class DrinkDecorator implements Drink{
    protected Drink wrappedDrink;

    public DrinkDecorator(Drink wrappedDrink) {
        this.wrappedDrink = wrappedDrink;
    }

    @Override
    public String getDescription() {
        return wrappedDrink.getDescription();
    }

    @Override
    public double getPrice() {
        return wrappedDrink.getPrice();
    }
}
