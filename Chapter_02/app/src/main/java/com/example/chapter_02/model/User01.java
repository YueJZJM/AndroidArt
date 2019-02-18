package com.example.chapter_02.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User01 implements Parcelable {

    public int userId;
    public String userName;
    public boolean isMale;

    public User01(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }

    protected User01(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        isMale = in.readByte() != 0;
    }

    public static final Creator<User01> CREATOR = new Creator<User01>() {
        @Override
        public User01 createFromParcel(Parcel in) {
            return new User01(in);
        }

        @Override
        public User01[] newArray(int size) {
            return new User01[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeByte((byte) (isMale ? 1 : 0));
    }
}
