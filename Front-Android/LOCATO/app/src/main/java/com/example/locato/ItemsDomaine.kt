package com.example.locato

import java.io.Serializable
import java.time.LocalTime

class ItemsDomaine(
    val id:Int,
    val titleTxt: String,
    val address: String,
    val description: String,
    val bed: Int,
    val bath: Int,
    val price: Int,
    val pic: String,
    val category: String,
    val best: Int,
    val surface: Double,
    val typeAd:String,
    val time: LocalTime



) : Serializable {
}