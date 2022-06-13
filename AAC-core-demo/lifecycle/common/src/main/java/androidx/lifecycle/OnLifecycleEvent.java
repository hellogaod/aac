package androidx.lifecycle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that can be used to mark methods on {@link LifecycleObserver} implementations that
 * should be invoked to handle lifecycle events.
 *
 * @deprecated This annotation required the usage of code generation or reflection, which should
 * be avoided. Use {@link DefaultLifecycleObserver} or
 * {@link LifecycleEventObserver} instead.
 */
@SuppressWarnings("unused")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OnLifecycleEvent {
    Lifecycle.Event value();
}
