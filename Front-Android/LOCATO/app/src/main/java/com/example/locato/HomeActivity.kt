package com.example.locato

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dialog.FormDialogFragment
import java.time.LocalTime

class HomeActivity : AppCompatActivity(), ItemsAdapter.OnDeleteClickListener  {
    private lateinit var recyclerViewPopular: RecyclerView
    private lateinit var recyclerViewNew: RecyclerView
    private lateinit var itemsAdapterPopular: ItemsAdapter
    private lateinit var itemsAdapterNew: ItemsAdapter

    private val itemsListPopular: ArrayList<ItemsDomaine> = ArrayList()
    private val itemsListNew: ArrayList<ItemsDomaine> = ArrayList()

    private lateinit var postAdBtn : FloatingActionButton

    private lateinit var filterButton : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scroll_main)
        // Initialisation du RecyclerView et ajout des éléments à la liste itemsListPopular
        recyclerViewPopular = findViewById(R.id.viewPopular)
        recyclerViewNew = findViewById(R.id.viewNew)


        // Ajout d'exemples d'éléments à la liste itemsListPopular
        val item1 = ItemsDomaine(
            id = 1,
            titleTxt = "House with a great view",
            address = "Sousse, Tunisie",
            description = "This 2 bed /1 bath home boasts an enormous open-living plan, " +
                    "accented by striking architectural features and high-end finishes." +
                    " Feel inspired by open sight lines that embrace the outdoors crowned by stunning coffered ceilings",
            bed = 2,
            bath = 1,
            price = 200,
            pic = "pic1",
            category = "villa",
            best = 847456,
            surface = 0.0,
            typeAd = "Type1",
            time = LocalTime.now()
        )

        val item2 = ItemsDomaine(
            id = 2,
            titleTxt = "House with a great view",
            address = "Sousse, Tunisie",
            description = "This 2 bed /1 bath home boasts an enormous open-living plan, accented by striking architectural features and high-end finishes. Feel inspired by open sight lines that embrace the outdoors crowned by stunning coffered ceilings",
            bed = 2,
            bath = 1,
            price = 200,
            pic = "pic2",
            category = "villa",
            best = 847456,
            surface = 0.0,
            typeAd = "Type1",
            time = LocalTime.now(),
        )
        val item3 = ItemsDomaine(
            id = 3,
            titleTxt = "House with a great view",
            address = "Sousse, Tunisie",
            description = "This 2 bed /1 bath home boasts an enormous open-living plan, accented by striking architectural features and high-end finishes. Feel inspired by open sight lines that embrace the outdoors crowned by stunning coffered ceilings",
            bed = 2,
            bath = 1,
            price = 200,
            pic = "pic3",
            category = "villa",
            best = 847456,
            surface = 0.0,
            typeAd = "Type1",
            time = LocalTime.now(),
        )

        itemsListPopular.add(item1)
        itemsListPopular.add(item2)
        itemsListNew.add(item3)


        // Configuration du RecyclerView avec un LayoutManager et l'adaptateur
        itemsAdapterPopular = ItemsAdapter(itemsListPopular, RECYCLER_VIEW_POPULAR)
        itemsAdapterPopular.setOnDeleteClickListener(this)
        recyclerViewPopular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPopular.adapter = itemsAdapterPopular

        itemsAdapterNew = ItemsAdapter(itemsListNew, RECYCLER_VIEW_NEW)
        itemsAdapterNew.setOnDeleteClickListener(this)
        recyclerViewNew.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewNew.adapter = itemsAdapterNew


      //filter surch btn
        // Create an instance of the dialog fragment
        
        filterButton = findViewById(R.id.filterBtn)
        filterButton.setOnClickListener {
            val dialog = FormDialogFragment()
            // Show the dialog
            dialog.show(supportFragmentManager, "FormDialogFragment")
        }

        postAdBtn = findViewById(R.id.postAdBtn)
        postAdBtn.setOnClickListener(){
            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
        }

    }
    //delete
    override fun onDeleteClick(position: Int, recyclerViewType: Int) {
        when (recyclerViewType) {
            RECYCLER_VIEW_POPULAR -> {
                itemsListPopular.removeAt(position)
                itemsAdapterPopular.notifyItemRemoved(position)
            }
            RECYCLER_VIEW_NEW -> {
                itemsListNew.removeAt(position)
                itemsAdapterNew.notifyItemRemoved(position)
            }
        }
    }
    companion object {
        const val RECYCLER_VIEW_POPULAR = 1
        const val RECYCLER_VIEW_NEW = 2
    }





}

