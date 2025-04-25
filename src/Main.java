public class Main {
    public static void main(String[] args) {
        FileHandler file = new FileHandler();
        try {
            file.writeToFile("src/example.txt", "\nFourth line", true);
            String content = file.readFromFile("src/example.txt");
            System.out.println(content);
        } catch (FileException e) {
            System.err.println(e.getMessage());
        }
    }
}