package dev.alejo.excuseme.ui.excuse

import dev.alejo.excuseme.data.ExcuseModel

sealed class UIState {
    object Loading : UIState()
    data class Success(val excuseData: ExcuseModel) : UIState()
    data class Error(val error: Throwable) : UIState()
}