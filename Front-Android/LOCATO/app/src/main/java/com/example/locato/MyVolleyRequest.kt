package com.example.locato

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import multipartFiles.MultipartRequest
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class MyVolleyRequest private constructor(private val context: Context) {
    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: MyVolleyRequest? = null

        fun getInstance(context: Context): MyVolleyRequest {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: MyVolleyRequest(context).also {
                    INSTANCE = it
                }
            }
        }
    }

    fun getRequest(url: String, callback: (List<ItemsDomaine>?) -> Unit) {
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                // Handle successful response
                val adsList = parseResponse(response)
                callback(adsList)
            },
            { error ->
                // Handle error
                callback(null)
                Log.e(
                    "MyVolleyRequest",
                    "Error: ${error.networkResponse?.statusCode}, ${error.localizedMessage}"
                )
            }
        )

        requestQueue.add(jsonArrayRequest)
    }

    private fun parseResponse(response: JSONArray): List<ItemsDomaine> {
        val itemsList = mutableListOf<ItemsDomaine>()

        for (i in 0 until response.length()) {
            val adObject: JSONObject = response.getJSONObject(i)
            val item = createItemFromJsonObject(adObject)
            itemsList.add(item)
        }

        return itemsList
    }

    private fun createItemFromJsonObject(adObject: JSONObject): ItemsDomaine {
        val id = adObject.optString("id")
        val title = adObject.optString("title")
        val description = adObject.optString("description")
        val price = adObject.optDouble("price")
        val timeStamp = adObject.optString("timeStamp")

        val accomodationObject = adObject.optJSONObject("accomodation")
        val accomodation =
            if (accomodationObject != null) createAccomodationFromJsonObject(accomodationObject) else null
        val gender = adObject.optInt("gender", -1)

        return ItemsDomaine(id, title, description, price, timeStamp, accomodation, gender)
    }

    private fun createAccomodationFromJsonObject(accomodationObject: JSONObject): ItemsDomaine.Accomodation {
        val location = accomodationObject.optString("location")
        val surface = accomodationObject.optDouble("surface")
        val rooms = accomodationObject.optInt("rooms")
        val bathrooms = accomodationObject.optInt("bathrooms")
        val best = accomodationObject.optInt("best")

        val imagesArray = accomodationObject.optJSONArray("images")
        val imagesList = parseJSONArray(imagesArray)

        val type = accomodationObject.optString("type")

        val categoryObject = accomodationObject.optJSONObject("category")
        val category = createCategoryFromJsonObject(categoryObject)

        val categoriesObject = accomodationObject.optJSONObject("categories")
        val categories = createCategoryFromJsonObject(categoriesObject)

        return ItemsDomaine.Accomodation(
            location,
            surface,
            rooms,
            bathrooms,
            best,
            imagesList,
            type,
            category,
            categories
        )
    }

    private fun createCategoryFromJsonObject(categoryObject: JSONObject?): ItemsDomaine.Category {
        val id = categoryObject?.optString("id")
        val name = categoryObject?.optString("name")
        val image = categoryObject?.optString("image")

        return ItemsDomaine.Category(id, name, image)
    }

    private fun parseJSONArray(jsonArray: JSONArray?): List<String> {
        val result = mutableListOf<String>()
        if (jsonArray != null) {
            for (i in 0 until jsonArray.length()) {
                result.add(jsonArray.optString(i))
            }
        }
        return result
    }


    //delete
    fun deleteAdById(adId: String, callback: (String?) -> Unit) {

        //val ip :String = Resources.getSystem().getString(R.string.ip)
        val url = "http://192.168.0.141:8081/deleteAd?id=$adId"

        val deleteRequest = object : StringRequest(
            Method.DELETE, url,
            { response ->
                // Handle successful response
                callback(response)
            },
            { error ->
                // Handle error
                callback(null)
                Log.e(
                    "MyVolleyRequest",
                    "Error: ${error.networkResponse?.statusCode}, ${error.localizedMessage}"
                )
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                // Add any headers needed for the request
                return headers
            }
        }

        requestQueue.add(deleteRequest)
    }

}

