package com.example.locato

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dialog.FormDialogFragment
import java.time.LocalTime
import com.example.locato.ItemsAdapter.Action

class HomeActivity : AppCompatActivity() {
    private lateinit var recyclerViewPopular: RecyclerView
    private lateinit var recyclerViewNew: RecyclerView
    private lateinit var itemsAdapterPopular: ItemsAdapter
    private lateinit var itemsAdapterNew: ItemsAdapter

    private val itemsListPopular: ArrayList<ItemsDomaine> = ArrayList()
    private val itemsListNew: ArrayList<ItemsDomaine> = ArrayList()

    private lateinit var postAdBtn: FloatingActionButton
    private lateinit var filterButton: Button
    private val baseUrl = "http://192.168.0.141:8081/getAds"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scroll_main)

        // Configuration du RecyclerView avec un LayoutManager et l'adaptateur
        recyclerViewPopular = findViewById(R.id.viewPopular)
        recyclerViewNew = findViewById(R.id.viewNew)

        itemsAdapterPopular = ItemsAdapter(itemsListPopular, RECYCLER_VIEW_POPULAR)
        recyclerViewPopular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPopular.adapter = itemsAdapterPopular

        // Ajout d'exemples d'éléments à la liste itemsListPopular
        val item1 = ItemsDomaine(
            id = "1",
            title = "House with a great view",
            description = "This 2 bed /1 bath home boasts an enormous open-living plan, " +
                    "accented by striking architectural features and high-end finishes." +
                    " Feel inspired by open sight lines that embrace the outdoors crowned by stunning coffered ceilings",
            price = 200.0,
            timeStamp = LocalTime.now().toString(),
            accomodation = ItemsDomaine.Accomodation(
                location = "Sousse, Tunisie",
                surface = 0.0,
                rooms = 2,
                bathrooms = 1,
                best = 847456,
                images = listOf("image1"),
                type = "Type1",
                category = ItemsDomaine.Category(id = "1", name = "villa", image = null),
                categories = null
            ),
            gender = null
        )

        val item2 = ItemsDomaine(
            id = "2",
            title = "House with a great view",
            description = "This 2 bed /1 bath home boasts an enormous open-living plan, " +
                    "accented by striking architectural features and high-end finishes." +
                    " Feel inspired by open sight lines that embrace the outdoors crowned by stunning coffered ceilings",
            price = 200.0,
            timeStamp = LocalTime.now().toString(),
            accomodation = ItemsDomaine.Accomodation(
                location = "Sousse, Tunisie",
                surface = 0.0,
                rooms = 2,
                bathrooms = 1,
                best = 847456,
                images = listOf("image2"),
                type = "Type1",
                category = ItemsDomaine.Category(id = "1", name = "villa", image = null),
                categories = null
            ),
            gender = null
        )
        //filter surch btn
        // Create an instance of the dialog fragment

        itemsListPopular.add(item1)
        itemsListPopular.add(item2)

        filterButton = findViewById(R.id.filterBtn)
        filterButton.setOnClickListener {
            val dialog = FormDialogFragment()
            // Show the dialog
            dialog.show(supportFragmentManager, "FormDialogFragment")
        }

        //button post ad
        postAdBtn = findViewById(R.id.postAdBtn)
        postAdBtn.setOnClickListener(){
            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
        }

        // Appel de la fonction DeleteRecyclerView() pour configurer le recyclerViewNew
        DeleteRecyclerView()

        //volleyget
        MyVolleyRequest.getInstance(this).getRequest(baseUrl) { itemsList ->
            runOnUiThread {
                if (itemsList != null) {
                    // Update the RecyclerView with the obtained data
                    itemsListNew.clear()
                    itemsListNew.addAll(itemsList)
                    itemsAdapterNew.notifyDataSetChanged()
                } else {
                    // Handle the case where the request failed or the response is null
                    Toast.makeText(this, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
                    Log.e("MyVolleyRequest", "Failed to retrieve data")
                }
            }
        }

        // ... Autres configurations ...
    }

       //DeleteAd
    private fun DeleteRecyclerView() {
        // Configuration du RecyclerView avec un LayoutManager et l'adaptateur
        itemsAdapterNew = ItemsAdapter(itemsListNew, RECYCLER_VIEW_NEW, object : ItemsAdapter.OnItemClickListener {
            override fun onItemClick(item: ItemsDomaine?, action: ItemsAdapter.Action) {
                // L'interface est déjà mise en œuvre dans la méthode setupRecyclerView
                when (action) {
                    ItemsAdapter.Action.DELETE -> {
                        // Action de suppression, appel de la méthode de suppression
                        if (item != null) {
                            val adId = item.id
                            if (adId != null) {
                                MyVolleyRequest.getInstance(this@HomeActivity).deleteAdById(adId) { response ->
                                    // Handle the response, e.g., update UI or show a message
                                    if (response != null) {
                                        // Successful deletion
                                        Log.d("DeleteAd", "Ad deleted successfully.")
                                        // Mettez à jour votre RecyclerView ici si nécessaire
                                        runOnUiThread {
                                            itemsListNew.remove(item)
                                            itemsAdapterNew.notifyDataSetChanged()
                                        }
                                    } else {
                                        // Error during deletion
                                        Log.e("DeleteAd", "Error deleting ad.")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        })

        recyclerViewNew.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewNew.adapter = itemsAdapterNew
    }

    companion object {
        const val RECYCLER_VIEW_POPULAR = 1
        const val RECYCLER_VIEW_NEW = 2
    }
}

