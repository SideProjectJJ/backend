package gnu.project.pbl.auth.service;

import static gnu.project.pbl.common.error.ErrorCode.AUTH_USER_NOT_FOUND;

import gnu.project.pbl.auth.dto.request.OauthLoginRequest;
import gnu.project.pbl.auth.dto.response.AuthTokenDto;
import gnu.project.pbl.auth.entity.Accessor;
import gnu.project.pbl.auth.entity.OauthUser;
import gnu.project.pbl.auth.enumerated.SocialProvider;
import gnu.project.pbl.auth.factory.OauthUserFactory;
import gnu.project.pbl.auth.jwt.JwtProvider;
import gnu.project.pbl.auth.provider.OauthProvider;
import gnu.project.pbl.auth.provider.OauthProviders;
import gnu.project.pbl.auth.userinfo.OauthUserInfo;
import gnu.project.pbl.common.enumerated.UserRole;
import gnu.project.pbl.common.exception.AuthException;
import gnu.project.pbl.user.entity.User;
import gnu.project.pbl.user.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OauthService {

    private final OauthProviders oauthProviders;
    private final OauthUserFactory oauthUserFactory;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    public AuthTokenDto login(final OauthLoginRequest request) {
        final OauthProvider provider = oauthProviders.getProvider(request.socialProvider());
        final String accessToken = provider.getAccessToken(request.code());
        final OauthUserInfo userInfo = provider.getUserInfo(accessToken);
        final OauthUser user = oauthUserFactory.findOrCreateUser(
            userInfo,
            provider.getProvider(),
            request.userRole()
        );
        return AuthTokenDto.of(
            jwtProvider.createAccessToken(
                user.getUuid(),
                user.getUserRole()
            )
        );
    }


    public Accessor getCurrentAccessor(final UUID uuid, final UserRole userRole) {

        if (!isUserExists(uuid, userRole)) {
            throw new AuthException(AUTH_USER_NOT_FOUND);
        }

        return Accessor.user(uuid, userRole);
    }

    private boolean isUserExists(UUID uuid, UserRole userRole) {
        return switch (userRole) {
            case USER -> userRepository.existsByUuid(uuid);
            default -> false;
        };
    }

}
