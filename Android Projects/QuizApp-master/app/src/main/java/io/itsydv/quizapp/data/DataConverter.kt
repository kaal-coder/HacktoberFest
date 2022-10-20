package io.itsydv.quizapp.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.itsydv.quizapp.models.Option

// Converters for Room Database
// Room does not support List, so we need to convert it to String and vice versa

class DataConverter {

    // Converting List of Options to String
    @TypeConverter
    fun fromOptionList(optionList: ArrayList<Option>): String {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Option>>() {}.type
        return gson.toJson(optionList, type)
    }

    // Converting String to List of Options
    @TypeConverter
    fun toOptionList(optionListString: String): ArrayList<Option> {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<Option>>() {}.type
        return gson.fromJson(optionListString, type)
    }

    // Converting List to String
    @TypeConverter
    fun fromStringList(stringList: ArrayList<String>?): String? {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<String>?>() {}.type
        return gson.toJson(stringList, type)
    }

    // Converting String to List
    @TypeConverter
    fun toStringList(listString: String?): ArrayList<String>? {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<String>?>() {}.type
        return gson.fromJson(listString, type)
    }
}