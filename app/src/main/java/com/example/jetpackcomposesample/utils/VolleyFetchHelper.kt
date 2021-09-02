package com.example.jetpackcomposesample

import org.json.JSONArray
import org.json.JSONObject

class VolleyFetchHelper {

    companion object {
        fun getPhotos(response: JSONObject): ArrayList<String> {

            val localDataSet = ArrayList<String>();

            val jsonArray: JSONArray = response.getJSONObject("photos").getJSONArray("photo")
            for (i in 0 until jsonArray.length() - 1) {
                val photo = jsonArray.getJSONObject(i)

                val farm = photo.getString("farm")
                val server = photo.getString("server")
                val id = photo.getString("id")
                val secret = photo.getString("secret")

                val photoUrl = "https://farm" + farm + ".static.flickr.com/" + server + "/" + id + "_" + secret + ".jpg"
//            val photoUrl = "https://farm${farm}.static.flickr.com/${server}/${id}_${secret}.jpg"
                localDataSet.add(photoUrl)
            }
            return localDataSet;
        }
    }
}