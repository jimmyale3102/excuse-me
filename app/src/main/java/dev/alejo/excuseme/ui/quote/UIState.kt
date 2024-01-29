package dev.alejo.excuseme.ui.quote

sealed class UIState {
    object Loading : UIState()
    data class Success(val quote: String) : UIState()
    data class Error(val error: Throwable) : UIState()
}