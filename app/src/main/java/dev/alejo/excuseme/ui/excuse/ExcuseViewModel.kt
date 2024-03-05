package dev.alejo.excuseme.ui.excuse

import android.content.Context
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shuffle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alejo.excuseme.core.Constants.RANDOM_CATEGORY_ID
import dev.alejo.excuseme.data.local.ExcuseCategory
import dev.alejo.excuseme.domain.GetExcuseByCategoryUseCase
import dev.alejo.excuseme.domain.GetExcuseCategoryUseCase
import dev.alejo.excuseme.domain.GetExcuseUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExcuseViewModel @Inject constructor(
    private val getExcuseUseCase: GetExcuseUseCase,
    private val getExcuseCategoryUseCase: GetExcuseCategoryUseCase,
    private val getExcuseByCategoryUseCase: GetExcuseByCategoryUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState
    private val _categories = MutableLiveData<List<ExcuseCategory>>()
    val categories: LiveData<List<ExcuseCategory>> = _categories
    private val _categoriesVisible = MutableLiveData<Boolean>()
    val categoriesVisible: LiveData<Boolean> = _categoriesVisible
    private val _categorySelected = MutableLiveData(getCategoryRandom())
    val categorySelected: LiveData<ExcuseCategory> = _categorySelected

    init {
        getExcuse()
        getCategories()
    }

    fun onGetExcuse() { getExcuse() }

    fun onSendExcuse(context: Context, excuse: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, excuse)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

    private fun getExcuse() {
        _uiState.value = UIState.Loading
        viewModelScope.launch {
            val excuseData = if(_categorySelected.value!!.id == RANDOM_CATEGORY_ID) {
                getExcuseUseCase()
            } else {
                getExcuseByCategoryUseCase(_categorySelected.value!!.name)
            }
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
                _categorySelected.value = categoryAction.category
                if (categoryAction.category.id == RANDOM_CATEGORY_ID) {
                    getExcuse()
                } else {
                    getExcuseByCategory(categoryAction.category.name)
                }
            }
        }
    }

    private fun getExcuseByCategory(categoryName: String) {
        _uiState.value = UIState.Loading
        viewModelScope.launch {
            _uiState.value = getExcuseByCategoryUseCase(categoryName)?.let { excuse ->
                UIState.Success(excuse)
            } ?: UIState.Error(Throwable("Something went wrong"))
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            _categories.value = getExcuseCategoryUseCase()
        }
    }

    fun getCategoryRandom() = ExcuseCategory(
        id = RANDOM_CATEGORY_ID,
        name = "Random",
        icon = Icons.Default.Shuffle
    )

}