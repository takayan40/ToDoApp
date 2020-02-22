package io.github.takayan40.todoapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_add.*
import java.text.SimpleDateFormat
import java.util.*
import android.widget.Toast
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log


class AddActivity : AppCompatActivity(), DatePickerDialogFragment.Callback{

    private lateinit var dateEdit: EditText
    private lateinit var prioritySpinner: Spinner
    private lateinit var addButton: Button
    private lateinit var deleteButton: Button

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        toolbar.title = "追加"

        dateEdit = findViewById(R.id.date_edit)
        prioritySpinner = findViewById(R.id.priority_spinner)
        addButton = findViewById(R.id.add_button)
        deleteButton = findViewById(R.id.delete_button)

        dateEdit.setText(SimpleDateFormat("yyyy/MM/dd").format(Date()))

        Log.d("tag", prioritySpinner.selectedItem.toString())

        // 日付
        dateEdit.setOnClickListener {
            DatePickerDialogFragment().apply {
            }.show(supportFragmentManager, DatePickerDialogFragment::class.java.canonicalName)
        }
        // 登録
        addButton.setOnClickListener{

        }
        // 削除
        deleteButton.setOnClickListener {

        }

    }

    override fun onDialogResult(dialogCode: Int?, year: Int, month: Int, dayOfMonth: Int) {
        dateEdit.setText("%s/%s/%s".format(year, month, dayOfMonth))
    }
}
