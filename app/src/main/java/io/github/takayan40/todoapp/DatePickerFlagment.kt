package io.github.takayan40.todoapp

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

    companion object {
        /**
         * 値渡しをするときのタグ
         */
        const val ARG_CODE = "dialog_code"
    }

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
        val requestCode = arguments?.getInt(ARG_CODE)

        listener?.onDialogResult(requestCode, year, month + 1, dayOfMonth)
    }

    interface Callback {
        fun onDialogResult(dialogCode: Int?, year: Int, month: Int, dayOfMonth: Int)
    }
}