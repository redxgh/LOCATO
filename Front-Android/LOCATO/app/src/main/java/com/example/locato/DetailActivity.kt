package com.example.locato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var titleTxt: TextView
    private lateinit var addressTxt: TextView
    private lateinit var bedTxt: TextView
    private lateinit var bathTxt: TextView
    private lateinit var surfaceTxt: TextView
    private lateinit var descriptionTxt: TextView
    private lateinit var item: ItemsDomaine
    private lateinit var pic: ImageView
    private lateinit var priceTxt: TextView

    private val formatter = DecimalFormat("##,##,##,##,##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initView()
        setVariable()
    }

    private fun setVariable() {
        item = intent.getSerializableExtra("object") as ItemsDomaine
        titleTxt.text = item.title
        addressTxt.text = item.accomodation?.location
        bedTxt.text = "${item.accomodation?.rooms} Bed"
        bathTxt.text = "${item.accomodation?.bathrooms} Bath"
        surfaceTxt.text = "${item.accomodation?.surface} m²"
        descriptionTxt.text = item.description
        priceTxt.text = "${formatter.format(item.price)} DT"

        if (item.accomodation?.images?.isNotEmpty() == true) {
            Glide.with(this)
                .load(item.accomodation!!.images?.get(0))
                .into(pic)
        }
    }

    private fun initView() {
        titleTxt = findViewById(R.id.titleTxt)
        addressTxt = findViewById(R.id.adress) // Adjusted to match the XML layout
        bedTxt = findViewById(R.id.bedTxt)
        bathTxt = findViewById(R.id.bathTxt)
        surfaceTxt = findViewById(R.id.wifiTxt)
        descriptionTxt = findViewById(R.id.descriptionTxt)
        pic = findViewById(R.id.pic)
    }
}
