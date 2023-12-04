package com.example.locato


interface FilterDialogListener {
    fun onFilterApplied(type: String, category: String, price: Double?)
    fun onCancelFilter()
}
