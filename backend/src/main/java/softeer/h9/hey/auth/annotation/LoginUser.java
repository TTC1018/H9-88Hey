package softeer.h9.hey.auth.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 스프링 Argument Resolver 가 자동으로 userId 값을 넣어줌.
 * ex)
 * @GetMapping
 * public int test(@LoginUser int userId) {
 *     return userId;
 * }
 */
@Target(ElementType.PARAMETER)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
