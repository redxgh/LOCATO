package dialog



import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.locato.R
import androidx.navigation.fragment.findNavController
import com.example.locato.HomeActivity


class ConfirmReportFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.report_done, container, false)

        val doneBtn: Button = view.findViewById(R.id.DoneBtn)

        doneBtn.setOnClickListener {
            // Navigate back to HomeActivity using an Intent
            val intent = Intent(context, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            // Close the current dialog fragment
            dismiss()
        }


        return view
    }
}

