package Strategy;

public class StrategyDemo {
    public static void main(String[] args) {
        Device device = new Device();

        device.setVideoStrategy(new TV("LG", 1578321895));
        device.playVideo("Clinic - S1:E3");

        device.setVideoStrategy(new Phone("Samsung", "F0:98:9D:1C:93:F6"));
        device.playVideo("Doctor Who? - S5:E5");

        device.setVideoStrategy(new Laptop("Lenovo", "Admin"));
        device.playVideo("Vikings - S2:E8");
    }
}
