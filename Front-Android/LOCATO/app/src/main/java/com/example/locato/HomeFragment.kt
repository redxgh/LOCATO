package com.example.locato

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.locato.Chat.ProfileFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dialog.FormDialogFragment
import java.time.LocalTime

class HomeFragment : Fragment(), FilterDialogListener {

    private lateinit var recyclerViewNew: RecyclerView
    private lateinit var itemsAdapterNew: ItemsAdapter
    private lateinit var allItemsListNew: List<ItemsDomaine>
    private val itemsListNew: ArrayList<ItemsDomaine> = ArrayList()
    private lateinit var originalItemsListNew: List<ItemsDomaine>
    private var initialItemsListNew: ArrayList<ItemsDomaine> = ArrayList()
    private lateinit var filterButton: Button
    private lateinit var noResultsTextView: TextView
    private lateinit var baseUrl: String
    private lateinit var ip: String
    private val activeFilters: MutableList<(ItemsDomaine) -> Boolean> = mutableListOf()
    private var selectedType: String? = null
    private var selectedCategory: String? = null
    private var maxPrice: Double? = null
    private lateinit var searchEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        ip = getString(R.string.ip)
        baseUrl = "http://$ip:8081/getAds"

        searchEditText = view.findViewById(R.id.editTextText)
        //Config category recycler
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        val itemList = listOf("Hotel", "Home", "Apartment", "Villa","Studio","Office","Condo","Farm","")
        val adapter = HorizontalTextAdapter(itemList)
        recyclerView.adapter = adapter

        // Configuration du RecyclerView avec un LayoutManager et l'adaptateur
        recyclerViewNew = view.findViewById(R.id.viewNew)
        //refresh
        originalItemsListNew = ArrayList(itemsListNew)

        // Sauvegarder les listes initiales
        initialItemsListNew = ArrayList(itemsListNew)

        noResultsTextView = view.findViewById(R.id.noResultsTextView)
        noResultsTextView.visibility = View.GONE

        //filter surch btn
        filterButton = view.findViewById(R.id.filterBtn)
        filterButton.setOnClickListener {
            val dialog = FormDialogFragment()
            dialog.setFilterDialogListener(this)
            dialog.show(requireFragmentManager(), "FormDialogFragment")
        }

        // Appel de la fonction DeleteRecyclerView() pour configurer le recyclerViewNew
        DeleteRecyclerView()

        //volleyget
        MyVolleyRequest.getInstance(requireContext()).getRequest(baseUrl) { itemsList ->
            requireActivity().runOnUiThread {
                if (itemsList != null) {
                    // Update the RecyclerView with the obtained data
                    itemsListNew.clear()
                    itemsListNew.addAll(itemsList)
                    itemsAdapterNew.notifyDataSetChanged()

                } else {
                    // Handle the case where the request failed or the response is null
                    Toast.makeText(requireContext(), "Failed to retrieve data", Toast.LENGTH_SHORT)
                        .show()
                    Log.e("MyVolleyRequest", "Failed to retrieve data")
                }
            }
        }


        return view
    }

    private fun DeleteRecyclerView() {
        // Configuration du RecyclerView avec un LayoutManager et l'adaptateur
        itemsAdapterNew = ItemsAdapter(
            itemsListNew,
            RECYCLER_VIEW_NEW,
            object : ItemsAdapter.OnItemClickListener {
                override fun onItemClick(item: ItemsDomaine?, action: ItemsAdapter.Action) {
                    when (action) {
                        ItemsAdapter.Action.DELETE -> {
                            if (item != null) {
                                // Show delete confirmation dialog
                                showDeleteConfirmationDialog(item)
                            }
                        }
                    }
                }
            })

        recyclerViewNew.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewNew.adapter = itemsAdapterNew
    }

    private fun showDeleteConfirmationDialog(item: ItemsDomaine) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Delete Ad")
        alertDialogBuilder.setMessage("Do you want to delete this ad?")

        alertDialogBuilder.setPositiveButton("OK") { _, _ ->
            val adId = item.id
            if (adId != null) {
                MyVolleyRequest.getInstance(requireContext()).deleteAdById(adId) { response ->
                    if (response != null) {
                        // Successful deletion
                        Log.d("DeleteAd", "Ad deleted successfully.")
                        // Mettez à jour votre RecyclerView ici si nécessaire
                        requireActivity().runOnUiThread {
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
        // Mettez à jour le RecyclerView avec les résultats de la recherche
        itemsListNew.clear()
        itemsListNew.addAll(filteredList)
        /////
    }
    //refreshList
    private fun refreshLists() {
        itemsListNew.clear()
        itemsListNew.addAll(originalItemsListNew)
        itemsAdapterNew.notifyDataSetChanged()


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


        // Apply all active filters to itemsListNew
        val filteredListNew = initialItemsListNew.filter { item ->
            activeFilters.all { filter -> filter(item) }
        }

        itemsListNew.clear()
        itemsListNew.addAll(filteredListNew)
        itemsAdapterNew.notifyDataSetChanged()

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

}