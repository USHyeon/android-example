package com.umssung.skeletonlibraryexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        val adapter = ItemAdapter()
        val skeletonScreen: SkeletonScreen = Skeleton.bind(recycler_view)
            .adapter(adapter)
            .count(10)
            .load(R.layout.item_view_skeleton)
            .show() //default count is 10

        recycler_view.postDelayed(Runnable { skeletonScreen.hide() }, 3000)
    }
}