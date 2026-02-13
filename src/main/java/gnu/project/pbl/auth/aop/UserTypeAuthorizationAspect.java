package gnu.project.pbl.auth.aop;

import static gnu.project.pbl.common.error.ErrorCode.AUTH_FORBIDDEN;

import gnu.project.pbl.auth.entity.Accessor;
import gnu.project.pbl.common.exception.AuthException;
import java.util.Arrays;
import java.util.function.Predicate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserTypeAuthorizationAspect {


    @Around("@annotation(gnu.project.pbl.auth.aop.OnlyAdmin)")
    public Object authorizeAdmin(ProceedingJoinPoint joinPoint) throws Throwable {
        return authorize(joinPoint, Accessor::isAdmin);
    }

    @Around("@annotation(gnu.project.pbl.auth.aop.OnlyUser)")
    public Object authorizeOwner(ProceedingJoinPoint joinPoint) throws Throwable {
        return authorize(joinPoint, Accessor::isUser);
    }

    private Object authorize(
        ProceedingJoinPoint joinPoint,
        Predicate<Accessor> condition
    ) throws Throwable {
        Arrays.stream(joinPoint.getArgs())
            .filter(Accessor.class::isInstance)
            .map(Accessor.class::cast)
            .filter(condition)
            .findFirst()
            .orElseThrow(() -> new AuthException(AUTH_FORBIDDEN));

        return joinPoint.proceed();
    }
}
