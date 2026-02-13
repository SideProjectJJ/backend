package gnu.project.pbl.auth.dto.request;


import gnu.project.pbl.auth.enumerated.SocialProvider;
import gnu.project.pbl.common.enumerated.UserRole;

public record OauthLoginRequest(
    String code,
    SocialProvider socialProvider,
    UserRole userRole
    // String state //naver 필드
) {

}
