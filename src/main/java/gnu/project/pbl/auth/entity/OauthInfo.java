package gnu.project.pbl.auth.entity;

import gnu.project.pbl.auth.enumerated.SocialProvider;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OauthInfo {

    private String email;

    private String name;

    private UUID uuid;

    @Enumerated(EnumType.STRING)
    private SocialProvider socialProvider;

    public static OauthInfo of(
        final String email,
        final String name,
        final UUID uuid,
        final SocialProvider provider
    ) {
        return new OauthInfo(email, name, uuid, provider);
    }
}