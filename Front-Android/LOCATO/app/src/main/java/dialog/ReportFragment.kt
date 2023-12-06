package dialog

// ReportFragment.java

// ReportFragment.kt

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.locato.R

class ReportFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view= inflater.inflate(R.layout.report, container, false)

        val reportBtn: Button = view.findViewById(R.id.reportBtn)

        reportBtn.setOnClickListener {
            // Replace the current fragment with ConfirmReportFragment
            val confirmReportFragment = ConfirmReportFragment()
            confirmReportFragment.show(parentFragmentManager, "confirmReportFragment")
        }
        // Inflate the layout for this fragment
        return view
    }
}

