package com.elfefe.coffeejoin.models.consumervalidation

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ConsumerValidation : Parcelable {
    @SerializedName("validationUrl")
    @Expose
    var validationUrl: String? = null

    @SerializedName("consumerKey")
    @Expose
    var consumerKey: String? = null

    @SerializedName("state")
    @Expose
    var state: String? = null

    protected constructor(`in`: Parcel) {
        validationUrl =
            `in`.readValue(String::class.java.classLoader) as String?
        consumerKey =
            `in`.readValue(String::class.java.classLoader) as String?
        state =
            `in`.readValue(String::class.java.classLoader) as String?
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param validationUrl
     * @param state
     * @param consumerKey
     */
    constructor(
        validationUrl: String?,
        consumerKey: String?,
        state: String?
    ) : super() {
        this.validationUrl = validationUrl
        this.consumerKey = consumerKey
        this.state = state
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(validationUrl)
        dest.writeValue(consumerKey)
        dest.writeValue(state)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<ConsumerValidation> =
            object : Parcelable.Creator<ConsumerValidation?> {
                override fun createFromParcel(`in`: Parcel): ConsumerValidation? {
                    return ConsumerValidation(`in`)
                }

                override fun newArray(size: Int): Array<ConsumerValidation?> {
                    return arrayOfNulls(size)
                }
            }
    }
}