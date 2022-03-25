package `in`.bitlogger.kikstart.db.model

data class SigninModel(
    var email: String,
    var password: String,
)

data class SignupModel(
    var email: String,
    var username: String,
    var dob: String,
    var gender: String,
    var password: String
)

data class LoginResponseModel(
    var success: Boolean,
    var message: String
)