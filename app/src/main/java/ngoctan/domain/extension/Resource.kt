package ngoctan.domain.extension

sealed class Resource<T> {
    class Success<T>(val data: T): Resource<T>()
    class Error<T>(val errorMessage: String): Resource<T>()
}