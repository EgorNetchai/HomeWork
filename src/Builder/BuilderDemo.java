package Builder;

public class BuilderDemo {
    public static void main(String[] args) {
        Pizza pizza = new Builder()
                .size("Large")
                .dough("Thin")
                .cheese(true)
                .pepperoni(false)
                .mushrooms(true)
                .build();

        System.out.println(pizza);
    }
}
