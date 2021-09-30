package com.wzq.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wzq.room.databinding.ActivityMainBinding
import com.wzq.room.room.AppDatabase
import com.wzq.room.room.dao.entity.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var viewBind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        val adapter = object : PagingDataAdapter<Student, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.sId == newItem.sId
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem == newItem
            }

        }) {
            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                holder.itemView.findViewById<TextView>(R.id.item).text = getItem(position).toString()
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                return object : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)) {

                }
            }
        }


        viewBind.recyclerview.adapter = adapter

        viewBind.add.setOnClickListener {
            Log.e("data", "add")
            GlobalScope.launch {
                for (index in 0..1000) {
                    AppDatabase.appDatabase.studentDao().insertStudents(Student(0, name = "张三", age = 20))
                }

            }
        }


        viewBind.quary.setOnClickListener {
            GlobalScope.launch {
                Pager(config = PagingConfig(pageSize = 20)) {
                    Log.e("quary","aaaaa")
                    AppDatabase.appDatabase.studentDao().query()
                }.flow.collectLatest {
                    adapter.submitData(it)
                }

            }
        }


    }


}