/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.fragment.app;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

/**
 * Factory for constructing instances of {@link SpecialEffectsController} on demand.
 */
interface SpecialEffectsControllerFactory {
    /**
     * Create a new {@link SpecialEffectsController} for the given container.
     *
     * @param container The ViewGroup the created SpecialEffectsController should control.
     * @return a new instance of SpecialEffectsController
     */
    @NonNull
    SpecialEffectsController createController(@NonNull ViewGroup container);
}
