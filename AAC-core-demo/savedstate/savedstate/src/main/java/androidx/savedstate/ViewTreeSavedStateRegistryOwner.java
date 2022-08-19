package androidx.savedstate;

import android.view.View;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.Nullable;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

public final class ViewTreeSavedStateRegistryOwner {
    @JvmName(
            name = "set"
    )
    public static final void set(@NotNull View $this$setViewTreeSavedStateRegistryOwner, @Nullable SavedStateRegistryOwner owner) {
        Intrinsics.checkNotNullParameter($this$setViewTreeSavedStateRegistryOwner, "<this>");
        $this$setViewTreeSavedStateRegistryOwner.setTag(R.id.view_tree_saved_state_registry_owner, owner);
    }

    @JvmName(
            name = "get"
    )
    @Nullable
    public static final SavedStateRegistryOwner get(@NotNull View $this$findViewTreeSavedStateRegistryOwner) {
        Intrinsics.checkNotNullParameter($this$findViewTreeSavedStateRegistryOwner, "<this>");
        return (SavedStateRegistryOwner) SequencesKt.firstOrNull(
                SequencesKt.mapNotNull(
                        SequencesKt.generateSequence($this$findViewTreeSavedStateRegistryOwner, null), null
                )
        );
    }
}
