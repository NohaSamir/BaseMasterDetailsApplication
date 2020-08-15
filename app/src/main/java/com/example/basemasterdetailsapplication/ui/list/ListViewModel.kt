package com.example.basemasterdetailsapplication.ui.list

import androidx.lifecycle.*
import com.example.basemasterdetailsapplication.data.source.network.models.ResultWrapper
import com.example.basemasterdetailsapplication.domain.DataRepository
import com.example.basemasterdetailsapplication.domain.DummyData
import kotlinx.coroutines.launch

class ListViewModel(private val repository: DataRepository) : ViewModel() {

    private val _List: MutableLiveData<ResultWrapper<List<DummyData>>> = MutableLiveData()

    val networkList: LiveData<ResultWrapper<List<DummyData>>> = _List

    val list: LiveData<List<DummyData>> = repository.getList()

    val loading = MutableLiveData(false)

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            loading.postValue(true)
            _List.postValue(repository.refreshList())
            loading.postValue(false)
        }

    }


    /**
     * Factory for constructing ListViewModel with parameter
     */
    class Factory(private val repository: DataRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ListViewModel(repository) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
