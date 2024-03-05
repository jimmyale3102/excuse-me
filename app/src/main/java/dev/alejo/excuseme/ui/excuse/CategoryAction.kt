package dev.alejo.excuseme.ui.excuse

import dev.alejo.excuseme.data.local.ExcuseCategory

sealed class CategoryAction {
    object Opened : CategoryAction()
    object Closed : CategoryAction()
    data class Selected(val category: ExcuseCategory) : CategoryAction()
}
