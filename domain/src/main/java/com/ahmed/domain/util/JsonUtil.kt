package com.ahmed.domain.util

import com.google.gson.Gson

fun <T> T.toJson(): String {
    return Gson().toJson(this)
}

inline fun <reified T : Any> String.fromJson(): T {
    return Gson().fromJson(this, T::class.java)
}

inline fun <reified T : Any> String.fromJsonArray(): List<T> {
    return Gson().fromJson(this, emptyArray<T>().javaClass).toList()
}