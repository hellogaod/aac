package androidx.room;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;

@Target(
        allowedTargets = {AnnotationTarget.CLASS}
)
@Retention(AnnotationRetention.BINARY)
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@java.lang.annotation.Target({ElementType.TYPE})
public @interface AutoMigration {
    int from();

    int to();

    Class spec() default Object.class;
}
