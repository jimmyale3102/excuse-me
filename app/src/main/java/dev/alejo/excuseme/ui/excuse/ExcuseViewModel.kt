package dev.alejo.excuseme.ui.excuse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alejo.excuseme.domain.GetExcuseUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExcuseViewModel @Inject constructor(
    private val getExcuseUseCase: GetExcuseUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState
    private val _categoriesVisible = MutableLiveData<Boolean>()
    val categoriesVisible: LiveData<Boolean> = _categoriesVisible

    init {
        _uiState.value = UIState.Loading
        getExcuse()
    }

    fun onGetExcuse() { getExcuse() }

    private fun getExcuse() {
        viewModelScope.launch {
            val excuseData = getExcuseUseCase()
            _uiState.value = excuseData?.let {
                UIState.Success(it)
            } ?: UIState.Error(Throwable("Something went wrong"))
        }
    }

    fun onCategoriesOpened() { _categoriesVisible.value = true }

    fun onCategorySelected() { _categoriesVisible.value = false }

}