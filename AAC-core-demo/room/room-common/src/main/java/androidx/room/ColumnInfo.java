package androidx.room;

import androidx.annotation.RequiresApi;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;
import org.jetbrains.annotations.NotNull;

@Target(
        allowedTargets = {AnnotationTarget.FIELD, AnnotationTarget.FUNCTION}
)
@Retention(AnnotationRetention.BINARY)
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@java.lang.annotation.Target({ElementType.FIELD, ElementType.METHOD})
public @interface ColumnInfo {
    @NotNull
    ColumnInfo.Companion Companion = ColumnInfo.Companion.$$INSTANCE;
    @NotNull
    String INHERIT_FIELD_NAME = "[field-name]";
    int UNDEFINED = 1;
    int TEXT = 2;
    int INTEGER = 3;
    int REAL = 4;
    int BLOB = 5;
    int UNSPECIFIED = 1;
    int BINARY = 2;
    int NOCASE = 3;
    int RTRIM = 4;
    int LOCALIZED = 5;
    int UNICODE = 6;
    @NotNull
    String VALUE_UNSPECIFIED = "[value-unspecified]";

    String name() default "[field-name]";

    @ColumnInfo.SQLiteTypeAffinity
    int typeAffinity() default 1;

    boolean index() default false;

    @ColumnInfo.Collate
    int collate() default 1;

    String defaultValue() default "[value-unspecified]";

    @Retention(AnnotationRetention.BINARY)
    @java.lang.annotation.Retention(RetentionPolicy.CLASS)
    public @interface SQLiteTypeAffinity {
    }

    @Retention(AnnotationRetention.BINARY)
    @java.lang.annotation.Retention(RetentionPolicy.CLASS)
    @RequiresApi(21)
    public @interface Collate {
    }

    public static final class Companion {
        @NotNull
        public static final String INHERIT_FIELD_NAME = "[field-name]";
        public static final int UNDEFINED = 1;
        public static final int TEXT = 2;
        public static final int INTEGER = 3;
        public static final int REAL = 4;
        public static final int BLOB = 5;
        public static final int UNSPECIFIED = 1;
        public static final int BINARY = 2;
        public static final int NOCASE = 3;
        public static final int RTRIM = 4;
        @RequiresApi(21)
        public static final int LOCALIZED = 5;
        @RequiresApi(21)
        public static final int UNICODE = 6;
        @NotNull
        public static final String VALUE_UNSPECIFIED = "[value-unspecified]";
        // $FF: synthetic field
        static final ColumnInfo.Companion $$INSTANCE;

        private Companion() {
        }

        static {
            ColumnInfo.Companion var0 = new ColumnInfo.Companion();
            $$INSTANCE = var0;
        }
    }
}
