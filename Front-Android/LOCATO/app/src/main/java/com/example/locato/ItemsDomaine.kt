package com.example.locato

import java.io.Serializable

class ItemsDomaine(
    val titleTxt: String,
    val address: String,
    val description: String,
    val bed: Int,
    val bath: Int,
    val price: Int,
    val pic: String,
    val wifi: Boolean



) : Serializable {
}