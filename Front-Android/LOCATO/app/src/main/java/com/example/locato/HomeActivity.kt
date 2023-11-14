package com.example.locato

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeActivity : AppCompatActivity() {
    private lateinit var recyclerViewPopular: RecyclerView
    private lateinit var recyclerViewNew: RecyclerView

    private lateinit var itemsAdapterPopular: ItemsAdapter
    private lateinit var itemsAdapterNew: ItemsAdapter

    private val itemsListPopular: ArrayList<ItemsDomaine> = ArrayList()
    private val itemsListNew: ArrayList<ItemsDomaine> = ArrayList()

    private lateinit var postAdBtn : FloatingActionButton


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scroll_main)
        // Initialisation du RecyclerView et ajout des éléments à la liste itemsListPopular
        recyclerViewPopular = findViewById(R.id.viewPopular)
        recyclerViewNew = findViewById(R.id.viewNew)


        // Ajout d'exemples d'éléments à la liste itemsListPopular
        val item1 = ItemsDomaine("Dar Calibori", "Sousse", 100, "pic1")
        val item2 = ItemsDomaine("Plermio", "Sud", 200, "pic2")
        val item3 = ItemsDomaine("Dar Maria", "Cap zebib", 150, "pic3")

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



        postAdBtn = findViewById(R.id.postAdBtn)
        postAdBtn.setOnClickListener(){
            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
        }
    }
}

