package my.example.annotation;

import java.lang.annotation.*;

/**
 * Annotation denoting a queued example that is using a constrained resource exclusively, e.g. applicative tenant
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Queued {
}
