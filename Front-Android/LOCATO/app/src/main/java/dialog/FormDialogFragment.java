package dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.locato.R;

public class FormDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Inflate the form layout
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.filter_layout, null);

        final AutoCompleteTextView typeAutoCompleteTextView = view.findViewById(R.id.auto_complete_txt);
        final AutoCompleteTextView categoryAutoCompleteTextView = view.findViewById(R.id.auto_complete_txt1);


        String[] types = {"Colocation", "Rent"};
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, types);

        String[] categories = {"House", "Villa", "Studio","Hotel","Appartment"};
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, categories);


        typeAutoCompleteTextView.setAdapter(typeAdapter);
        categoryAutoCompleteTextView.setAdapter(categoryAdapter);


        builder.setView(view)
                .setTitle("Form")
                .setCancelable(false)
                .setPositiveButton("Filter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FormDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
