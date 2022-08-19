/*
 * Copyright 2018 The Android Open Source Project
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

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

@SuppressLint("BanParcelableUsage")
final class FragmentManagerState implements Parcelable {
    ArrayList<String> mActive;
    ArrayList<String> mAdded;
    BackStackRecordState[] mBackStack;
    int mBackStackIndex;
    String mPrimaryNavActiveWho = null;
    ArrayList<String> mBackStackStateKeys = new ArrayList<>();
    ArrayList<BackStackState> mBackStackStates = new ArrayList<>();
    ArrayList<FragmentManager.LaunchedFragmentInfo> mLaunchedFragments;

    public FragmentManagerState() {
    }

    public FragmentManagerState(Parcel in) {
        mActive = in.createStringArrayList();
        mAdded = in.createStringArrayList();
        mBackStack = in.createTypedArray(BackStackRecordState.CREATOR);
        mBackStackIndex = in.readInt();
        mPrimaryNavActiveWho = in.readString();
        mBackStackStateKeys = in.createStringArrayList();
        mBackStackStates = in.createTypedArrayList(BackStackState.CREATOR);
        mLaunchedFragments = in.createTypedArrayList(FragmentManager.LaunchedFragmentInfo.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(mActive);
        dest.writeStringList(mAdded);
        dest.writeTypedArray(mBackStack, flags);
        dest.writeInt(mBackStackIndex);
        dest.writeString(mPrimaryNavActiveWho);
        dest.writeStringList(mBackStackStateKeys);
        dest.writeTypedList(mBackStackStates);
        dest.writeTypedList(mLaunchedFragments);
    }

    public static final Creator<FragmentManagerState> CREATOR
            = new Creator<FragmentManagerState>() {
        @Override
        public FragmentManagerState createFromParcel(Parcel in) {
            return new FragmentManagerState(in);
        }

        @Override
        public FragmentManagerState[] newArray(int size) {
            return new FragmentManagerState[size];
        }
    };
}
