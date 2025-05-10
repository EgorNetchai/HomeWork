package Strategy;

public class TV implements VideoStrategy {
    private String mark;
    private int id;

    TV(String mark, int id) {
        this.mark = mark;
        this.id = id;
    }

    @Override
    public void play(String video) {
        System.out.println("Video: " + video + " plays on -> " + mark + " TV; id: " + id);
    }
}
