package io.github.takayan40.todoapp.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.takayan40.todoapp.R
import io.github.takayan40.todoapp.Todo
import io.github.takayan40.todoapp.TodoAdapter
import io.github.takayan40.todoapp.ViewHolder
import io.github.takayan40.todoapp.view.DetailActivity

class PlaceholderFragment : Fragment(), ViewHolder.HomeView, ViewHolder.ItemClickListener {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var todoAdapter: TodoAdapter

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainPresenter = MainPresenter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.mainRecyclerView)
        todoAdapter = TodoAdapter(this, ArrayList())
        val viewManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = viewManager
        recyclerView.adapter = todoAdapter

    }

    override fun onClickItemListener(todo: Todo) {
        val intent = Intent(activity, DetailActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, todo)
        }
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.dataUpdate()
    }

    override fun updateAdapter(list: ArrayList<Todo>) {
        todoAdapter.update(list)

    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}