package gnu.project.pbl.auth.entity;

import gnu.project.pbl.common.enumerated.UserRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Accessor {

    private final String socialId;
    private final Long userId;
    private final UserRole userRole;

    public static Accessor user(String socialId, Long userId, UserRole userROle) {
        return new Accessor(socialId, userId, userROle);
    }

    public boolean isUser() {
        return userRole == UserRole.USER;
    }

    public boolean isAdmin() {
        return userRole == UserRole.ADMIN;
    }
}
