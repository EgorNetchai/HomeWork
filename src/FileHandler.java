import java.io.*;

public class FileHandler {
    public void writeToFile(String filePath, String data, boolean append) throws FileException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            if(append) {
                writer.newLine();
            }
            writer.write(data);
            System.out.println("Data written successfully!");
        } catch (IOException e) {
            throw new FileException("Error while writing: " + filePath, e);
        }
    }

    public String readFromFile(String filePath) throws FileException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new FileException("Error while reading: " + filePath, e);
        }
        return content.toString();
    }
}
