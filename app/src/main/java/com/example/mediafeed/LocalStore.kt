package com.example.mediafeed

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocalStore(context: Context) {

    private val prefs = context.getSharedPreferences("mediafeed_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    private fun likeKey(type: MediaType, id: String) = "like_${type.name}_$id"
    private fun commentsKey(type: MediaType, id: String) = "comments_${type.name}_$id"

    // =======================================================================
    // TODO 6a: Implement isLiked() — read like state
    // Hint: Use prefs.getBoolean(likeKey(type, id), false)
    //       The second argument (false) is the default value (not liked)
    // =======================================================================
    fun isLiked(type: MediaType, id: String): Boolean {
        TODO("Read like state from SharedPreferences")
    }

    // =======================================================================
    // TODO 6b: Implement toggleLike() — toggle like state and save
    // Hint:
    //   1. Get the new value: !isLiked(type, id)
    //   2. Save it: prefs.edit().putBoolean(likeKey(type, id), newValue).apply()
    //   3. Return the new value
    // =======================================================================
    fun toggleLike(type: MediaType, id: String): Boolean {
        TODO("Toggle like state and save to SharedPreferences")
    }

    // =======================================================================
    // TODO 6c: Implement getComments() — read the comment list
    // Hint:
    //   1. Read JSON string: prefs.getString(commentsKey(type, id), "[]")
    //   2. Deserialize with Gson: val token = object : TypeToken<MutableList<String>>() {}.type
    //   3. Return: gson.fromJson(json, token)
    // =======================================================================
    fun getComments(type: MediaType, id: String): MutableList<String> {
        TODO("Read JSON from SharedPreferences and deserialize into a comment list")
    }

    // =======================================================================
    // TODO 6d: Implement addComment() — add a comment and save
    // Hint:
    //   1. Call getComments() to get the existing list
    //   2. list.add(comment) to append the new comment
    //   3. Serialize with gson.toJson(list)
    //   4. Save: prefs.edit().putString(commentsKey(type, id), json).apply()
    // =======================================================================
    fun addComment(type: MediaType, id: String, comment: String) {
        TODO("Add comment to the list, serialize, and save to SharedPreferences")
    }

    // =======================================================================
    // TODO 6e: Implement commentsCount() — return the number of comments
    // Hint: Call getComments() and return .size
    // =======================================================================
    fun commentsCount(type: MediaType, id: String): Int {
        TODO("Return the comment count")
    }
}