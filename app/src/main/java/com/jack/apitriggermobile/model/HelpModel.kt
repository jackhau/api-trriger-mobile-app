package com.jack.apitriggermobile.model

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize

/**
 * Created by jackhau on 25/07/2021.
 */

@Parcelize
data class HelpModel (var message: String? = null): Parcelable {

    companion object {
        fun deserialize(json: String): HelpModel {
            return Gson().fromJson(json, HelpModel::class.java)
        }
    }
}

