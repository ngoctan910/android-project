package ngoctan.domain.extension

sealed class Failure

class CustomFailure(var error: Throwable): Failure()

class None