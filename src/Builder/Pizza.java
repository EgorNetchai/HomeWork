package Builder;

public class Pizza {
    private final String size;
    private final String dough;
    private final boolean cheese;
    private final boolean pepperoni;
    private final boolean mushrooms;

    private Pizza(String size, String dough, boolean cheese, boolean pepperoni, boolean mushrooms) {
        this.size = size;
        this.dough = dough;
        this.cheese = cheese;
        this.pepperoni = pepperoni;
        this.mushrooms = mushrooms;
    }

    static Pizza createPizza(String size, String dough, boolean cheese, boolean pepperoni, boolean mushrooms) {
        return new Pizza(size, dough, cheese, pepperoni, mushrooms);
    }

    @Override
    public String toString() {
        return "Pizza -> {" +
                "size='" + size + '\'' +
                ", dough='" + dough + '\'' +
                ", cheese='" + cheese + '\'' +
                ", pepperoni='" + pepperoni + '\'' +
                ", mushrooms='" + mushrooms + '\'' +
                '}';
    }
}
