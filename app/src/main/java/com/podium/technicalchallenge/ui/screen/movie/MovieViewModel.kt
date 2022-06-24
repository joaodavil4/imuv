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
    private fun getFilter(): String {
        return filter + if(selectedGenre.value?.isNotEmpty() == true) ", genre: \"${selectedGenre.value}\"" else ""
    }

    private val _genres = MutableLiveData<List<String>>()
    val genres: LiveData<List<String>>
        get() = _genres

    val selectedFilter = MutableLiveData<String>()
    val selectedGenre = MutableLiveData<String>()
    val selectedEntity = MutableLiveData<MovieEntity>()

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
            selectedFilter.value = "Top 5"
            top5query
        } else {
            selectedFilter.value = "All"
            resetQuery
        }
        refresh()
    }

    fun filterGenre(genre: String){
        if(selectedGenre.value != genre){
            selectedGenre.value = genre
            refresh()
        } else {
            selectedGenre.value = ""
        }
    }

    fun loadDetails(){
        getMovie()
    }

    private fun getMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                Repo.getInstance().getMovie(selectedEntity.value?.id.toString())
            } catch (e: Exception) {
                Result.Error(e)
            }
            when (result) {
                is Result.Success<MovieEntity?> -> {
                    val entity = selectedEntity.value
                    entity?.cast = result.data?.cast!!
                    entity?.director = result.data.director

                    selectedEntity.postValue(entity)
                }
                else -> {
                    Log.e(TAG, "movies= " + result)
                }
            }
            isRefreshing.postValue(false)
        }
    }


    private fun getMovies() {

        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                Repo.getInstance().getMovies(getFilter())
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

    fun getGenres() {

        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                Repo.getInstance().getGenres()
            } catch (e: Exception) {
                Result.Error(e)
            }
            when (result) {
                is Result.Success<List<String>?> -> {
                    _genres.postValue(result.data)
                    Log.d(TAG, "genres= " + result.data)
                }
                else -> {
                    Log.e(TAG, "genres= " + result)
                }
            }
            isRefreshing.postValue(false)
        }
    }
}
