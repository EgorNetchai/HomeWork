package Strategy;

public class Laptop implements VideoStrategy {
    private String mark;
    private String laptopName;

    Laptop(String mark, String laptopName) {
        this.mark = mark;
        this.laptopName = laptopName;
    }

    @Override
    public void play(String video) {
        System.out.println("Video: " + video + " plays on laptop -> " + mark + "; laptop name: " + laptopName);
    }
}
