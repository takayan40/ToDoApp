package io.github.takayan40.todoapp.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_add.*
import java.text.SimpleDateFormat
import java.util.*
import android.util.Log
import io.github.takayan40.todoapp.Presenter.AddPresenter
import io.github.takayan40.todoapp.R


class AddActivity : AppCompatActivity(),
    DatePickerDialogFragment.Callback {

    private lateinit var dateEdit: EditText

    private var presenter = AddPresenter()

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        toolbar.title = "追加"

        val titleEdit: EditText = findViewById(R.id.title_edit)
        val detailEdit: EditText = findViewById(R.id.detail_edit)
        dateEdit = findViewById(R.id.deadline_edit)
        val prioritySpinner: Spinner = findViewById(R.id.priority_spinner)
        val addButton: Button = findViewById(R.id.add_button)
        val deleteButton: Button = findViewById(R.id.delete_button)

        dateEdit.setText(SimpleDateFormat("yyyy/MM/dd").format(Date()))

        Log.d("tag", prioritySpinner.selectedItem.toString())

        // 日付
        dateEdit.setOnClickListener {
            DatePickerDialogFragment().apply {
            }.show(supportFragmentManager, DatePickerDialogFragment::class.java.canonicalName)
        }
        // 登録
        addButton.setOnClickListener {
            presenter.add(
                titleEdit.text.toString(),
                detailEdit.text.toString(),
                prioritySpinner.selectedItemId,
                dateEdit.text.toString(),
                Date()
            )
        }
        // 削除
        deleteButton.setOnClickListener {

        }
    }

    override fun onDialogResult(year: Int, month: Int, dayOfMonth: Int) {
        dateEdit.setText("%s/%s/%s".format(year, month, dayOfMonth))
    }
}
