package com.ahmed.data.local

import android.content.Context
import android.util.Log
import com.ahmed.data.data_source.local.LocalDataSource
import com.ahmed.domain.model.movie.Movies
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject


class LocalDataSourceImpl @Inject constructor(var context: Context) : LocalDataSource {

    override suspend fun getMovies(): Movies {

        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("movies.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (_: IOException) {

        }

        val listCountryType = object : TypeToken<Movies>() {}.type
        return Gson().fromJson(jsonString, listCountryType)

    }


}