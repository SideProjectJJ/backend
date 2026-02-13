package gnu.project.pbl.auth.entity;

import gnu.project.pbl.auth.enumerated.SocialProvider;
import gnu.project.pbl.common.enumerated.UserRole;

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

    default String getUuid() {
        return getOauthInfo().getUuid();
    }

    default SocialProvider getSocialProvider() {
        return getOauthInfo().getSocialProvider();
    }
}
