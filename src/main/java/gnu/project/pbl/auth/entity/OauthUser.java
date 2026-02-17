package gnu.project.pbl.auth.entity;

import gnu.project.pbl.auth.enumerated.SocialProvider;
import gnu.project.pbl.common.enumerated.UserRole;
import java.util.UUID;

public interface OauthUser {
    UUID getUuid();
    Long getId();

    OauthInfo getOauthInfo();

    UserRole getUserRole();

    default String getEmail() {
        return getOauthInfo().getEmail();
    }

    default String getName() {
        return getOauthInfo().getName();
    }

    default String getSocialId() {
        return getOauthInfo().getSocialId();
    }

    default SocialProvider getSocialProvider() {
        return getOauthInfo().getSocialProvider();
    }
}
