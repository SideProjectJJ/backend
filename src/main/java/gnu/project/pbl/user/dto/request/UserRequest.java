package gnu.project.pbl.user.dto.request;

import gnu.project.pbl.common.enumerated.Gender;

public record UserRequest(
    Gender gender,
    String phoneNumber,
    Short weight,
    Short height

) {

}
