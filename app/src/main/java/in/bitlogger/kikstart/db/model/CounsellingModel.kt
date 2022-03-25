package `in`.bitlogger.kikstart.db.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CounsellingModel(
    var data: List<Counselling>,
    var message: String
) : Parcelable

@Parcelize
data class Counselling(
    var cons_no: Int,
    var contact: String,
    var experience: String,
    var expertise: List<String>,
    var img: String,
    var name: String,
    var qualification: String,
    var rating: Float
) : Parcelable