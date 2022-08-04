package androidx.room;

import java.lang.annotation.RetentionPolicy;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;

@Target(
        allowedTargets = {}
)
@Retention(AnnotationRetention.BINARY)
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@java.lang.annotation.Target({})
public @interface Index {
    String[] value();

    Index.Order[] orders() default {};

    String name() default "";

    boolean unique() default false;

    public static enum Order {
        ASC,
        DESC;
    }
}
