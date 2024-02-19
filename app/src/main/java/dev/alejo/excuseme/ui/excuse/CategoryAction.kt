package dev.alejo.excuseme.ui.excuse

sealed class CategoryAction {
    object Opened : CategoryAction()
    object Closed : CategoryAction()
    data class Selected(val categoryName: String) : CategoryAction()
}
