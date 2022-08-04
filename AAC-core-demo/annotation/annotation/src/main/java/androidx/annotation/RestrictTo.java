package androidx.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.Deprecated;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.MustBeDocumented;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;

@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
@Target(
        allowedTargets = {AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.FIELD, AnnotationTarget.FILE}
)
@java.lang.annotation.Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PACKAGE})
@Documented
@java.lang.annotation.Retention(RetentionPolicy.CLASS)

public @interface RestrictTo {
    RestrictTo.Scope[] value();


    public static enum Scope {
        LIBRARY,
        LIBRARY_GROUP,
        LIBRARY_GROUP_PREFIX,
        /** @deprecated */
        GROUP_ID,
        TESTS,
        SUBCLASSES;
    }
}
