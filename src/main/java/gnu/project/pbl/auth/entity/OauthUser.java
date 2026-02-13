package gnu.project.pbl.auth.entity;

import gnu.project.pbl.auth.enumerated.SocialProvider;
import gnu.project.pbl.common.enumerated.UserRole;
import java.util.UUID;

public interface OauthUser {

    Long getId();

    OauthInfo getOauthInfo();

    UserRole getUserRole();

    default String getEmail() {
        return getOauthInfo().getEmail();
    }

    default String getName() {
        return getOauthInfo().getName();
    }

    default UUID getUuid() {
        return getOauthInfo().getUuid();
    }

    default SocialProvider getSocialProvider() {
        return getOauthInfo().getSocialProvider();
    }
}
