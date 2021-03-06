/**
 * Copyright (c) 2016-present, RxJava Contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */
package io.reactivex.subscribers;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Wraps another Subscriber and ensures all onXXX methods conform the protocol
 * (except the requirement for serialized access).
 *
 * @param <T> the value type
 */
public final class SafeSubscriber<T> implements FlowableSubscriber<T>, Subscription {
    /** The actual Subscriber. */
    final Subscriber<? super T> downstream;
    /** The subscription. */
    Subscription upstream;
    /** Indicates a terminal state. */
    boolean done;

    /**
     * Constructs a SafeSubscriber by wrapping the given actual Subscriber.
     * @param downstream the actual Subscriber to wrap, not null (not validated)
     */
    public SafeSubscriber(Subscriber<? super T> downstream) {
        this.downstream = downstream;
    }

    @Override
    public void onSubscribe(Subscription s) {
        if (SubscriptionHelper.validate(this.upstream, s)) {
            this.upstream = s;
            try {
                downstream.onSubscribe(this);
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                done = true;
                // can't call onError because the actual's state may be corrupt at this point
                try {
                    s.cancel();
                } catch (Throwable e1) {
                    Exceptions.throwIfFatal(e1);
                    RxJavaPlugins.onError(new CompositeException(e, e1));
                    return;
                }
                RxJavaPlugins.onError(e);
            }
        }
    }

    @Override
    public void onNext(T t) {
        if (done) {
            return;
        }
        if (upstream == null) {
            onNextNoSubscription();
            return;
        }

        if (t == null) {
            Throwable ex = new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
            try {
                upstream.cancel();
            } catch (Throwable e1) {
                Exceptions.throwIfFatal(e1);
                onError(new CompositeException(ex, e1));
                return;
            }
            onError(ex);
            return;
        }

        try {
            downstream.onNext(t);
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            try {
                upstream.cancel();
            } catch (Throwable e1) {
                Exceptions.throwIfFatal(e1);
                onError(new CompositeException(e, e1));
                return;
            }
            onError(e);
        }
    }

    void onNextNoSubscription() {
        done = true;
        Throwable ex = new NullPointerException("Subscription not set!");

        try {
            downstream.onSubscribe(EmptySubscription.INSTANCE);
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            // can't call onError because the actual's state may be corrupt at this point
            RxJavaPlugins.onError(new CompositeException(ex, e));
            return;
        }
        try {
            downstream.onError(ex);
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            // if onError failed, all that's left is to report the error to plugins
            RxJavaPlugins.onError(new CompositeException(ex, e));
        }
    }

    @Override
    public void onError(Throwable t) {
        if (done) {
            RxJavaPlugins.onError(t);
            return;
        }
        done = true;

        if (upstream == null) {
            Throwable npe = new NullPointerException("Subscription not set!");

            try {
                downstream.onSubscribe(EmptySubscription.INSTANCE);
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                // can't call onError because the actual's state may be corrupt at this point
                RxJavaPlugins.onError(new CompositeException(t, npe, e));
                return;
            }
            try {
                downstream.onError(new CompositeException(t, npe));
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                // if onError failed, all that's left is to report the error to plugins
                RxJavaPlugins.onError(new CompositeException(t, npe, e));
            }
            return;
        }

        if (t == null) {
            t = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }

        try {
            downstream.onError(t);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);

            RxJavaPlugins.onError(new CompositeException(t, ex));
        }
    }

    @Override
    public void onComplete() {
        if (done) {
            return;
        }
        done = true;

        if (upstream == null) {
            onCompleteNoSubscription();
            return;
        }

        try {
            downstream.onComplete();
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            RxJavaPlugins.onError(e);
        }
    }

    void onCompleteNoSubscription() {

        Throwable ex = new NullPointerException("Subscription not set!");

        try {
            downstream.onSubscribe(EmptySubscription.INSTANCE);
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            // can't call onError because the actual's state may be corrupt at this point
            RxJavaPlugins.onError(new CompositeException(ex, e));
            return;
        }
        try {
            downstream.onError(ex);
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            // if onError failed, all that's left is to report the error to plugins
            RxJavaPlugins.onError(new CompositeException(ex, e));
        }
    }

    @Override
    public void request(long n) {
        try {
            upstream.request(n);
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            try {
                upstream.cancel();
            } catch (Throwable e1) {
                Exceptions.throwIfFatal(e1);
                RxJavaPlugins.onError(new CompositeException(e, e1));
                return;
            }
            RxJavaPlugins.onError(e);
        }
    }

    @Override
    public void cancel() {
        try {
            upstream.cancel();
        } catch (Throwable e1) {
            Exceptions.throwIfFatal(e1);
            RxJavaPlugins.onError(e1);
        }
    }
}
