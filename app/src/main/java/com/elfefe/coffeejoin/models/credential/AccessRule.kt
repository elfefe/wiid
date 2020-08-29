package com.elfefe.coffeejoin.models.credential

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AccessRule : Parcelable {
    @SerializedName("method")
    @Expose
    var method: String? = null

    @SerializedName("path")
    @Expose
    var path: String? = null

    protected constructor(`in`: Parcel) {
        method =
            `in`.readValue(String::class.java.classLoader) as String?
        path = `in`.readValue(String::class.java.classLoader) as String?
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param path
     * @param method
     */
    constructor(method: String?, path: String?) : super() {
        this.method = method
        this.path = path
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(method)
        dest.writeValue(path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<AccessRule> = object : Parcelable.Creator<AccessRule?> {
            override fun createFromParcel(`in`: Parcel): AccessRule? {
                return AccessRule(`in`)
            }

            override fun newArray(size: Int): Array<AccessRule?> {
                return arrayOfNulls(size)
            }
        }
    }
}