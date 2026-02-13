package gnu.project.pbl.auth.entity;

import gnu.project.pbl.common.enumerated.UserRole;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Accessor {

    private final UUID uuid;
    private final UserRole userRole;

    public static Accessor user(UUID uuid, UserRole userROle) {
        return new Accessor(uuid, userROle);
    }

    public boolean isUser() {
        return userRole == UserRole.USER;
    }

    public boolean isAdmin() {
        return userRole == UserRole.ADMIN;
    }
}
