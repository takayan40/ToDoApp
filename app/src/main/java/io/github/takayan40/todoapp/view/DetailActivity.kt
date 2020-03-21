package io.github.takayan40.todoapp.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import io.github.takayan40.todoapp.Presenter.AddPresenter
import io.github.takayan40.todoapp.R
import io.github.takayan40.todoapp.Todo
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.SimpleDateFormat
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailActivity : AppCompatActivity(), DatePickerDialogFragment.Callback {

    private var presenter = AddPresenter()
    private lateinit var todo: Todo

    private lateinit var dateEdit: EditText
    private lateinit var titleEdit: EditText
    private lateinit var detailEdit: EditText
    private lateinit var completeButton: Button
    private lateinit var deleteButton: Button
    private lateinit var prioritySpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        toolbar.title = "詳細"

        todo = intent.getParcelableExtra(AlarmClock.EXTRA_MESSAGE)

        titleEdit = findViewById(R.id.title_edit)
        detailEdit = findViewById(R.id.detail_edit)
        dateEdit = findViewById(R.id.deadline_edit)
        prioritySpinner = findViewById(R.id.priority_spinner)
        completeButton = findViewById(R.id.complete_button)
        deleteButton = findViewById(R.id.delete_button)

        setEvent()
    }

    override fun onResume() {
        super.onResume()
        setView()
    }

    override fun onDialogResult(year: Int, month: Int, dayOfMonth: Int) {
        dateEdit.setText("%s/%s/%s".format(year, month, dayOfMonth))
    }

    @SuppressLint("SimpleDateFormat")
    fun setView() {
        titleEdit.setText(todo.title)
        detailEdit.setText(todo.detail)
        dateEdit.setText(SimpleDateFormat("yyyy/MM/dd").format(todo.deadline))
        prioritySpinner.setSelection(todo.priority.toInt())
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
                todo.id,
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
            presenter.delete(todo.id)
            finish()
        }
    }
}
