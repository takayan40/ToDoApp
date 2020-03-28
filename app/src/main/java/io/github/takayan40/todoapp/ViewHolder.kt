package io.github.takayan40.todoapp

import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val relativeLayout: RelativeLayout = itemView.findViewById(R.id.relative_layout)
    val titleListTextView: TextView = itemView.findViewById(R.id.title_list_text)
    val deadlineTextView: TextView = itemView.findViewById(R.id.deadline_list_text)

    interface ItemClickListener{
        fun onClickItemListener(todo: Todo)
        fun onClickLongItemListener(todo: Todo): Boolean
    }

    interface HomeView{
        fun updateAdapter(list: ArrayList<Todo>)
    }
}