package com.tn07.githubapp.data.remote.api

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Created by toannguyen
 * Jun 27, 2021 at 14:53
 */
class GsonUTCDateAdapter : JsonSerializer<Date>, JsonDeserializer<Date> {

    private val dateFormat: DateFormat
    private val locker = Any()

    init {
        // This is the format we need
        dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        // This is the key line which converts the date to UTC which cannot be accessed with the default serializer
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"))
    }

    override fun serialize(
        date: Date,
        type: Type,
        jsonSerializationContext: JsonSerializationContext
    ): JsonElement {
        synchronized(locker) {
            return JsonPrimitive(dateFormat.format(date))
        }
    }

    override fun deserialize(
        jsonElement: JsonElement,
        type: Type,
        jsonDeserializationContext: JsonDeserializationContext
    ): Date? {
        try {
            synchronized(locker) {
                return dateFormat.parse(jsonElement.asString)
            }
        } catch (e: ParseException) {
            throw JsonParseException(e)
        }
    }
}