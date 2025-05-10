package Builder;

public class Builder {
    private String size = "Medium";
    private String dough = "Thin";
    private boolean cheese = false;
    private boolean pepperoni = false;
    private boolean mushrooms = false;

    public Builder setSize(String size) {
        this.size = size;
        return this;
    }

    public Builder setDough(String dough) {
        this.dough = dough;
        return this;
    }

    public Builder addCheese(boolean cheese) {
        this.cheese = cheese;
        return this;
    }

    public Builder addPepperoni(boolean pepperoni) {
        this.pepperoni = pepperoni;
        return this;
    }

    public Builder addMushrooms(boolean mushrooms) {
        this.mushrooms = mushrooms;
        return this;
    }

    public Pizza build() {
        return Pizza.createPizza(size, dough, cheese, pepperoni, mushrooms);
    }
}
