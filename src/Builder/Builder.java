package Builder;

public class Builder {
    private String size = "Medium";
    private String dough = "Thin";
    private boolean cheese;
    private boolean pepperoni;
    private boolean mushrooms;

    public Builder size(String size) {
        this.size = size;
        return this;
    }

    public Builder dough(String dough) {
        this.dough = dough;
        return this;
    }

    public Builder cheese(boolean cheese) {
        this.cheese = cheese;
        return this;
    }

    public Builder pepperoni(boolean pepperoni) {
        this.pepperoni = pepperoni;
        return this;
    }

    public Builder mushrooms(boolean mushrooms) {
        this.mushrooms = mushrooms;
        return this;
    }

    public Pizza build() {
        return Pizza.createPizza(size, dough, cheese, pepperoni, mushrooms);
    }
}
