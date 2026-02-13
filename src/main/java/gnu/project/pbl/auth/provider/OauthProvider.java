package gnu.project.pbl.auth.provider;

import gnu.project.pbl.auth.enumerated.SocialProvider;
import gnu.project.pbl.auth.userinfo.OauthUserInfo;

public interface OauthProvider {

    SocialProvider getProvider();

    //naver 추후 parmeter state 추가
    String getAccessToken(String code);

    OauthUserInfo getUserInfo(String accessToken);


}
