package gnu.project.pbl.auth.dto.response;

public record AuthTokenDto(
    String accessToken
) {

    public static AuthTokenDto of(String accessToken) {
        return new AuthTokenDto(accessToken);
    }
}
