package com.tn07.githubapp.data.remote.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

/**
 * Created by toannguyen
 * Jun 27, 2021 at 17:53
 */
class GsonUTCDateAdapterTest {
    private lateinit var dateTime: Date
    private lateinit var dateTimeString: String
    private lateinit var gson: Gson

    @Before
    fun setUp() {
        dateTime = Calendar.getInstance()
            .apply {
                timeZone = TimeZone.getTimeZone("UTC")
                set(Calendar.MILLISECOND, 0)
                set(2020, 4, 29, 6, 41, 23)
            }
            .time
        dateTimeString = "2020-05-29T06:41:23Z"

        gson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, GsonUTCDateAdapter())
            .create()
    }

    @Test
    fun deserialization_success_dataModel() {
        val expectedDate = dateTime
        val json = "{\"dateTime\": \"$dateTimeString\"}"

        val dataModel = gson.fromJson(json, DataModel::class.java)

        Assert.assertEquals(expectedDate, dataModel.dateTime)
    }

    @Test
    fun deserialization_success_dataModel_nullDateField() {
        val expectedDate: Date? = null
        val json = "{ }"

        val dataModel = gson.fromJson(json, DataModel::class.java)

        Assert.assertEquals(expectedDate, dataModel.dateTime)
    }

    @Test
    fun deserialization_success_singleDate() {
        val expectedDate = dateTime
        val json = "\"$dateTimeString\""

        val actualDate = gson.fromJson(json, Date::class.java)

        Assert.assertEquals(expectedDate, actualDate)
    }

    @Test(expected = JsonParseException::class)
    fun deserialization_error_invalidDateString() {
        val json = "{\"dateTime\": \"2020-05-29T06:41:23\"}"

        gson.fromJson(json, DataModel::class.java)
    }

    @Test
    fun serialization_success_dataModel() {
        val expectedJson = "{\"dateTime\":\"$dateTimeString\"}"
        val dataModel = DataModel(dateTime)
        val actualJson = gson.toJson(dataModel)

        Assert.assertEquals(expectedJson, actualJson)
    }

    @Test
    fun serialization_success_dataModel_nullDateField() {
        val expectedJson = "{}"
        val dataModel = DataModel(null)
        val actualJson = gson.toJson(dataModel)

        Assert.assertEquals(expectedJson, actualJson)
    }

    @Test
    fun serialization_success_singleDate() {
        val expectedJson = "\"$dateTimeString\""
        val actualJson = gson.toJson(dateTime)

        Assert.assertEquals(expectedJson, actualJson)
    }

    @Test
    fun serialization_success_singleDate_nullValue() {
        val expectedJson = "null"
        val date: Date? = null
        Assert.assertEquals(expectedJson, gson.toJson(date))
    }

    private data class DataModel(val dateTime: Date?)
}