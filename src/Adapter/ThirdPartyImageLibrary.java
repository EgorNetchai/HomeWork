package Adapter;

public class ThirdPartyImageLibrary {
    public String load(String imageFile) {
        System.out.println("ThirdPartyImageLibrary: Image uploaded: " + imageFile);
        return imageFile;
    }

    public String applyFilter(String imageFile) {
        System.out.println("ThirdPartyImageLibrary: Filter applied to: " + imageFile);
        return "filtered_" + imageFile;
    }

    public void export(String processedImageFile) {
        System.out.println("ThirdPartyImageLibrary: Exported image: " + processedImageFile);
    }
}
