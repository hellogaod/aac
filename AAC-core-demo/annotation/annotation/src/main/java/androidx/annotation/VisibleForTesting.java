
package androidx.annotation;

import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;

/**
 * Denotes that the class, method or field has its visibility relaxed, so that it is more widely
 * visible than otherwise necessary to make code testable.
 * <p>
 * You can optionally specify what the visibility <b>should</b> have been if not for
 * testing; this allows tools to catch unintended access from within production
 * code.
 * <p>
 * Example:
 * <pre><code>
 *  &#64;VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
 *  public String printDiagnostics() { ... }
 * </code></pre>
 *
 * If not specified, the intended visibility is assumed to be private.
 */
@Retention(CLASS)
public @interface VisibleForTesting {
    /**
     * The visibility the annotated element would have if it did not need to be made visible for
     * testing.
     */
    @ProductionVisibility
    int otherwise() default PRIVATE;

    /**
     * The annotated element would have "private" visibility
     */
    int PRIVATE = 2; // Happens to be the same as Modifier.PRIVATE

    /**
     * The annotated element would have "package private" visibility
     */
    int PACKAGE_PRIVATE = 3;

    /**
     * The annotated element would have "protected" visibility
     */
    int PROTECTED = 4; // Happens to be the same as Modifier.PROTECTED

    /**
     * The annotated element should never be called from production code, only from tests.
     * <p>
     * This is equivalent to {@code @RestrictTo.Scope.TESTS}.
     */
    int NONE = 5;
}
