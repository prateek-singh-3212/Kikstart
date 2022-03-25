package `in`.bitlogger.kikstart.db.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SchemesModel (
    var _id: String,
    var scheme_no: Int,
    var title: String,
    var content: String,
    var image: String
) : Parcelable