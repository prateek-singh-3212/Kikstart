package `in`.bitlogger.kikstart.interfaces

data class CoroutineDataPassModel<T>(
    val isDataLoading: Boolean,
    val data: T?,
    val errorMessage: String?
)