package dev.alejo.excuseme.ui.excuse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alejo.excuseme.data.local.ExcuseCategory
import dev.alejo.excuseme.domain.GetExcuseCategoryUseCase
import dev.alejo.excuseme.domain.GetExcuseUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExcuseViewModel @Inject constructor(
    private val getExcuseUseCase: GetExcuseUseCase,
    private val getExcuseCategoryUseCase: GetExcuseCategoryUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState
    private val _categories = MutableLiveData<List<ExcuseCategory>>()
    val categories: LiveData<List<ExcuseCategory>> = _categories
    private val _categoriesVisible = MutableLiveData<Boolean>()
    val categoriesVisible: LiveData<Boolean> = _categoriesVisible

    init {
        _uiState.value = UIState.Loading
        getExcuse()
        getCategories()
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

    fun onCategoriesAction(categoryAction: CategoryAction) {
        when (categoryAction) {
            is CategoryAction.Opened -> _categoriesVisible.value = true
            is CategoryAction.Closed -> _categoriesVisible.value = false
            is CategoryAction.Selected -> {
                _categoriesVisible.value = false
                // Set the category selected
            }
        }
    }

    fun onCategorySelected() { _categoriesVisible.value = false }

    private fun getCategories() {
        viewModelScope.launch {
            _categories.value = getExcuseCategoryUseCase()
        }
    }

}