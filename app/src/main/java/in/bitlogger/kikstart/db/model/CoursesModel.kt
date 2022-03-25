package `in`.bitlogger.kikstart.db.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoursesModel (
    var _id: String,
    var course_id: Int,
    var course_name: String,
    var rating: Float,
    var source: String,
    var Instructor: String,
    var desc: String,
    var courseVids: List<CoursesVideos>,
    var course_link: String
) : Parcelable

@Parcelize
data class CoursesVideos(
    var title: String,
    var thumbnail: String,
    var duration: String,
    var url: String
) : Parcelable