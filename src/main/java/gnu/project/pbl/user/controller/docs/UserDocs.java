package gnu.project.pbl.user.controller.docs;

import gnu.project.pbl.auth.aop.Auth;
import gnu.project.pbl.auth.entity.Accessor;
import gnu.project.pbl.user.dto.request.UserRequest;
import gnu.project.pbl.user.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
    name = "User API",
    description = "일반 사용자(User) 정보 등록 및 조회 API"
)
public interface UserDocs {

    @Operation(
        summary = "회원 정보 등록 / 재가입",
        description = """
            소셜 로그인으로 생성된 사용자에 대해
            추가 정보를 등록하거나,
            탈퇴했던 계정을 다시 활성화합니다.
            """,
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "회원 등록 또는 재가입 성공",
                content = @Content(
                    schema = @Schema(implementation = UserResponse.class)
                )
            )
        }
    )
    @PostMapping
    ResponseEntity<UserResponse> signUp(
        @Parameter(hidden = true)
        @Auth Accessor accessor,

        @Parameter(description = "회원 등록 또는 재가입 시 입력 정보", required = true)
        @RequestBody UserRequest request
    );

    @Operation(
        summary = "내 정보 조회",
        description = "현재 로그인한 사용자의 정보를 조회합니다.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "조회 성공",
                content = @Content(
                    schema = @Schema(implementation = UserResponse.class)
                )
            )
        }
    )
    @GetMapping
    ResponseEntity<UserResponse> find(
        @Parameter(hidden = true)
        @Auth Accessor accessor
    );
}
