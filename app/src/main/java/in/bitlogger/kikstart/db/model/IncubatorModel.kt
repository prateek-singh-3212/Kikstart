package `in`.bitlogger.kikstart.db.model

data class IncubatorModel(
    var _id: String,
    var name: String,
    var logo: String,
    var startups_funded: List<String>,
    var link: String,
    var content: String
)