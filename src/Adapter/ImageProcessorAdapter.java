package Adapter;

public class ImageProcessorAdapter implements ImageProcessor {
    private final ThirdPartyImageLibrary thirdPartyImageLibrary;
    private String loadedImage;

    public ImageProcessorAdapter(ThirdPartyImageLibrary THIRD_PARTY_LIBRARY) {
        this.thirdPartyImageLibrary = THIRD_PARTY_LIBRARY;
    }

    @Override
    public void loadImage(String fileName) {
        System.out.println("ImageProcessorAdapter: Loading image via adapter...");
        this.loadedImage = thirdPartyImageLibrary.load(fileName);
    }

    @Override
    public void processImage() {
        if (loadedImage == null) {
            throw new IllegalStateException("Image not loaded!");
        }
        System.out.println("ImageProcessorAdapter: Image processing via adapter...");
        this.loadedImage = thirdPartyImageLibrary.applyFilter(loadedImage);
    }

    @Override
    public void saveImage() {
        if (loadedImage == null) {
            throw new IllegalStateException("Image not loaded or not processed!");
        }
        System.out.println("ImageProcessorAdapter: Saving image via adapter...");
        thirdPartyImageLibrary.export(loadedImage);
    }
}
