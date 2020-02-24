package io.github.takayan40.todoapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class TodoAdapter(
    private val itemClickListener: ViewHolder.ItemClickListener,
    private val itemList: ArrayList<Todo>
) : RecyclerView.Adapter<ViewHolder>() {

    private var mRecyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null

    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[holder.adapterPosition]

        holder.let {
            it.titleListTextView.text = item.title
            it.deadlineTextView.text = SimpleDateFormat("yyyy/MM/dd").format(item.deadline)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.todo_item, parent, false)
        return ViewHolder(view)
    }

    fun update(data: ArrayList<Todo>) {
        if (itemList.isNotEmpty()) {
            itemList.clear()
        }
        itemList.addAll(data)
        notifyDataSetChanged()
    }
}