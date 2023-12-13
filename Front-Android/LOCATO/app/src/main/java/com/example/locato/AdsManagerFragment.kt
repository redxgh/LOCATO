package com.example.locato

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.locato.R.*


class AdManagerFragment : Fragment() {
    private lateinit var adsRecyclerView: RecyclerView
    private lateinit var itemsAdapterNew: ItemsAdapter
    private var itemsListNew: ArrayList<ItemsDomaine> = ArrayList()

    private lateinit var baseUrl: String
    private lateinit var ip: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layout.fragment_ad_manager, container, false)

        // Initialize RecyclerView and its adapter
        adsRecyclerView = view.findViewById(R.id.adsRV)
        itemsAdapterNew = ItemsAdapter(itemsListNew)
        adsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adsRecyclerView.adapter = itemsAdapterNew

        // Fetch data using Volley
        val userId = "jQgIBsBLeyUG8CwcdeM1WbaMf9h2" // Replace with the actual user ID
        MyVolleyRequest.getInstance(requireContext()).getAdsByUserId(userId) { adsList ->
            requireActivity().runOnUiThread {
                if (adsList != null) {
                    // Update the UI with the obtained ads for the user
                    itemsListNew.clear()
                    itemsListNew.addAll(adsList)
                    itemsAdapterNew.notifyDataSetChanged()
                } else {
                    // Handle the case where the request failed or the response is null
                    Toast.makeText(
                        requireContext(),
                        "Failed to retrieve ads for the user",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("MyVolleyRequest", "Failed to retrieve ads for the user")
                }
            }
        }

        return view
    }
}

