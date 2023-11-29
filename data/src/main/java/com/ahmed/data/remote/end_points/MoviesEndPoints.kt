package com.ahmed.data.remote.end_points

import com.ahmed.data.util.MainRemoteEndPointsParameters.MOVIE_NAME
import com.ahmed.domain.model.photo.Images
import com.ahmed.domain.model.photo.Photos
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesEndPoints {

    @GET("services/rest/?method=flickr.photos.search&api_key=c89ce21e3a9741d590b3cc232a5a18e6&format=json&nojsoncallback=1")
    suspend fun getPhotos(
        @Query(MOVIE_NAME) movie_name: String
    ): Images<Photos>

}