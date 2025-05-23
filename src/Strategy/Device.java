package Strategy;

public class Device {
    private VideoStrategy videoStrategy;

    public void setVideoStrategy(VideoStrategy videoStrategy) {
        this.videoStrategy = videoStrategy;
    }

    public void playVideo(String video) {
        if (videoStrategy == null) {
            throw new IllegalStateException("Video strategy is not set");
        }
        videoStrategy.play(video);
    }
}
