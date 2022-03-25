package `in`.bitlogger.kikstart.utils

import android.content.Context
import android.util.DisplayMetrics




object Constants {
    const val PREFERENCE_NAME = "KIKSTART_PREFERENCE"
    const val PREFERENCE_DEFAULT_STRING = "NULL"
    const val PREFERENCE_DEFAULT_INT = -1
    const val PREFERENCE_DEFAULT_BOOLEAN = false
    const val PREFERENCE_DEFAULT_FLOAT = 0.0f

    const val YOUTUBE_API_KEY="AIzaSyAwXKG-yJXJrhze-lnJBlIWAP7-LP33t5Q"

    const val LOGIN_STATUS = "LOGIN_STATUS"
    const val USER_ID= "USER_ID"

    const val USERS = "users"

    const val NULL = "null"

    const val EMAIL = "email"
    const val GENDER = "gender"
    const val NAME = "name"
    const val PHONE = "phone"
    const val UID = "uid"

    const val COURSE_ID = "cid"
    const val COURSE_LIST = "clist"
    const val SCHEME_LIST = "slist"

    fun getPercentage(percent: Int, total: Int): Int = (percent * total) / 100

    fun calculateNoOfColumns(
        context: Context,
        columnWidthDp: Float
    ): Int { // For example columnWidthdp=180
        val displayMetrics: DisplayMetrics = context.getResources().getDisplayMetrics()
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return (screenWidthDp / columnWidthDp + 0.5).toInt()
    }
}