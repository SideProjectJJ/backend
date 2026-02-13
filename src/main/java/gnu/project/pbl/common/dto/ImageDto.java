package gnu.project.pbl.common.dto;

public record ImageDto(
    String key
) {

    public static ImageDto from(String key) {
        return new ImageDto(key);
    }
}
