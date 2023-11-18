package com.example.locato

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dialog.FormDialogFragment


class HomeActivity : AppCompatActivity() {
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
        val item1 = ItemsDomaine("House with a great view", "Sousse, Tunisie, 90014", "This 2 bed /1 bath home boasts an enormous. open-living plan, accented by striking architectural features and high-end finishes. Feel inspired by open sight lines that embrace the outdoors crowned by stunning coffered ceilings"
            ,2, 1, 847456, "pic1", true)
        val item2 = ItemsDomaine("Plermio", "Sud", "This 2 bed /1 bath home boasts an enormous. open-living plan, accented by striking architectural features and high-end finishes. Feel inspired by open sight lines that embrace the outdoors crowned by stunning coffered ceilings"
            ,2, 1, 847456, "pic2", true)
        val item3 = ItemsDomaine("dar maria", "cap zebib, Tunisie, 90014", "This 2 bed /1 bath home boasts an enormous. open-living plan, accented by striking architectural features and high-end finishes. Feel inspired by open sight lines that embrace the outdoors crowned by stunning coffered ceilings"
            ,2, 1, 847456, "pic3", true)

        itemsListPopular.add(item1)
        itemsListPopular.add(item2)
        itemsListNew.add(item3)


        // Configuration du RecyclerView avec un LayoutManager et l'adaptateur
        itemsAdapterPopular = ItemsAdapter(itemsListPopular)
        recyclerViewPopular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPopular.adapter = itemsAdapterPopular

        itemsAdapterNew = ItemsAdapter(itemsListNew)
        recyclerViewNew.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewNew.adapter = itemsAdapterNew


      //filter surch btn
        // Create an instance of the dialog fragment
        val dialog = FormDialogFragment()
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
}

