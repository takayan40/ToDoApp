package io.github.takayan40.todoapp

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val checkbox: CheckBox = itemView.findViewById(R.id.checkbox)
    val titleListTextView: TextView = itemView.findViewById(R.id.title_list_text)
    val deadlineTextView: TextView = itemView.findViewById(R.id.deadline_list_text)


    interface ItemClickListener{
        fun onClickItemListener(todo: Todo)
    }

    interface HomeView{
        fun updateAdapter(list: ArrayList<Todo>)
    }
}