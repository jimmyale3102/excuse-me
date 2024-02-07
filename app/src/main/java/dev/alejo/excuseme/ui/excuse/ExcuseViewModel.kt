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

    private val _excuse = MutableLiveData<String>()
    val excuse: LiveData<String> = _excuse

    /*
    init {
        getExcuse()
    }
    */

    fun onGetExcuse() { getExcuse() }

    private fun getExcuse() {
        viewModelScope.launch {
            _excuse.value = getExcuseUseCase()
        }
    }
}