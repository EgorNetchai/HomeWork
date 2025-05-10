package Builder;

public class Pizza {
    private final String SIZE;
    private final String DOUGH;
    private final boolean CHEESE;
    private final boolean PEPPERONI;
    private final boolean MUSHROOMS;

    private Pizza (String size, String dough, boolean cheese, boolean pepperoni, boolean mushrooms) {
        this.SIZE = size;
        this.DOUGH = dough;
        this.CHEESE = cheese;
        this.PEPPERONI = pepperoni;
        this.MUSHROOMS = mushrooms;
    }

    static Pizza createPizza(String size, String dough, boolean cheese, boolean pepperoni, boolean mushrooms) {
        return new Pizza(size, dough, cheese, pepperoni, mushrooms);
    }

    @Override
    public String toString() {
        return "Pizza -> {" +
                "size='" + SIZE + '\'' +
                ", dough='" + DOUGH + '\'' +
                ", cheese='" + CHEESE + '\'' +
                ", pepperoni='" + PEPPERONI + '\'' +
                ", mushrooms='" + MUSHROOMS + '\'' +
                '}';
    }
}
