package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;


/**
 * Transformation methods for {@link LiveData}.
 * <p>
 * These methods permit functional composition and delegation of {@link LiveData} instances. The
 * transformations are calculated lazily, and will run only when the returned {@link LiveData} is
 * observed. Lifecycle behavior is propagated from the input {@code source} {@link LiveData} to the
 * returned one.
 */
@SuppressWarnings("WeakerAccess")
public class Transformations {
    private Transformations() {
    }

    /**
     * Returns a {@code LiveData} mapped from the input {@code source} {@code LiveData} by applying
     * {@code mapFunction} to each value set on {@code source}.
     * <p>
     * This method is analogous to {@link io.reactivex.Observable#map}.
     * <p>
     * {@code transform} will be executed on the main thread.
     * <p>
     * Here is an example mapping a simple {@code User} struct in a {@code LiveData} to a
     * {@code LiveData} containing their full name as a {@code String}.
     *
     * <pre>
     * LiveData&lt;User&gt; userLiveData = ...;
     * LiveData&lt;String&gt; userFullNameLiveData =
     *     Transformations.map(
     *         userLiveData,
     *         user -> user.firstName + user.lastName);
     * });
     * </pre>
     *
     * @param source      the {@code LiveData} to map from
     * @param mapFunction a function to apply to each value set on {@code source} in order to set
     *                    it
     *                    on the output {@code LiveData}
     * @param <X>         the generic type parameter of {@code source}
     * @param <Y>         the generic type parameter of the returned {@code LiveData}
     * @return a LiveData mapped from {@code source} to type {@code <Y>} by applying
     * {@code mapFunction} to each value set.
     */
    @MainThread
    @NonNull
    public static <X, Y> LiveData<Y> map(
            @NonNull LiveData<X> source,
            @NonNull final Function<X, Y> mapFunction) {
        final MediatorLiveData<Y> result = new MediatorLiveData<>();
        result.addSource(source, new Observer<X>() {
            @Override
            public void onChanged(@Nullable X x) {
                result.setValue(mapFunction.apply(x));
            }
        });
        return result;
    }

    /**
     * Returns a {@code LiveData} mapped from the input {@code source} {@code LiveData} by applying
     * {@code switchMapFunction} to each value set on {@code source}.
     * <p>
     * The returned {@code LiveData} delegates to the most recent {@code LiveData} created by
     * calling {@code switchMapFunction} with the most recent value set to {@code source}, without
     * changing the reference. In this way, {@code switchMapFunction} can change the 'backing'
     * {@code LiveData} transparently to any observer registered to the {@code LiveData} returned
     * by {@code switchMap()}.
     * <p>
     * Note that when the backing {@code LiveData} is switched, no further values from the older
     * {@code LiveData} will be set to the output {@code LiveData}. In this way, the method is
     * analogous to {@link io.reactivex.Observable#switchMap}.
     * <p>
     * {@code switchMapFunction} will be executed on the main thread.
     * <p>
     * Here is an example class that holds a typed-in name of a user
     * {@code String} (such as from an {@code EditText}) in a {@link MutableLiveData} and
     * returns a {@code LiveData} containing a List of {@code User} objects for users that have
     * that name. It populates that {@code LiveData} by requerying a repository-pattern object
     * each time the typed name changes.
     * <p>
     * This {@code ViewModel} would permit the observing UI to update "live" as the user ID text
     * changes.
     *
     * <pre>
     * class UserViewModel extends AndroidViewModel {
     *     MutableLiveData&lt;String&gt; nameQueryLiveData = ...
     *
     *     LiveData&lt;List&lt;String&gt;&gt; getUsersWithNameLiveData() {
     *         return Transformations.switchMap(
     *             nameQueryLiveData,
     *                 name -> myDataSource.getUsersWithNameLiveData(name));
     *     }
     *
     *     void setNameQuery(String name) {
     *         this.nameQueryLiveData.setValue(name);
     *     }
     * }
     * </pre>
     *
     * @param source            the {@code LiveData} to map from
     * @param switchMapFunction a function to apply to each value set on {@code source} to create a
     *                          new delegate {@code LiveData} for the returned one
     * @param <X>               the generic type parameter of {@code source}
     * @param <Y>               the generic type parameter of the returned {@code LiveData}
     * @return a LiveData mapped from {@code source} to type {@code <Y>} by delegating
     * to the LiveData returned by applying {@code switchMapFunction} to each
     * value set
     */
    @MainThread
    @NonNull
    public static <X, Y> LiveData<Y> switchMap(
            @NonNull LiveData<X> source,
            @NonNull final Function<X, LiveData<Y>> switchMapFunction) {
        final MediatorLiveData<Y> result = new MediatorLiveData<>();

        result.addSource(source, new Observer<X>() {
            LiveData<Y> mSource;

            @Override
            public void onChanged(@Nullable X x) {
                LiveData<Y> newLiveData = switchMapFunction.apply(x);
                if (mSource == newLiveData) {
                    return;
                }
                if (mSource != null) {
                    result.removeSource(mSource);
                }
                mSource = newLiveData;
                if (mSource != null) {
                    result.addSource(mSource, new Observer<Y>() {
                        @Override
                        public void onChanged(@Nullable Y y) {
                            result.setValue(y);
                        }
                    });
                }
            }
        });

        return result;
    }

    /**
     * Creates a new {@link LiveData} object that does not emit a value until the source LiveData
     * value has been changed.  The value is considered changed if {@code equals()} yields
     * {@code false}.
     *
     * @param source the input {@link LiveData}
     * @param <X>    the generic type parameter of {@code source}
     * @return a new {@link LiveData} of type {@code X}
     */
    @MainThread
    @NonNull
    public static <X> LiveData<X> distinctUntilChanged(@NonNull LiveData<X> source) {
        final MediatorLiveData<X> outputLiveData = new MediatorLiveData<>();
        outputLiveData.addSource(source, new Observer<X>() {

            boolean mFirstTime = true;

            @Override
            public void onChanged(X currentValue) {
                final X previousValue = outputLiveData.getValue();
                if (mFirstTime
                        || (previousValue == null && currentValue != null)
                        || (previousValue != null && !previousValue.equals(currentValue))) {
                    mFirstTime = false;
                    outputLiveData.setValue(currentValue);
                }
            }
        });

        return outputLiveData;
    }
}
