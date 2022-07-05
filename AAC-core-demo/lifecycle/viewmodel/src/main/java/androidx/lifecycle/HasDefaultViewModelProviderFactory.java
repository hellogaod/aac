package androidx.lifecycle;


import androidx.annotation.NonNull;
import androidx.lifecycle.viewmodel.CreationExtras;

/**
 * Interface that marks a {@link ViewModelStoreOwner} as having a default
 * {@link androidx.lifecycle.ViewModelProvider.Factory} for use with
 * {@link androidx.lifecycle.ViewModelProvider#ViewModelProvider(ViewModelStoreOwner)}.
 */
public interface HasDefaultViewModelProviderFactory {
    /**
     * Returns the default {@link androidx.lifecycle.ViewModelProvider.Factory} that should be
     * used when no custom {@code Factory} is provided to the
     * {@link androidx.lifecycle.ViewModelProvider} constructors.
     *
     * @return a {@code ViewModelProvider.Factory}
     */
    @NonNull
    ViewModelProvider.Factory getDefaultViewModelProviderFactory();

    /**
     * Returns the default {@link CreationExtras} that should be passed into the
     * {@link ViewModelProvider.Factory#create(Class, CreationExtras)} when no overriding
     * {@link CreationExtras} were passed to the
     * {@link androidx.lifecycle.ViewModelProvider} constructors.
     */
    @NonNull
    default CreationExtras getDefaultViewModelCreationExtras() {
        return CreationExtras.Empty.INSTANCE;
    }
}
