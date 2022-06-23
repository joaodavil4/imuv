package com.podium.technicalchallenge.ui.screen.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.podium.technicalchallenge.Repo
import com.podium.technicalchallenge.Result
import com.podium.technicalchallenge.entity.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    val TAG = "DemoViewModel"

    var isRefreshing = MutableLiveData(false)

    private val resetQuery = "limit: 10"
    private var filter = resetQuery
    fun setFilter(newFilter: String){
        filter = newFilter
    }


    private val _top5Filter = MutableLiveData<Boolean>()
    val top5Filter: LiveData<Boolean>
        get() = _top5Filter

    private val _items = MutableLiveData<List<MovieEntity>>(listOf())
    val items: LiveData<List<MovieEntity>>
        get() = _items

    fun refresh(){
        isRefreshing.value = true
        getMovies()
    }

    private val top5query = "limit: 5, orderBy: \"popularity\""
    fun filterTop5(){
        _top5Filter.value = _top5Filter.value != true
        filter = if(_top5Filter.value == true){
            top5query
        } else {
            resetQuery
        }
        refresh()
    }


    private fun getMovies() {

        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                Repo.getInstance().getMovies(filter)
            } catch (e: Exception) {
                Result.Error(e)
            }
            when (result) {
                is Result.Success<List<MovieEntity>?> -> {
                    _items.postValue(result.data)
                    Log.d(TAG, "movies= " + result.data)
                }
                else -> {
                    Log.e(TAG, "movies= " + result)
                }
            }
            isRefreshing.postValue(false)
        }
    }
}
