package gnu.project.pbl.auth.jwt;

import static gnu.project.pbl.auth.constant.JwtConstants.TOKEN_TYPE;
import static gnu.project.pbl.auth.constant.JwtConstants.UUID;
import static gnu.project.pbl.auth.constant.JwtConstants.USER_ROLE;

import gnu.project.pbl.auth.constant.JwtConstants;
import gnu.project.pbl.auth.enumerated.TokenType;
import gnu.project.pbl.common.enumerated.UserRole;
import gnu.project.pbl.common.enumerated.UserRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;

    public String createAccessToken(
        final UUID uuid,
        final UserRole userRole) {
        return createToken(
            uuid,
            jwtProperties.getAccessTokenExpirationMillis(),
            TokenType.ACCESS_TOKEN,
            userRole
        );
    }

    // TODO: 추후 사용
    public String createRefreshToken(
        final UUID uuid,
        final UserRole userRole) {
        return createToken(
            uuid,
            jwtProperties.getAccessTokenExpirationMillis(),
            TokenType.REFRESH_TOKEN,
            userRole
        );
    }

    private String createToken(
        final UUID uuid,
        final long expirationMillis,
        final TokenType tokenType,
        final UserRole userRole
    ) {
        final Date now = new Date();
        final Date expiredDate = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
            .setSubject(String.valueOf(uuid))
            .claim(USER_ROLE, userRole)
            .setIssuedAt(now)
            .setExpiration(expiredDate)
            .claim(TOKEN_TYPE, tokenType.name())
            .signWith(jwtProperties.getSecretKey(), SignatureAlgorithm.HS256)
            .compact();
    }
}
