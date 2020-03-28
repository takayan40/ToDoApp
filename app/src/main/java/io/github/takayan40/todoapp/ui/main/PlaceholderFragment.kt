package io.github.takayan40.todoapp.ui.main

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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

    // プッシュ通知用
    lateinit var builder: NotificationCompat.Builder
    var notificationId = 0   /// notificationID

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainPresenter = MainPresenter(this)
        setPush()
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.dataUpdate()
    }

    override fun updateAdapter(list: ArrayList<Todo>) {
        todoAdapter.update(list)

    }

    override fun onClickItemListener(todo: Todo) {
        val intent = Intent(activity, DetailActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, todo)
        }
        startActivity(intent)
    }

    override fun onClickLongItemListener(todo: Todo): Boolean {
        /// 通知の中身
        builder = NotificationCompat.Builder(this.context!!, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_check_box)              // 表示されるアイコン
            .setContentTitle(todo.title)                        // 通知タイトル
            .setContentText(todo.detail)                        // 通知コンテンツ
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)   // 通知の優先度

        with(NotificationManagerCompat.from(this.context!!)) {
            notify(notificationId, builder.build())
            notificationId += 1
        }
        return true
    }

    private fun setPush() {

        ///APIレベルに応じてチャネルを作成
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_NAME
            val descriptionText = CHANNEL_DESCRIPTION
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            /// チャネルを登録
            val notificationManager: NotificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
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

        const val CHANNEL_ID = "channel_id"
        const val CHANNEL_NAME = "channel_name"
        const val CHANNEL_DESCRIPTION = "channel_description "

    }
}