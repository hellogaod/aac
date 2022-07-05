package androidx.savedstate

import android.view.View

/**
 * Set the [SavedStateRegistryOwner] responsible for managing the saved state for this [View]
 * Calls to [get] from this view or descendants will return `owner`.
 *
 * This is is automatically set for you in the common cases of using fragments or
 * ComponentActivity.
 *
 *
 * This should only be called by constructs such as activities or fragments that manage
 * a view tree and their saved state through a [SavedStateRegistryOwner]. Callers
 * should only set a [SavedStateRegistryOwner] that will be *stable.* The
 * associated [SavedStateRegistry] should be cleared if the view tree is removed and is
 * not guaranteed to later become reattached to a window.
 *
 * @param owner The [SavedStateRegistryOwner] responsible for managing the
 * saved state for the given view
 */
@JvmName("set")
fun View.setViewTreeSavedStateRegistryOwner(owner: SavedStateRegistryOwner?) {
    setTag(R.id.view_tree_saved_state_registry_owner, owner)
}

/**
 * Retrieve the [SavedStateRegistryOwner] responsible for managing the saved state
 * for this [View].
 * This may be used to save or restore the state associated with the view.
 *
 * The returned [SavedStateRegistryOwner] is managing all the Views within the Fragment
 * or Activity this [View] is added to.

 * @return The [SavedStateRegistryOwner] responsible for managing the saved state for
 * this view and/or some subset of its ancestors
 */
@JvmName("get")
fun View.findViewTreeSavedStateRegistryOwner(): SavedStateRegistryOwner? {
    return generateSequence(this) { view ->
        view.parent as? View
    }.mapNotNull { view ->
        view.getTag(R.id.view_tree_saved_state_registry_owner) as? SavedStateRegistryOwner
    }.firstOrNull()
}