package Decorator;

public class DecoratorDemo {
    public static void main(String[] args) {
        Drink coffee = new Coffee();
        System.out.println(coffee.getDescription() + ": $" + coffee.getPrice());

        Drink coffeeWithSugar = new Sugar(coffee);
        System.out.println(coffeeWithSugar.getDescription() + ": $" + coffeeWithSugar.getPrice());

        Drink coffeeWithMilk = new Milk(coffee);
        System.out.println(coffeeWithMilk.getDescription() + ": $" + coffeeWithMilk.getPrice());

        Drink coffeeWithCinnamon = new Cinnamon(coffeeWithSugar);
        System.out.println(coffeeWithCinnamon.getDescription() + ": $" + coffeeWithCinnamon.getPrice());

    }
}
