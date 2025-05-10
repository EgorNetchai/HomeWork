package Builder;

public class BuilderDemo {
    public static void main(String[] args) {
        Pizza pizza = new Builder()
                .setSize("Large")
                .setDough("Thin")
                .addCheese(true)
                .addPepperoni(false)
                .addMushrooms(true)
                .build();

        System.out.println(pizza);
    }
}
