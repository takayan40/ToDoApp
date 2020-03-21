package io.github.takayan40.todoapp.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import io.github.takayan40.todoapp.Presenter.AddPresenter
import io.github.takayan40.todoapp.R
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity(), DatePickerDialogFragment.Callback {

    private var presenter = AddPresenter()

    private lateinit var dateEdit: EditText
    private lateinit var titleEdit: EditText
    private lateinit var detailEdit: EditText
    private lateinit var completeButton: Button
    private lateinit var deleteButton: Button
    private lateinit var prioritySpinner: Spinner

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        toolbar.title = "詳細"

        titleEdit = findViewById(R.id.title_edit)
        detailEdit = findViewById(R.id.detail_edit)
        dateEdit = findViewById(R.id.deadline_edit)
        prioritySpinner = findViewById(R.id.priority_spinner)
        completeButton = findViewById(R.id.add_button)
        deleteButton = findViewById(R.id.delete_button)
        dateEdit.setText(SimpleDateFormat("yyyy/MM/dd").format(Date()))

        setEvent()
    }

    override fun onDialogResult(year: Int, month: Int, dayOfMonth: Int) {
        dateEdit.setText("%s/%s/%s".format(year, month, dayOfMonth))
    }

    fun setEvent() {
        // 日付
        dateEdit.setOnClickListener {
            DatePickerDialogFragment().apply {
            }.show(supportFragmentManager, DatePickerDialogFragment::class.java.canonicalName)
        }

        // 完了
        completeButton.setOnClickListener {
            presenter.update(
                titleEdit.text.toString(),
                detailEdit.text.toString(),
                prioritySpinner.selectedItemId,
                dateEdit.text.toString(),
                Date()
            )
            finish()
        }
        // 削除
        deleteButton.setOnClickListener {

        }
    }
}
