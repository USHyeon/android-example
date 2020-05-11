package com.devosha.kotlin_recyclerview_checkbox

import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import java.util.ArrayList

/**
 * class: MainActivity
 * This class is deriving from `android.support.v7.app.AppCompatActivity`.
 */
class MainActivity : AppCompatActivity() {


    private val teachers: Array<SpiritualTeacher>
        get() =
            arrayOf(SpiritualTeacher("Rumi", "Out beyond ideas of wrongdoing and rightdoing there is a field.I'll meet you there."),
                    SpiritualTeacher("Eckhart Tolle", "Walk as if you are kissing the Earth with your feet."),
                    SpiritualTeacher("Meister Eckhart", "Man suffers only because he takes seriously what the gods made for fun."),
                    SpiritualTeacher("Mooji", "I have lived with several Zen masters -- all of them cats."),
                    SpiritualTeacher("Confucius", "I'm simply saying that there is a way to be sane. I'm saying that you "),
                    SpiritualTeacher("Francis Lucille", "The way out is through the door. Why is it that no one will use this method?"),
                    SpiritualTeacher("Thich Nhat Hanh", "t is the power of the mind to be unconquerable."),
                    SpiritualTeacher("Dalai Lama", "It's like you took a bottle of ink and you threw it at a wall. Smash! "),
                    SpiritualTeacher("Jiddu Krishnamurti", "A student, filled with emotion and crying, implored, 'Why is there so much suffering?"),
                    SpiritualTeacher("Osho", "Only the hand that erases can write the true thing."),
                    SpiritualTeacher("Sedata", "Many have died; you also will die. The drum of death is being beaten."),
                    SpiritualTeacher("Allan Watts", "Where there are humans, You'll find flies,And Buddhas."),
                    SpiritualTeacher("Leo Gura", "Silence is the language of Om. We need silence to be able to reach our Self."),
                    SpiritualTeacher("Rupert Spira", "One day in my shoes and a day for me in your shoes, the beauty of travel lies "),
                    SpiritualTeacher("Sadhguru", "Like vanishing dew,a passing apparition or the sudden flashnof lightning"))
    internal var sb: StringBuilder? = null
    internal lateinit var adapter: MyAdapter

    inner class SpiritualTeacher(var name: String?, val quote: String, val image: Int? = null) {
        var isSelected: Boolean = false
    }

    internal class MyAdapter(var c: Context, var teachers: Array<SpiritualTeacher>) : RecyclerView.Adapter<MyAdapter.MyHolder>() {
        var checkedTeachers = ArrayList<SpiritualTeacher>()

        //VIEWHOLDER IS INITIALIZED
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.model, null)
            return MyHolder(v)
        }

        //DATA IS BOUND TO VIEWS
        override fun onBindViewHolder(holder: MyHolder, position: Int) {
            val teacher = teachers[position]
            holder.nameTxt.text = teacher.name
            holder.posTxt.text = teacher.quote
            holder.myCheckBox.isChecked = teacher.isSelected
            teacher.image?.let { holder.img.setImageResource(it) }

            holder.setItemClickListener(object : MyHolder.ItemClickListener {
                override fun onItemClick(v: View, pos: Int) {
                    val myCheckBox = v as CheckBox
                    val currentTeacher = teachers[pos]

                    if (myCheckBox.isChecked) {
                        currentTeacher.isSelected = true
                        checkedTeachers.add(currentTeacher)
                    } else if (!myCheckBox.isChecked) {
                        currentTeacher.isSelected = false
                        checkedTeachers.remove(currentTeacher)
                    }
                }
            })
        }

        override fun getItemCount(): Int {
            return teachers.size
        }

        internal class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

            var img: ImageView
            var nameTxt: TextView
            var posTxt: TextView
            var myCheckBox: CheckBox

            lateinit var myItemClickListener: ItemClickListener

            init {

                nameTxt = itemView.findViewById(R.id.nameTextView)
                posTxt = itemView.findViewById(R.id.descritionTextView)
                img = itemView.findViewById(R.id.teacherImageView)
                myCheckBox = itemView.findViewById(R.id.myCheckBox)

                myCheckBox.setOnClickListener(this)
            }

            fun setItemClickListener(ic: ItemClickListener) {
                this.myItemClickListener = ic
            }

            override fun onClick(v: View) {
                this.myItemClickListener.onItemClick(v, layoutPosition)
            }

            internal interface ItemClickListener {

                fun onItemClick(v: View, pos: Int)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MyAdapter(this, teachers)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            sb = StringBuilder()

            var i = 0
            do {
                val spiritualTeacher = adapter.checkedTeachers[i]
                sb!!.append(spiritualTeacher.name)
                if (i != adapter.checkedTeachers.size - 1) {
                    sb!!.append("\n")
                }
                i++

            } while (i < adapter.checkedTeachers.size)

            if (adapter.checkedTeachers.size > 0) {
                Toast.makeText(this@MainActivity, sb!!.toString(), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "Please Check An Item First", Toast.LENGTH_SHORT).show()
            }
        }

        //RECYCLER
        val rv = findViewById(R.id.myRecycler) as RecyclerView
        rv.layoutManager = LinearLayoutManager(this)
        rv.itemAnimator = DefaultItemAnimator()

        //SET ADAPTER
        rv.adapter = adapter

    }
// end
}
