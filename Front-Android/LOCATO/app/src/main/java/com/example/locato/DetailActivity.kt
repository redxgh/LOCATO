package com.example.locato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import java.text.DecimalFormat

@Suppress("DEPRECATION")
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
    private lateinit var typeTxt: TextView
    private lateinit var categorieTxt: TextView



    private val formatter = DecimalFormat("##,##,##,##,##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initView()
        setVariable()

        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed() // Cette ligne reviendra à la page précédente
        }
    }



    private fun setVariable() {
        item = intent.getSerializableExtra("object") as ItemsDomaine
        if (item != null) {
            titleTxt.text = item?.title
            addressTxt.text = item?.accomodation?.location
            bedTxt.text = "${item?.accomodation?.rooms} Bed"
            bathTxt.text = "${item?.accomodation?.bathrooms} Bath"
            surfaceTxt.text = "${item?.accomodation?.surface} m²"
            descriptionTxt.text = item?.description ?: ""
            priceTxt.text = "${formatter.format(item?.price)} DT"
            typeTxt.text = item?.accomodation?.type
            categorieTxt.text = item?.accomodation?.categories?.name

            if (item.accomodation?.images?.isNotEmpty() == true) {
                val imageName = item.accomodation!!.images?.get(0)
                Log.d("ImageLoading", "Image Name: $imageName")

                // Remove file extension from image name
                val imageNameWithoutExtension = imageName?.substringBeforeLast(".")

                // Assuming that the images are in the res/drawable folder
                val resourceId = getResourceId(imageNameWithoutExtension)

                Glide.with(this)
                    .load(resourceId)
                    .into(pic)
            }
        } else {
            Log.e("DetailActivity", "Item is null.")
        }
    }

    private fun getResourceId(imageName: String?): Int {
        // Assuming that the images are in the res/drawable folder
        val resId = resources.getIdentifier(imageName, "drawable", packageName)
        Log.d("ImageLoading", "Resource ID for $imageName: $resId")
        return if (resId != 0) resId else R.drawable.image2
    }

    private fun initView() {
        titleTxt = findViewById(R.id.titleTxt)
        addressTxt = findViewById(R.id.addressTxt) // Adjusted to match the XML layout
        bedTxt = findViewById(R.id.bedTxt)
        bathTxt = findViewById(R.id.bathTxt)
        surfaceTxt = findViewById(R.id.wifiTxt)
        descriptionTxt = findViewById(R.id.descriptionTxt)
        pic = findViewById(R.id.pic)
        priceTxt=findViewById(R.id.priceTxt)
        typeTxt=findViewById(R.id.typeTxt)
        categorieTxt=findViewById(R.id.categorieTxt)


    }

}
