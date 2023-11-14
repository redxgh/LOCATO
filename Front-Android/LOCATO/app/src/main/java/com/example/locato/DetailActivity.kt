package com.example.locato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var titleTxt: TextView
    private lateinit var addressTxt: TextView
    private lateinit var bedTxt: TextView
    private lateinit var bathTxt: TextView
    private lateinit var wifiTxt: TextView
    private lateinit var descriptionTxt: TextView
    private lateinit var item: ItemsDomaine
    private lateinit var pic: ImageView
    private lateinit var priceTxt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initView()
        setVariable()
    }

    private fun setVariable() {
        item = intent.getSerializableExtra("object") as ItemsDomaine
        titleTxt.text = item.titleTxt
        addressTxt.text = item.address
        bedTxt.text = "${item.bed} Bed"
        bathTxt.text = "${item.bath} Bath"
        descriptionTxt.text = item.description

        if (item.wifi==true) {
            wifiTxt.text = "Wifi"
        } else {
            wifiTxt.text = "No Wifi"
        }

        val drawableResourceId = resources.getIdentifier(item.pic, "drawable", packageName)

        Glide.with(this)
            .load(drawableResourceId)
            .into(pic)
    }

    private fun initView() {
        titleTxt = findViewById(R.id.titleTxt)
        addressTxt = findViewById(R.id.addressTxt)
        bedTxt = findViewById(R.id.bedTxt)
        bathTxt = findViewById(R.id.bathTxt)
        wifiTxt = findViewById(R.id.wifiTxt)
        descriptionTxt = findViewById(R.id.descriptionTxt)
        pic = findViewById(R.id.pic)
    }
}
