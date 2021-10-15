package com.wzq.motionlayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.wzq.motionlayout.baseic.BasicMovementActivity
import com.wzq.motionlayout.baseic.KeyFramePositionActivity
import com.wzq.motionlayout.coordinator.CoordinatorLayoutActivity
import com.wzq.motionlayout.databinding.ActivityMainBinding
import com.wzq.motionlayout.drawerlayout.DrawerLayoutActivity

class MainActivity : AppCompatActivity() {

    val funcations = mutableListOf<Pair<String, Class<*>>>()

    lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        funcations.add(Pair("基本运动", BasicMovementActivity::class.java))
        funcations.add(Pair("关键帧位置", KeyFramePositionActivity::class.java))
        funcations.add(Pair("CoordinatorLayout", CoordinatorLayoutActivity::class.java))
        funcations.add(Pair("DrawerLayout", DrawerLayoutActivity::class.java))


        viewBinding.recyclerview.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                return object : RecyclerView.ViewHolder(TextView(this@MainActivity)) {

                }
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                (holder.itemView as TextView).apply {
                    setPadding(30)
                    text = funcations[position].first
                    setOnClickListener {
                        startActivity(Intent(this@MainActivity, funcations[position].second))

                    }
                }
            }

            override fun getItemCount(): Int {
                return funcations.size
            }

        }
    }
}