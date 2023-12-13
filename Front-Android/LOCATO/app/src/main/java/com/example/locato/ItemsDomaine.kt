package com.example.locato

import java.io.Serializable
import java.time.LocalTime

data class ItemsDomaine(
    val id: String,
    val title: String,
    val description: String,
    val price: Double,
    val timeStamp: String?,
    val accomodation: Accomodation?,
    val gender: Int?,
    val userId : String?
) : Serializable {
    data class Accomodation(
        val location: String?,
        val surface: Double?,
        val rooms: Int?,
        val bathrooms: Int?,
        val best: Int?,
        val images: List<String>?,
        val type: String?,
        val category: Category?,
        val categories: Category?
    ) : Serializable
    @Transient
    val nonSerializableProperty: Any? = null

    data class Category(
        val id: String?,
        val name: String?,
        val image: String?
    ):Serializable
}