package Adapter;

public class AdapterDemo {
    public static void main(String[] args) {
        ThirdPartyImageLibrary library = new ThirdPartyImageLibrary();

        ImageProcessor processor = new ImageProcessorAdapter(library);

        System.out.println("=== Start image processing ===");
        processor.loadImage("photo.jpg");
        processor.processImage();
        processor.saveImage();
        System.out.println("=== End of image processing ===");
    }
}
