package com.elfefe.coffeejoin.models.credential

import android.os.Parcel
import android.os.Parcelable
import com.elfefe.coffeejoin.models.credential.AccessRule
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Credentials : Parcelable {
    @SerializedName("accessRules")
    @Expose
    var accessRules: List<AccessRule?>? = null

    @SerializedName("redirection")
    @Expose
    var redirection: String? = null

    protected constructor(`in`: Parcel) {
        `in`.readList(accessRules, AccessRule::class.java.classLoader)
        redirection =
            `in`.readValue(String::class.java.classLoader) as String?
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param accessRules
     * @param redirection
     */
    constructor(
        accessRules: List<AccessRule?>?,
        redirection: String?
    ) : super() {
        this.accessRules = accessRules
        this.redirection = redirection
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeList(accessRules)
        dest.writeValue(redirection)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<Credentials> =
            object : Parcelable.Creator<Credentials?> {
                override fun createFromParcel(`in`: Parcel): Credentials? {
                    return Credentials(`in`)
                }

                override fun newArray(size: Int): Array<Credentials?> {
                    return arrayOfNulls(size)
                }
            }
    }
}