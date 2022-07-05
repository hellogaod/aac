
package androidx.lifecycle

import android.view.View

/**
 * Locates the [ViewModelStoreOwner] associated with this [View], if present.
 * This may be used to retain state associated with this view across configuration changes.
 */
public fun View.findViewTreeViewModelStoreOwner(): ViewModelStoreOwner? =
    ViewTreeViewModelStoreOwner.get(this)
