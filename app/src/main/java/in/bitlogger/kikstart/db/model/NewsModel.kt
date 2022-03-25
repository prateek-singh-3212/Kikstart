package `in`.bitlogger.kikstart.db.model

data class NewsModel(
    val status: Int,
    val news: Array<News>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NewsModel

        if (status != other.status) return false
        if (!news.contentEquals(other.news)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = status
        result = 31 * result + news.contentHashCode()
        return result
    }
}

data class News(
    val date: String,
    val link: String,
    val position: Int,
    val snippet: String,
    val source: String,
    val thumbnail: String,
    val time: String,
    val title: String
)