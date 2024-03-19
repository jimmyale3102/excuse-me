package dev.alejo.excuseme.ui.excuse

sealed class ExcuseOption {
    object Refresh : ExcuseOption()
    object Send : ExcuseOption()
}
