package io.github.takayan40.todoapp.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar
import java.util.Locale

class DatePickerDialogFragment : DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    private var listener: Callback? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance(Locale.JAPAN)
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity!!, this, year, month, dayOfMonth)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (parentFragment is Callback) {
            listener = parentFragment as Callback
            return
        }
        if (context is Callback) {
            listener = context
        }
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        listener?.onDialogResult(year, month + 1, dayOfMonth)
    }

    interface Callback {
        fun onDialogResult(year: Int, month: Int, dayOfMonth: Int)
    }
}