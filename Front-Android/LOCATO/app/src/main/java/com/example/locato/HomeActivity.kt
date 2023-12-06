package com.example.locato

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dialog.FormDialogFragment
import dialog.ReportFragment

import java.time.LocalTime
import com.example.locato.ItemsAdapter.Action

class HomeActivity : AppCompatActivity(), FilterDialogListener {
    private lateinit var recyclerViewPopular: RecyclerView
    private lateinit var recyclerViewNew: RecyclerView
    private lateinit var itemsAdapterPopular: ItemsAdapter
    private lateinit var itemsAdapterNew: ItemsAdapter
    private lateinit var allItemsListNew: List<ItemsDomaine>
    private lateinit var allItemsListPopular: List<ItemsDomaine>
    private val itemsListPopular: ArrayList<ItemsDomaine> = ArrayList()
    private val itemsListNew: ArrayList<ItemsDomaine> = ArrayList()
    private lateinit var originalItemsListNew: List<ItemsDomaine>
    private lateinit var originalItemsListPopular: List<ItemsDomaine>
    private  var initialItemsListNew : ArrayList<ItemsDomaine> = ArrayList()
    private  var initialItemsListPopular: ArrayList<ItemsDomaine> = ArrayList()
    private lateinit var postAdBtn: FloatingActionButton
    private lateinit var filterButton: Button
    private lateinit var noResultsTextView: TextView
    private  lateinit var baseUrl :String
    private  lateinit var ip :String
    private val activeFilters: MutableList<(ItemsDomaine) -> Boolean> = mutableListOf()

    private var selectedType: String? = null
    private var selectedCategory: String? = null
    private var maxPrice: Double? = null

    private lateinit var searchEditText: EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scroll_main)
        ip = getString(R.string.ip)
        baseUrl ="http://$ip:8081/getAds"

        searchEditText = findViewById(R.id.editTextText)
        // Get the button reference



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
                type = "House",
                category = ItemsDomaine.Category(id = "1", name = "cat1", image = null),
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
                type = "Apartment",
                category = ItemsDomaine.Category(id = "1", name = "cat2", image = null),
                categories = null,
            ),
            gender = null
        )


        itemsListPopular.add(item1)
        itemsListPopular.add(item2)
        //refresh
        originalItemsListNew = ArrayList(itemsListNew)
        originalItemsListPopular = ArrayList(itemsListPopular)
        // Sauvegarder les listes initiales
        initialItemsListNew = ArrayList(itemsListNew)
        initialItemsListPopular = ArrayList(itemsListPopular)
        noResultsTextView = findViewById(R.id.noResultsTextView)
        noResultsTextView.visibility = View.GONE

        //filter surch btn
        // Create an instance of the dialog fragment
        filterButton = findViewById(R.id.filterBtn)
        filterButton.setOnClickListener {
            val dialog = FormDialogFragment()
            dialog.setFilterDialogListener(this)
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
                               // Show delete confirmation dialog
                               showDeleteConfirmationDialog(item)
                           }
                       }
                   }
               }
           })

           recyclerViewNew.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
           recyclerViewNew.adapter = itemsAdapterNew
       }

    private fun showDeleteConfirmationDialog(item: ItemsDomaine) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Delete Ad")
        alertDialogBuilder.setMessage("Do you want to delete this ad?")

        alertDialogBuilder.setPositiveButton("OK") { _, _ ->
            val adId = item.id
            if (adId != null) {
                MyVolleyRequest.getInstance(this@HomeActivity).deleteAdById(adId) { response ->
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

        alertDialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            // User clicked Cancel, do nothing or handle as needed
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


    companion object {
        const val RECYCLER_VIEW_POPULAR = 1
        const val RECYCLER_VIEW_NEW = 2
    }

    // Fonction appelée lors du clic sur l'icône de recherche
    fun onSearchIconClick(view: View) {
        val searchTerm = searchEditText.text.toString()
        performSearch(searchTerm)
    }

    // Ajouter une fonction pour effectuer la recherche
    private fun performSearch(searchTerm: String) {
        // Faites quelque chose avec le terme de recherche (par exemple, filtrer la liste)
        val filteredList = itemsListNew.filter { it.title.contains(searchTerm, true) }
        val filteredList2 = itemsListPopular.filter { it.title.contains(searchTerm, true) }
        // Mettez à jour le RecyclerView avec les résultats de la recherche
        itemsListNew.clear()
        itemsListNew.addAll(filteredList)
        itemsAdapterNew.notifyDataSetChanged()
        /////
        itemsListPopular.clear()
        itemsListPopular.addAll(filteredList2)
        itemsAdapterPopular.notifyDataSetChanged()
    }
    //refreshList
    private fun refreshLists() {
        itemsListNew.clear()
        itemsListNew.addAll(originalItemsListNew)
        itemsAdapterNew.notifyDataSetChanged()

        itemsListPopular.clear()
        itemsListPopular.addAll(originalItemsListPopular)
        itemsAdapterPopular.notifyDataSetChanged()
    }





    override fun onCancelFilter() {
        // Reset filters or handle cancellation if needed
        selectedType = null
        selectedCategory = null
        maxPrice = null
        // Clear all filters
        clearFilters()

        // Refetch data or update RecyclerViews accordingly
       filterItems()
    }

    private fun filterItems() {
        if (initialItemsListNew.isEmpty()) {
            initialItemsListNew = ArrayList(itemsListNew)
        }

        if (initialItemsListPopular.isEmpty()) {
            initialItemsListPopular = ArrayList(itemsListPopular)
        }


        // Apply all active filters to itemsListNew
        val filteredListNew = initialItemsListNew.filter { item ->
            activeFilters.all { filter -> filter(item) }
        }

        itemsListNew.clear()
        itemsListNew.addAll(filteredListNew)
        itemsAdapterNew.notifyDataSetChanged()

        // Apply all active filters to itemsListPopular
        val filteredListPopular = initialItemsListPopular.filter { item ->
            activeFilters.all { filter -> filter(item) }
        }

        itemsListPopular.clear()
        itemsListPopular.addAll(filteredListPopular)
        itemsAdapterPopular.notifyDataSetChanged()

        if (itemsListNew.isEmpty() && itemsListPopular.isEmpty()) {
            showNoResultsMessage()
        } else {
            hideNoResultsMessage()
        }
    }
    private fun addFilter(filter: (ItemsDomaine) -> Boolean) {
        activeFilters.add(filter)
    }

    private fun removeFilter(filter: (ItemsDomaine) -> Boolean) {
        activeFilters.remove(filter)
    }

    private fun clearFilters() {
        initialItemsListNew.clear()
        initialItemsListNew.addAll(allItemsListNew)

        initialItemsListPopular.clear()
        initialItemsListPopular.addAll(allItemsListPopular)
    }
    override fun onFilterApplied(type: String, category: String, price: Double?) {
        selectedType = type
        selectedCategory = category
        maxPrice = price

        // Add filter based on selectedType, selectedCategory, maxPrice, etc.
        addFilter { item ->
            (selectedType == null || item.accomodation?.type == selectedType) &&
                    (selectedCategory == null || item.accomodation?.category?.name == selectedCategory) &&
                    (maxPrice == null || item.price <= maxPrice!!)
        }

        // Apply filters
        filterItems()
    }


    private fun showNoResultsMessage() {

        val noResultsTextView: TextView = findViewById(R.id.noResultsTextView)
        noResultsTextView.visibility = View.VISIBLE
    }

    private fun hideNoResultsMessage() {
        // Masquer le message "No results found" s'il y a des résultats.
        val noResultsTextView: TextView = findViewById(R.id.noResultsTextView)
        noResultsTextView.visibility = View.GONE
    }


}











