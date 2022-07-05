package androidx.savedstate

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import java.lang.Exception

class Recreator(
    private val owner: SavedStateRegistryOwner
) : LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event != Lifecycle.Event.ON_CREATE) {
            throw AssertionError("Next event must be ON_CREATE")
        }
        source.lifecycle.removeObserver(this)
        val bundle: Bundle =
            owner.savedStateRegistry.consumeRestoredStateForKey(COMPONENT_KEY) ?: return
        val classes: MutableList<String> = bundle.getStringArrayList(CLASSES_KEY)
            ?: throw IllegalStateException(
                "Bundle with restored state for the component " +
                        "\"$COMPONENT_KEY\" must contain list of strings by the key " +
                        "\"$CLASSES_KEY\""
            )

        for (className: String in classes) {
            reflectiveNew(className)
        }

    }

    private fun reflectiveNew(className: String) {
        val clazz: Class<out SavedStateRegistry.AutoRecreated> =
            try {
                Class.forName(className, false, Recreator::class.java.classLoader)
                    .asSubclass(SavedStateRegistry.AutoRecreated::class.java)
            } catch (e: ClassNotFoundException) {
                throw RuntimeException("Class $className wasn't found", e)
            }

        val constructor =
            try {
                clazz.getDeclaredConstructor()
            } catch (e: NoSuchMethodException) {
                throw IllegalStateException(
                    "Class ${clazz.simpleName} must have " +
                            "default constructor in order to be automatically recreated", e
                )
            }

        constructor.isAccessible = true
        val newInstance: SavedStateRegistry.AutoRecreated =
            try {
                constructor.newInstance()
            } catch (e: Exception) {
                throw RuntimeException("Failed to instantiate $className", e)
            }

        newInstance.onRecreated(owner)
    }

    internal class SavedStateProvider(registry: SavedStateRegistry) :
        SavedStateRegistry.SavedStateProvider {

        private val classes: MutableSet<String> = mutableSetOf()

        init {
            registry.registerSavedStateProvider(COMPONENT_KEY, this)
        }

        override fun saveState(): Bundle {
            return Bundle().apply {
                putStringArrayList(CLASSES_KEY, ArrayList(classes))
            }
        }

        fun add(className: String) {
            classes.add(className)
        }
    }

    companion object {
        const val CLASSES_KEY = "classes_to_restore"
        const val COMPONENT_KEY = "androidx.savedstate.Restarter"
    }
}