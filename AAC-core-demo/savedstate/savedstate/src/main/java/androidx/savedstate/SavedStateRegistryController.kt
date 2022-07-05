package androidx.savedstate

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.Lifecycle


/**
 * An API for [SavedStateRegistryOwner] implementations to control [SavedStateRegistry].
 *
 * `SavedStateRegistryOwner` should call [performRestore] to restore state of
 * [SavedStateRegistry] and [performSave] to gather SavedState from it.
 */
class SavedStateRegistryController private constructor(private val owner: SavedStateRegistryOwner) {

    /**
     * The [SavedStateRegistry] owned by this controller
     */
    val savedStateRegistry: SavedStateRegistry = SavedStateRegistry()

    private var attached = false

    /**
     * Perform the initial, one time attachment necessary to configure this
     * [SavedStateRegistry]. This must be called when the owner's [Lifecycle] is
     * [Lifecycle.State.INITIALIZED] and before you call [performRestore].
     *
     * Activity构造函数中调用附加功能
     */
    @MainThread
    fun performAttach() {
        val lifecycle = owner.lifecycle
        check(lifecycle.currentState == Lifecycle.State.INITIALIZED) {
            ("Restarter must be created only during owner's initialization stage")
        }
        lifecycle.addObserver(Recreator(owner))
        savedStateRegistry.performAttach(lifecycle)
        attached = true
    }

    /**
     * An interface for an owner of this [SavedStateRegistry] to restore saved state.
     *
     * 当执行Activity的onCreate方法时，执行该方法表示恢复上一次（横竖屏切换数据）
     *
     * @param savedState restored state
     */
    @MainThread
    fun performRestore(savedState: Bundle?) {
        // To support backward compatibility with libraries that do not explicitly
        // call performAttach(), we make sure that work is done here
        if (!attached) {
            performAttach()
        }
        val lifecycle = owner.lifecycle
        check(!lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            ("performRestore cannot be called when owner is ${lifecycle.currentState}")
        }
        savedStateRegistry.performRestore(savedState)
    }

    /**
     * An interface for an owner of this  [SavedStateRegistry]
     * to perform state saving, it will call all registered providers and
     * merge with unconsumed state.
     *
     * 横竖屏切换activity调用onSaveInstanceState方法执行存储任务
     *
     * @param outBundle Bundle in which to place a saved state
     */
    @MainThread
    fun performSave(outBundle: Bundle) {
        savedStateRegistry.performSave(outBundle)
    }

    companion object {
        /**
         * Creates a [SavedStateRegistryController].
         *
         * It should be called during construction time of [SavedStateRegistryOwner]
         */
        @JvmStatic
        fun create(owner: SavedStateRegistryOwner): SavedStateRegistryController {
            return SavedStateRegistryController(owner)
        }
    }
}