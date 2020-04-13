package com.test.speedrecyclerviewkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.speedrecyclerviewkotlin.databinding.ItemPersonBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var page = 1
    var isLoading = false
    val limit = 10
    var people = arrayListOf<Person>()

    lateinit var adapter: PersonAdapter
    lateinit var layoutManager2: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutManager2 = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager2
        getPage()


        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount = layoutManager2.childCount
                    val pastVisibleItem =
                        layoutManager2.findFirstCompletelyVisibleItemPosition()
                    val total = adapter.itemCount

                    if (!isLoading) {
                        if ((visibleItemCount + pastVisibleItem) >= total) {
                            page++
                            getPage()
                        }
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    fun getPage() {
        isLoading = true
        progressBar.visibility = View.VISIBLE
        val start = (page - 1) * limit
        val end = (page) * limit

        for (i in start..end) {
            people.add(Person("홍길동 $i", 20))
        }

        Handler().postDelayed({
            if (::adapter.isInitialized) {
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "생성됨: $start -> $end", Toast.LENGTH_SHORT).show()
                adapter = PersonAdapter(people) {person ->
                    // == recycle_view.adapter
                    Toast.makeText(this, "$person", Toast.LENGTH_SHORT).show()
                }
                recycler_view.adapter = adapter
            }
            isLoading = false
            progressBar.visibility = View.GONE
        }, 5000)


    }
}

class PersonAdapter(val items: List<Person>, private val clickListener: (person: Person) -> Unit) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    class PersonViewHolder(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        val viewHolder = PersonViewHolder(ItemPersonBinding.bind(view))
        view.setOnClickListener {
            clickListener.invoke(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.binding.person = items[position]
    }

}