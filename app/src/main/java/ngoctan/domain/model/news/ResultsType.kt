package ngoctan.domain.model.news

sealed class ResultsType(val type: Int) {
    data object NativeAd: ResultsType(1)
    data object ResultItem: ResultsType(0)
}