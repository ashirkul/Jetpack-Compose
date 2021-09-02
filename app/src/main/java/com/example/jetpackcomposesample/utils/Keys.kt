package com.example.flickdemo

data class Keys(var name: String, var page: Int){
    private val photosUrl : String =  "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=3e7cc266ae2b0e0d78e279ce8e361736&page=${page}&format=json&nojsoncallback=1&text=${name}"

    fun getPhotosUrl() : String{
        return photosUrl
    }
}
