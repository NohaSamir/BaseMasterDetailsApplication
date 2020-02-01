package com.example.basemasterdetailsapplication.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.basemasterdetailsapplication.domain.DataRepository
import com.example.basemasterdetailsapplication.domain.DataStatus
import com.example.basemasterdetailsapplication.domain.DummyData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListViewModel(private val repository: DataRepository) : ViewModel() {

    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _dataStatus: MutableLiveData<DataStatus> = MutableLiveData()

    val dataStatus :LiveData<DataStatus> = _dataStatus

    val list: LiveData<List<DummyData>> = repository.getList()

    init {
        refresh()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
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

    fun refresh()
    {
        coroutineScope.launch {
            repository.refreshList(_dataStatus)
        }
    }
}
