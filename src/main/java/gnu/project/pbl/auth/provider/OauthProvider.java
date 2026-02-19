package gnu.project.pbl.auth.provider;

import gnu.project.pbl.auth.enumerated.SocialProvider;
import gnu.project.pbl.auth.userinfo.OauthUserInfo;

public interface OauthProvider {

    SocialProvider getProvider();

    OauthUserInfo getUserInfo(String accessToken);


}
