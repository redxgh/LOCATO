package dialog;

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.locato.FilterDialogListener
import com.example.locato.R

class FormDialogFragment : DialogFragment() {

        private var filterListener: FilterDialogListener? = null

        fun setFilterDialogListener(listener: FilterDialogListener) {
                this.filterListener = listener
        }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())

        // Inflate the form layout
        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.filter_layout, null)

        val typeAutoCompleteTextView: AutoCompleteTextView = view.findViewById(R.id.auto_complete_txt)
        val categoryAutoCompleteTextView: AutoCompleteTextView = view.findViewById(R.id.auto_complete_txt1)
        val priceEditText: EditText = view.findViewById(R.id.password)

        val categories = arrayOf("cat1", "cat2")
        val categoryAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_dropdown_item_1line, categories)

        val types = arrayOf("House", "Villa", "Studio", "Hotel", "Apartment")
        val typeAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_dropdown_item_1line, types)

        typeAutoCompleteTextView.setAdapter(typeAdapter)
        categoryAutoCompleteTextView.setAdapter(categoryAdapter)

                builder.setView(view)
                        .setTitle("Form")
                        .setCancelable(false)
                        .setPositiveButton("Filter") { _, _ ->
                                val selectedType = typeAutoCompleteTextView.text.toString()
                                val selectedCategory = categoryAutoCompleteTextView.text.toString()
                                val maxPrice = priceEditText.text.toString().toDoubleOrNull()

                                filterListener?.onFilterApplied(selectedType, selectedCategory, maxPrice)
                        }
                        .setNegativeButton("Cancel") { _, _ -> dismiss() }

                return builder.create()
        }
        }
