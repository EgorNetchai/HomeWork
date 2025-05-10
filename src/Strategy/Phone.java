package Strategy;

public class Phone implements VideoStrategy {
    private String mark;
    private String MACAddress;

    Phone(String mark, String MACAddress) {
        this.mark = mark;
        this.MACAddress = MACAddress;
    }

    @Override
    public void play(String video) {
        System.out.println("Video: " + video + " plays on -> " + mark + " phone; MAC address: " + MACAddress);
    }
}
