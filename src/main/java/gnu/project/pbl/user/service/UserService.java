package gnu.project.pbl.user.service;

import static gnu.project.pbl.common.error.ErrorCode.CUSTOMER_DELETED_EXCEPTION;
import static gnu.project.pbl.common.error.ErrorCode.CUSTOMER_NOT_FOUND_EXCEPTION;

import gnu.project.pbl.auth.entity.Accessor;
import gnu.project.pbl.common.exception.BusinessException;
import gnu.project.pbl.user.dto.request.UserRequest;
import gnu.project.pbl.user.dto.response.UserResponse;
import gnu.project.pbl.user.entity.User;
import gnu.project.pbl.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserResponse findUser(final Accessor accessor) {

        final User user = findCustomerByUuid(accessor);

        if (!user.isActive()) {
            throw new BusinessException(CUSTOMER_DELETED_EXCEPTION);
        }
        return UserResponse.of(user);
    }


    public UserResponse signUp(
        final Accessor accessor,
        final UserRequest request
    ) {
        final User user = findCustomerByUuid(accessor);

        if (!user.isActive()) {
            user.reactivate();
        }

        user.signUp(
            request
        );

        return UserResponse.of(user);
    }

    private User findCustomerByUuid(Accessor accessor) {
        return userRepository.findByUuid(accessor.getUuid())
            .orElseThrow(() -> new BusinessException(CUSTOMER_NOT_FOUND_EXCEPTION));
    }


}
