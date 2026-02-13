package gnu.project.pbl.user.dto.response;

import gnu.project.pbl.common.enumerated.Gender;
import gnu.project.pbl.common.enumerated.UserRole;
import gnu.project.pbl.user.entity.User;
import java.util.UUID;

public record UserResponse(
    UUID uuid,
    Gender gender,
    Short weight,
    Short height,
    String email,
    String phoneNumber,
    boolean isDeleted,
    UserRole userRole
) {
    public static UserResponse of(User user){
        return new UserResponse(
            user.getUuid(),
            user.getGender(),
            user.getWeight(),
            user.getHeight(),
            user.getEmail(),
            user.getPhoneNumber(),
            user.getIsDeleted(),
            user.getUserRole()
        );
    }
}
