package androidx.activity;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;

/**
 * Base class for activities that enables composition of higher level components.
 * <p>
 * Rather than all functionality being built directly into this class, only the minimal set of
 * lower level building blocks are included. Higher level components can then be used as needed
 * without enforcing a deep Activity class hierarchy or strong coupling between components.
 */
public class ComponentActivity extends Activity implements LifecycleOwner {

    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReportFragment.injectIfNeededIn(this);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}
