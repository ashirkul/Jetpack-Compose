package com.example.jetpackcomposesample

import android.content.Context
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.flickdemo.Keys
import com.example.jetpackcomposesample.`interface`.ResponseListener

class FlickrViewModel(private val context: Context) : ViewModel() {
    private var search: String = ""
    private var page: Int = 1
    var mMutableList = MutableLiveData<List<String>>()
    var mDataset : LiveData<List<String>> =  mMutableList

    private fun addRequest(listener: ResponseListener?, url: String?) {

        val queue = Volley.newRequestQueue(context)
        var dataSet = mutableListOf<String>()
        mMutableList.value?.let { dataSet.addAll(it) }
        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            Log.d("activity", "onResponse is executed on ->" + Thread.currentThread().name + " " + (Looper.myLooper() == Looper.getMainLooper()))
            dataSet.addAll(VolleyFetchHelper.getPhotos(response!!))
            mMutableList.value = dataSet
            listener?.onResponseReceived(dataSet)
        }) { error -> error.printStackTrace() }

        queue.add(request)
    }

    fun newRequest(listener: ResponseListener? = null, query: String) {
        page = 1
        search = query
        val url: String = Keys(search, page).getPhotosUrl()
        addRequest(listener, url)
    }

    fun fetchNextPage(listener: ResponseListener? = null) {
        page++
        val url: String = Keys(search, page).getPhotosUrl()
        addRequest(listener, url)
    }
}