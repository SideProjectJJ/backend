package gnu.project.pbl.user.controller;

import gnu.project.pbl.auth.aop.Auth;
import gnu.project.pbl.auth.entity.Accessor;
import gnu.project.pbl.user.controller.docs.UserDocs;
import gnu.project.pbl.user.dto.request.UserRequest;
import gnu.project.pbl.user.dto.response.UserResponse;
import gnu.project.pbl.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController implements UserDocs{

    private final UserService userService;

    @Override
    @PostMapping
    public ResponseEntity<UserResponse> signUp(
            @Auth final Accessor accessor,
            @RequestBody @Valid final UserRequest request
    ) {
        return ResponseEntity.ok(userService.signUp(accessor, request));
    }

    @Override
    @GetMapping
    public ResponseEntity<UserResponse> find(
            @Auth final Accessor accessor
    ) {
        return ResponseEntity.ok(userService.findUser(accessor));
    }
}